package com.youga.silver.controller;

import com.alibaba.fastjson.JSONArray;
import com.aliyuncs.exceptions.ClientException;
import com.youga.silver.dao.MemberDao;
import com.youga.silver.dao.impl.MemberDaoImpl;
import com.youga.silver.obj.MemberInfo;
import com.youga.silver.service.MemberService;
import com.youga.silver.service.impl.MemberServiceImpl;
import com.youga.silver.util.SMSUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MemberController {

    MemberDao memberDao = new MemberDaoImpl();
    MemberService memberService = new MemberServiceImpl();

    //showAllMember
    @RequestMapping("/showAllMember")
    public void showAllMember(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String shopid = req.getParameter("shopid");

        List<MemberInfo> memberlist = new ArrayList<>();

        memberlist = memberService.getAllMember(shopid);

        resp.setContentType("text/html;charset=UTF-8");
        String JsonValue = JSONArray.toJSONString(memberlist);
        resp.getWriter().print(JsonValue);

    }


    @RequestMapping("/getMember")
    public void scanner(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String msisdn = req.getParameter("memberMsisdn");

        MemberInfo member = memberDao.getMemberInfo(msisdn);

        resp.setContentType("text/html;charset=UTF-8");
        String JsonValue = JSONArray.toJSONString(member);
        resp.getWriter().print(JsonValue);

        System.out.println("test");

    }


    @RequestMapping("/memberCharge")
    public void memberCharge(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String msisdn = req.getParameter("memberMsisdn");
        String chargeAmount = req.getParameter("chargeAmount");
        String serviceOff = req.getParameter("serviceOff");
        String goodsOff = req.getParameter("goodsOff");

        MemberInfo member = memberDao.getMemberInfo(msisdn);

        if(null == member.getMemberID())
        {
            resp.getWriter().print(1);
            return;
        }

        //不手动设置折扣的情况
        /**
         * 若当前折扣力度大于该段位应有折扣，则当前折扣不变更；
         * 若当前折扣力度小于该段位应有折扣，则折扣随之升级；
         */
        if(serviceOff.equals("")||null==serviceOff||goodsOff.equals("")||null == goodsOff)
        {
            member.calcuCharge(chargeAmount);
        }else {
            member.calcuCharge(chargeAmount);
            member.setServiceOff(serviceOff);
            member.setGoodsOff(goodsOff);
        }
        memberDao.chargeMemberInfo(member);
        memberDao.insertChargeInfo(member);
        //2019-01-03 用户充值、消费后应该将数据同步至线上DB;
        //2019-05-21 废弃该逻辑，不做同步处理，避免数据库连接过多；
//        if (memberDao.isOnlineMember(msisdn)){
//            memberDao.synchronizeOnlineMember(member);
//        }
        //充值成功后短信回馈
        try {
            //SMSUtil.sendChargeRecord(member.getMsisdn(),member.getMemberName(),member.getAmount(),member.getBalance());
            SMSUtil.firstChargeSms(member.getMsisdn(),member.getMemberName(),member.getAmount(),member.getBalance());

        } catch (ClientException e) {
            //若报错要加打印日志
        }
        resp.getWriter().print(2);
    }


    @RequestMapping("/register")
    public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String name = req.getParameter("memberName");
        String msisdn = req.getParameter("memberMsisdn");
        String petName = req.getParameter("petName");
        String firstCharge = req.getParameter("firstCharge");
        String serviceOff = req.getParameter("serviceOff");
        String goodsOff = req.getParameter("goodsOff");

        MemberInfo member = memberDao.getMemberInfo(msisdn);
        if (null != member.getMemberID())
        {
            resp.getWriter().print(1);
            return;
        }else {

            if(serviceOff.equals("")||null==serviceOff||goodsOff.equals("")||null == goodsOff)
            {
                member = new MemberInfo(
                        getMemberId(),
                        name,
                        member.getMemberLevel(firstCharge),
                        member.getFirstInter(firstCharge),
                        "1",
                        msisdn,
                        firstCharge,
                        firstCharge
                );
                member.setDefaultOff(member.getMemberLevelId());
                member.setPetName(petName);
                member.setHisAmount(firstCharge);
            }
            else
                {
                    member = new MemberInfo(
                            getMemberId(),
                            name,
                            member.getMemberLevel(firstCharge),
                            member.getFirstInter(firstCharge),
                            "1",
                            msisdn,
                            firstCharge,
                            firstCharge,
                            serviceOff,
                            goodsOff
                    );
                }

            member.setPetName(petName);
            member.setHisAmount(firstCharge);

            try {
                memberDao.insertMemberInfo(member);
                memberDao.insertChargeInfo(member);
                resp.getWriter().print(2);

                //会员注册成功发送短信
                //SMSUtil.sendRegisterSuccMsg(member.getMsisdn(),member.getMemberName());
                //发送充值短信
                //SMSUtil.sendChargeRecord(member.getMsisdn(),member.getMemberName(),member.getAmount(),member.getBalance());
                SMSUtil.firstChargeSms(member.getMsisdn(),member.getMemberName(),member.getAmount(),member.getBalance());
            }catch (Exception ex){
                resp.getWriter().print(3);
                return;
            }

        }
        resp.setContentType("text/html;charset=UTF-8");

    }

    private String getMemberId() {

        String lastId = memberDao.getLastId();
        String head = "yg-";
        String rear = lastId.replace(head,"");
        int Num = Integer.valueOf(rear);
        Num++;
        String outStr = String.format("%05d", Num);
        outStr = head + outStr;
        return outStr;
    }

}
