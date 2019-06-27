package com.youga.silver.service.impl;

import com.youga.silver.obj.BaseConfig;
import com.youga.silver.obj.OrderInfo;
import com.youga.silver.service.OrderService;
import com.youga.silver.util.EnCodingUtil;
import com.youga.silver.util.ProtocoUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {


    BaseConfig config = new BaseConfig();
    /***
     * 插入一个订单
     * @param orderInfo
     * @param shopid
     */
    @Override
    public void insertOrderInfo(OrderInfo orderInfo, String shopid) {

        String requestUrl = config.insertOrderInfo();//insertOrderInfo
        String result = null;
        List<NameValuePair> params = new ArrayList<>();
        NameValuePair shopidN = new BasicNameValuePair("shopid",shopid);
        NameValuePair orderId = new BasicNameValuePair("orderId",orderInfo.getOrderId());
        NameValuePair msisdn = new BasicNameValuePair("msisdn",orderInfo.getMsisdn());
        NameValuePair orderOutTime = new BasicNameValuePair("orderOutTime",orderInfo.getOrderTime());
        NameValuePair memberID = new BasicNameValuePair("memberID",orderInfo.getMemberID());
        NameValuePair memberFlag = new BasicNameValuePair("memberFlag",orderInfo.getMemberFlag());
        NameValuePair memberLevel = new BasicNameValuePair("memberLevel",orderInfo.getMemberLevel());
        NameValuePair iniAmount = new BasicNameValuePair("iniAmount",orderInfo.getMoneyAmount());
        NameValuePair actAmount = new BasicNameValuePair("actAmount",orderInfo.getActiveAmount());
        NameValuePair activeOffType = new BasicNameValuePair("activeOffType",orderInfo.getActiveType());
        NameValuePair activeOff = new BasicNameValuePair("activeOff",orderInfo.getActiveOff());
        NameValuePair goodsList = new BasicNameValuePair("goodsList",orderInfo.getGoodslistToString());
        String utfCodeGoodsRemarks = EnCodingUtil.gbEncoding(orderInfo.getNote());
        NameValuePair goodsRemarks = new BasicNameValuePair("goodsRemarks",utfCodeGoodsRemarks);
        NameValuePair decAmount = new BasicNameValuePair("decAmount",orderInfo.getDecAmount());
        String utfCodeDecAuthor = EnCodingUtil.gbEncoding(orderInfo.getDecAuthor());
        NameValuePair decAuthor = new BasicNameValuePair("decAuthor",utfCodeDecAuthor);


        params.add(shopidN);
        params.add(orderId);
        params.add(msisdn);
        params.add(orderOutTime);
        params.add(memberID);
        params.add(memberFlag);
        params.add(memberLevel);
        params.add(iniAmount);
        params.add(actAmount);
        params.add(activeOffType);
        params.add(activeOff);
        params.add(goodsList);
        params.add(goodsRemarks);
        params.add(decAmount);
        params.add(decAuthor);

        try {
            result  = ProtocoUtil.postWithParamsForString(requestUrl,params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public boolean checkNewOrder() {
        String requestUrl = config.checkNewOrder();//insertOrderInfo
        String result = "";
        Boolean resultOut = false;

        List<NameValuePair> params = new ArrayList<>();
        NameValuePair shopidN = new BasicNameValuePair("shopid","yg000001");
        params.add(shopidN);

        try {
            result  = ProtocoUtil.postWithParamsForString(requestUrl,params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (null == result || "1".equals(result)){
            resultOut = false;
        }else if ("2".equals(result)){
            resultOut = true;
        }
        return resultOut;
    }
}
