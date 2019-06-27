package com.youga.silver.controller;

import com.alibaba.fastjson.JSONArray;
import com.aliyuncs.exceptions.ClientException;
import com.youga.silver.dao.BillDao;
import com.youga.silver.dao.GoodsDao;
import com.youga.silver.dao.MemberDao;
import com.youga.silver.dao.OrderDao;
import com.youga.silver.dao.impl.BillDaoImpl;
import com.youga.silver.dao.impl.GoodsDaoImpl;
import com.youga.silver.dao.impl.MemberDaoImpl;
import com.youga.silver.dao.impl.OrderDaoImpl;
import com.youga.silver.obj.GoodsBase;
import com.youga.silver.obj.MemberInfo;
import com.youga.silver.obj.OrderInfo;
import com.youga.silver.obj.ServiceBase;
import com.youga.silver.service.BillService;
import com.youga.silver.service.MemberService;
import com.youga.silver.service.OrderService;
import com.youga.silver.service.impl.BillServiceImpl;
import com.youga.silver.service.impl.MemberServiceImpl;
import com.youga.silver.service.impl.OrderServiceImpl;
import com.youga.silver.util.SMSUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.youga.printFT.*;


import static com.youga.silver.util.OrderIdUtil.getOrderId;


@Controller
public class OrderController {

    MemberDao memberDao = new MemberDaoImpl();
    BillDao billDao = new BillDaoImpl();

    MemberService memberService = new MemberServiceImpl();
    BillService billService = new BillServiceImpl();
    OrderService orderService = new OrderServiceImpl();


    @RequestMapping("/getOrder")
    public void getOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String msisdn = req.getParameter("memberMsisdn");
        String shopid = req.getParameter("merchantid");
        OrderInfo orderInfo = getNewOrder(msisdn);
        Boolean isCookieHere = false;
        //判断当前是否已经有订单，有订单从cookie里读取订单，无订单则生成写入cookie
        Cookie[] cookies = req.getCookies();
        if(null == cookies)
        {
            //设置cookie
            Cookie returnUrl = new Cookie("orderId_offline",orderInfo.getOrderId());
            returnUrl.setPath("/");
            resp.addCookie(returnUrl);

        }
        else
            {
                for(Cookie cookie : cookies){
                    // get the cookie name
                    if("orderId_offline".equals(cookie.getName()))
                    {
                        isCookieHere = true;
                        String orderId = cookie.getValue();
                        orderInfo.setOrderId(orderId);
                    }
                }
            }
        if(!isCookieHere)
        {
            //设置cookie
            Cookie returnUrl = new Cookie("orderId_offline",orderInfo.getOrderId());
            returnUrl.setPath("/");
            resp.addCookie(returnUrl);
        }
        resp.setContentType("text/html;charset=UTF-8");
        String JsonValue = JSONArray.toJSONString(orderInfo);
        resp.getWriter().print(JsonValue);
    }

    @RequestMapping("/submitOrder")
    public void submitOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String orderid = req.getParameter("orderId");
        String msisdn = req.getParameter("memberMsisdn");
        String activeType = req.getParameter("activeType");
        String activeOff = req.getParameter("activeOff");
        String orderTime = req.getParameter("orderTime");

        //2018-12-29 新增额外扣减功能/新增备注记录功能
        String decAmount = req.getParameter("decAmount");
        String decAuthor = req.getParameter("decAuthor");
        String note = req.getParameter("note");
        String shopid = req.getParameter("merchantid");


        MemberInfo member = null;

        //生成订单
        OrderInfo orderInfo = getNewOrder(msisdn);
        orderInfo.setOrderId(orderid);
        //额外增加活动价格
        orderInfo.setActiveType(activeType);
        orderInfo.setActiveOff(activeOff);
        orderInfo.setOrderTime(orderTime);
        orderInfo.setNote(note);
        orderInfo.setDecAmount(decAmount);
        orderInfo.setDecAuthor(decAuthor);
        if(null == msisdn||msisdn.equals(""))
        {
            orderInfo.setMemberFlag("0");
            orderInfo.setMsisdn("null");
            orderInfo.setMemberID("null");
            orderInfo.setMemberLevel("null");
            orderInfo.setMemberIntergral(member);
            orderInfo.setActiveAmount(member);
            //留一个接口，万一以后要处理呢？
        }else
        {
            //2019-05-07 提速修复：
            member = memberService.getMemberInfoByMsisdn(msisdn);
            //member = memberDao.getMemberInfo(msisdn);
            //2018-12-22 修复会员订单记录插入没有记录msisdn的情况;
            orderInfo.setMsisdn(msisdn);
            orderInfo.setMemberIntergral(member);
            orderInfo.setActiveAmount(member);
            //若用户余额不足，则返回要求用户充值
            if(Float.valueOf(member.getBalance())<Float.valueOf(orderInfo.getActiveAmount()))
            {
                resp.getWriter().print(2);
                return;
            }else{
                orderInfo.setMemberFlag("1");
                orderInfo.setMemberID(member.getMemberID());
                orderInfo.setMemberLevel(member.getMemberLevel());
                member.setIntegral(String.valueOf(Float.valueOf(member.getIntegral())+Float.valueOf(orderInfo.getIntergral())));
                member.setBalance(String.valueOf(Float.valueOf(member.getBalance())-Float.valueOf(orderInfo.getActiveAmount())));
                memberService.updateMemberInfo(member);
                //memberDao.updateMemberInfo(member);

                //2019-05-07 方法废弃，合并为一个表
                //2019-01-03 若有对应的线上会员，应当同步线上会员信息
//                if (memberDao.isOnlineMember(msisdn)){
//                    memberDao.synchronizeOnlineMember(member);
//                }

                try {
                    //SMSUtil.sendCosumerRecord(member.getMsisdn(),member.getMemberName(),orderInfo.getActiveAmount());
                    SMSUtil.sendCosumerRecordForBaking(member.getMsisdn(),orderInfo.getActiveAmount(),member.getBalance());

                } catch (ClientException e) {
                    //Log Here : Msg send error
                }
            }
        }
        //订单入库;
        //2019-05-07 提速修复
        orderService.insertOrderInfo(orderInfo,shopid);
        //orderDao.insertOrder(orderInfo);
        //删除账单表bill
        billService.clearBill(shopid);
        //billDao.clearBill();
        //打印小票;
        List<GoodsInfo> goods = new ArrayList<>();
        for(GoodsBase goodbases: orderInfo.getGoodslist())
        {
            //库存减少
            goodbases.calculateStore();
            //2019-05-07:优化速度，暂时取消库存减少，下次更新时引入新的库存减少机制
            //goodsDao.UpdateStore(goodbases);
            GoodsInfo goodsInfo = new GoodsInfo(goodbases.getGoodsName(),
                    goodbases.getGoodsPrice(),
                    goodbases.getGoodsCount(),
                    String.valueOf(Float.valueOf(goodbases.getGoodsPrice())*Integer.valueOf(goodbases.getGoodsCount())));
            goods.add(goodsInfo);
        }

        for (ServiceBase service:orderInfo.getServiceBaseList())
        {
            GoodsInfo goodsInfo = new GoodsInfo(service.getServiceName(),
                    service.getServicePrice(),
                    service.getStander(),
                    String.valueOf(Float.valueOf(service.getServicePrice())*Integer.valueOf(service.getStander())));
            goods.add(goodsInfo);
        }

        String Opreater = "OP-00001";
        OrderBase order = new com.youga.printFT.OrderBase(goods,orderid,Float.valueOf(orderInfo.getActiveAmount()),
                Float.valueOf(orderInfo.getActiveOff()),Opreater,orderInfo.getMemberLevel(),orderInfo.getMemberOff());
        PrintTicket.PrintShoppingTicket(order);
        PrintTicket.PrintShoppingTicket(order);
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().print(1);

        //删除cookie
        Cookie newCookie=new Cookie("orderId_offline",null); //假如要删除名称为username的Cookie
        newCookie.setMaxAge(0); //立即删除型
        newCookie.setPath("/"); //项目所有目录均有效，这句很关键，否则不敢保证删除
        resp.addCookie(newCookie); //重新写入，将覆盖之前的

    }

    public OrderInfo getNewOrder(String msisdn)
    {
        //生成订单
        OrderInfo orderInfo = new OrderInfo();
        //0.生成订单号
        orderInfo.setOrderId(getOrderId());
        //1.获取bill list
        List<GoodsBase> goodsBaseList = new ArrayList<>();
        goodsBaseList = billDao.getBill();
        orderInfo.setGoodslist(goodsBaseList);
        //2.设置货品数量及金额
        orderInfo.setAllGoodsNum();
        orderInfo.setMoneyAmount();

        /**
         * 会员身份检查
         */

        if(null == msisdn||msisdn.equals(""))
        {
            //留一个接口，万一以后要处理呢？
        }else
        {
            MemberInfo member = memberDao.getMemberInfo(msisdn);
            orderInfo.setMemberAmount(member);
        }

        return orderInfo;
    }

}


