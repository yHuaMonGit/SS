package com.youga.silver.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.youga.silver.obj.BaseConfig;
import com.youga.silver.obj.GoodsBase;
import com.youga.silver.obj.MemberInfo;
import com.youga.silver.service.MemberService;
import com.youga.silver.util.ProtocoUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class MemberServiceImpl implements MemberService {

    BaseConfig config = new BaseConfig();

    @Override
    public List<MemberInfo> getAllMember(String merchantId) {
        List<MemberInfo> memberlist = new ArrayList<>();

        String getAllMemberUrl = config.getAllMemberUrl();
        String result = null;
        List<NameValuePair> params = new ArrayList<>();
        NameValuePair name_shopid = new BasicNameValuePair("shopid",merchantId);

        params.add(name_shopid);

        try {
            result  = ProtocoUtil.postWithParamsForString(getAllMemberUrl,params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //resutl -> GoodsBaseList;

        //返回空列表
        if (result.equals("ST1001")){
            return null;
        }

        memberlist = GetStoreFromResult(result);

        return memberlist;
    }

    @Override
    public MemberInfo getMemberInfoByMsisdn(String msisdn) {
        MemberInfo member = null;

        String requestUrl = config.getMemberInfoByMsisdn();//getMemberInfoByMsisdn
        String result = null;
        List<NameValuePair> params = new ArrayList<>();
        NameValuePair name_msisdn = new BasicNameValuePair("msisdn",msisdn);

        params.add(name_msisdn);

        try {
            result  = ProtocoUtil.postWithParamsForString(requestUrl,params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        //返回空列表
        if (result.equals("ST1001")){
            return null;
        }

        member = JSON.parseObject(result,MemberInfo.class);;

        return member;
    }

    @Override
    public void updateMemberInfo(MemberInfo member) {

        String requestUrl = config.updateMemberInfo();//updateMemberInfo
        String result = null;
        List<NameValuePair> params = new ArrayList<>();
        NameValuePair name_msisdn = new BasicNameValuePair("msisdn",member.getMsisdn());
        NameValuePair name_balance = new BasicNameValuePair("balance",member.getBalance());
        NameValuePair name_integral = new BasicNameValuePair("integral",member.getIntegral());

        params.add(name_msisdn);
        params.add(name_balance);
        params.add(name_integral);

        try {
            result  = ProtocoUtil.postWithParamsForString(requestUrl,params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private List<MemberInfo> GetStoreFromResult(String result) {

        return JSONArray.parseArray(result).toJavaList(MemberInfo.class);

    }

}
