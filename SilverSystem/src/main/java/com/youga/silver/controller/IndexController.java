package com.youga.silver.controller;


import com.alibaba.fastjson.JSONArray;
import com.youga.silver.dao.BillDao;
import com.youga.silver.dao.GoodsDao;
import com.youga.silver.dao.ServiceDao;
import com.youga.silver.dao.impl.BillDaoImpl;
import com.youga.silver.dao.impl.GoodsDaoImpl;
import com.youga.silver.dao.impl.ServiceDaoImpl;
import com.youga.silver.obj.GoodsBase;
import com.youga.silver.obj.MerchantInfo;
import com.youga.silver.obj.ServiceBase;
import com.youga.silver.service.BillService;
import com.youga.silver.service.MerchantService;
import com.youga.silver.service.impl.BillServiceImpl;
import com.youga.silver.service.impl.MerchantServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class IndexController {

    int MODIFY_TYPE_ADD = 0;
    int MODIFY_TYPE_DEC = 1;
    int MODIFY_TYPE_DLL = 2;

    GoodsDao goodsDao = new GoodsDaoImpl();
    BillDao billDao = new BillDaoImpl();
    ServiceDao serviceDao = new ServiceDaoImpl();
    MerchantService merchantService = new MerchantServiceImpl();

    BillService billService = new BillServiceImpl();

    @RequestMapping("/home")
    public String index(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        //2019-01-07 新增用户参数，必须通过用户校验才能进入这个页面;
        String shopid = req.getParameter("shopid");
        if (null == shopid||"".equals(shopid)){
            return "login";
        }
        else if (!merchantService.checkShopid(shopid)){
            return "login";
        }

        return "tmpEnter";
    }




    //2019-04-26 提单，扫描式修改，修改为中转式；
    //2019-05-04 提单，扫描速度慢，外网DB读取速度为500ms，逻辑优化为内网读取，由http直接返回；
    @RequestMapping("/scanner")
    public void scanner(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String goodsId = req.getParameter("goodsId");
        String merchantid = req.getParameter("merchantid");
        //2019-05-04 优化：中转式读取，提高数据库访问速率
        List<GoodsBase> goodsBaseList = billService.getBill(goodsId,merchantid);
        resp.setContentType("text/html;charset=UTF-8");
        String JsonValue = JSONArray.toJSONString(goodsBaseList);
        resp.getWriter().print(JsonValue);


    }


    @RequestMapping("/showAll")
    public void showAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        String merchantid = req.getParameter("merchantid");

        List<GoodsBase> goodsBaseList = billService.getBill("XL-00001-9",merchantid);

        resp.setContentType("text/html;charset=UTF-8");
        String JsonValue = JSONArray.toJSONString(goodsBaseList);
        resp.getWriter().print(JsonValue);
    }

    @RequestMapping("/billAdd")
    public void billAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String goodsid = req.getParameter("goodsid");
        String merchantid = req.getParameter("merchantid");

        //2019-05-06 请求中转式，提高访问效率
        billService.modifyBill(goodsid,merchantid,MODIFY_TYPE_ADD);

    }

    @RequestMapping("/billDec")
    public void billDec(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String goodsid = req.getParameter("goodsid");
        String merchantid = req.getParameter("merchantid");

        //2019-05-06 请求中转式，提高访问效率
        billService.modifyBill(goodsid,merchantid,MODIFY_TYPE_DEC);

    }

    @RequestMapping("/billDel")
    public void billDel(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        String goodsid = req.getParameter("goodsid");
        String merchantid = req.getParameter("merchantid");

        billService.modifyBill(goodsid,merchantid,MODIFY_TYPE_DLL);

    }


    @RequestMapping("/getUl")
    public void getUl(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        String ulType = req.getParameter("ulType");

        List<ServiceBase> ServiceBaseList = serviceDao.getServiceFromType(ulType);


        if(ServiceBaseList.isEmpty())
        {
            resp.getWriter().print(1);
            return;
        }



        resp.setContentType("text/html;charset=UTF-8");
        String JsonValue = JSONArray.toJSONString(ServiceBaseList);
        resp.getWriter().print(JsonValue);

    }

    @RequestMapping("/addService")
    public void addService(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        String serviceId = req.getParameter("serviceId");
        String serviceNum = req.getParameter("serviceNum");

        //2018-12-22 修复重复插入服务记录导致插入sql主键重复报错问题；
        List<GoodsBase> goodslist = billDao.getBill();
        for (GoodsBase goods:goodslist)
        {
            if(goods.getGoodsId().equals(serviceId))
            {
                resp.getWriter().print(2);
                return;
            }
        }

        ServiceBase serviceBase = serviceDao.getSingleService(serviceId);


        if(null == serviceBase)
        {
            resp.getWriter().print(1);
            return;
        }

        billDao.insertBill(new GoodsBase(
                serviceBase.getServiceId(),
                serviceBase.getServiceName(),
                serviceBase.getServicePrice(),
                serviceNum,
                String.valueOf(Integer.valueOf(serviceBase.getServicePrice())/10*Integer.valueOf(serviceNum))
        ));

        List<GoodsBase> goodsBaseList = billDao.getBill();
        resp.setContentType("text/html;charset=UTF-8");
        String JsonValue = JSONArray.toJSONString(goodsBaseList);
        resp.getWriter().print(JsonValue);

    }



    @RequestMapping("/initalPages")
    public void initalPages(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        String shopid = req.getParameter("shopid");

        MerchantInfo merchantInfo = merchantService.getMerchantInfoById(shopid);

        resp.setContentType("text/html;charset=UTF-8");
        String JsonValue = JSONArray.toJSONString(merchantInfo);
        resp.getWriter().print(JsonValue);

    }
}
