package com.youga.silver.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.youga.silver.obj.BaseConfig;
import com.youga.silver.obj.GoodsBase;
import com.youga.silver.service.BillService;
import com.youga.silver.util.ProtocoUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class BillServiceImpl implements BillService {

    //通用配置
    BaseConfig config = new BaseConfig();

    @Override
    public List<GoodsBase> getBill(String goodsid,String merchantid) {

        List<GoodsBase> billList = new ArrayList<>();

        String getStoreByGoodsIdUrl = config.getBillUrl(); //getBillById
        String result = null;
        List<NameValuePair> params = new ArrayList<>();
        NameValuePair name_shopid = new BasicNameValuePair("shopid",merchantid);
        NameValuePair name_goodsid = new BasicNameValuePair("goodsid",goodsid);


        params.add(name_shopid);
        params.add(name_goodsid);

        try {
            result  = ProtocoUtil.postWithParamsForString(getStoreByGoodsIdUrl,params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //resutl -> GoodsBaseList;

        //返回空列表
        if (result.equals("ST1001")){
            return null;
        }

        billList = GetStoreFromResult(result);

        return billList;
    }

    @Override
    public void modifyBill(String goodsid, String merchantid, int modify_type_add) {


        String getStoreByGoodsIdUrl = config.modifyBillByID(); //modifyBillByID
        String result = null;
        List<NameValuePair> params = new ArrayList<>();
        NameValuePair name_shopid = new BasicNameValuePair("shopid",merchantid);
        NameValuePair name_goodsid = new BasicNameValuePair("goodsid",goodsid);
        NameValuePair name_type = new BasicNameValuePair("modify_type",String.valueOf(modify_type_add));

        params.add(name_shopid);
        params.add(name_goodsid);
        params.add(name_type);

        try {
            result  = ProtocoUtil.postWithParamsForString(getStoreByGoodsIdUrl,params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void clearBill(String shopid) {
        String getStoreByGoodsIdUrl = config.clearBill(); //clearBill
        String result = null;
        List<NameValuePair> params = new ArrayList<>();
        NameValuePair name_shopid = new BasicNameValuePair("shopid",shopid);

        params.add(name_shopid);

        try {
            result  = ProtocoUtil.postWithParamsForString(getStoreByGoodsIdUrl,params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private List<GoodsBase> GetStoreFromResult(String result) {

        return JSONArray.parseArray(result).toJavaList(GoodsBase.class);

    }

}
