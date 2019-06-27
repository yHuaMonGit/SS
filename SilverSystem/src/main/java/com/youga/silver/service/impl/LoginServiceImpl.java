package com.youga.silver.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.youga.silver.obj.BaseConfig;
import com.youga.silver.obj.MerchantInfo;
import com.youga.silver.service.LoginService;
import com.youga.silver.util.ProtocoUtil;
import org.apache.http.NameValuePair;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginServiceImpl implements LoginService {

    BaseConfig config = new BaseConfig();

    @Override
    public boolean checkAccount(String acc, String pass) {

        String checkAccUrl = config.getMCCAccVerifyUrl();
        String result = null;
        List<NameValuePair> params = new ArrayList<>();
        NameValuePair name_acc = new BasicNameValuePair("acc",acc);
        NameValuePair name_pss = new BasicNameValuePair("pass", pass);
        params.add(name_acc);
        params.add(name_pss);
        try {
            result  = ProtocoUtil.postWithParamsForString(checkAccUrl,params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (result!=null&&!result.equals("null")){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public MerchantInfo getMerchantInfo(String acc) {
        String result = null;
        MerchantInfo merchantInfo = new MerchantInfo();
        String getMerchantInfoUrl = config.getMerchantInfoUrl();
        List<NameValuePair> params = new ArrayList<>();
        NameValuePair name_acc = new BasicNameValuePair("acc",acc);
        params.add(name_acc);
        try {
            result  = ProtocoUtil.postWithParamsForString(getMerchantInfoUrl,params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (result!=null){
            merchantInfo.ConstructByJsonString(result);
        }
        return merchantInfo;
    }
}
