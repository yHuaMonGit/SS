package com.youga.silver.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.youga.silver.obj.BaseConfig;
import com.youga.silver.obj.GoodsBase;
import com.youga.silver.obj.MerchantInfo;
import com.youga.silver.service.MerchantService;
import com.youga.silver.util.ProtocoUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class MerchantServiceImpl implements MerchantService {

    BaseConfig config = new BaseConfig();
    @Override
    public boolean checkShopid(String shopid) {
        String checkShopidUrl = config.getCheckShopidUrl();
        String result = null;
        List<NameValuePair> params = new ArrayList<>();
        NameValuePair name_shopid = new BasicNameValuePair("shopid",shopid);

        params.add(name_shopid);

        try {
            result  = ProtocoUtil.postWithParamsForString(checkShopidUrl,params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (result.equals("1")){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public MerchantInfo getMerchantInfoById(String shopid) {
        String merchantInfoGetsUrl = config.getMerchantInfoById();
        //getMerchantById
        String result = null;
        List<NameValuePair> params = new ArrayList<>();
        NameValuePair name_shopid = new BasicNameValuePair("shopid",shopid);

        params.add(name_shopid);

        try {
            result  = ProtocoUtil.postWithParamsForString(merchantInfoGetsUrl,params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return JSON.parseObject(result,MerchantInfo.class);
    }
}
