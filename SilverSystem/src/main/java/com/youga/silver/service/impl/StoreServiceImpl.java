package com.youga.silver.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.youga.silver.obj.BaseConfig;
import com.youga.silver.obj.GoodsBase;
import com.youga.silver.service.StoreService;
import com.youga.silver.util.EnCodingUtil;
import com.youga.silver.util.ProtocoUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class StoreServiceImpl implements StoreService {

    BaseConfig config = new BaseConfig();
    @Override
    public List<GoodsBase> getAllStore(String merchantid) {

        List<GoodsBase> storeList = new ArrayList<>();

        String getAllStoreUrl = config.getAllStoreUrl();
        String result = null;
        List<NameValuePair> params = new ArrayList<>();
        NameValuePair name_shopid = new BasicNameValuePair("shopid",merchantid);

        params.add(name_shopid);

        try {
            result  = ProtocoUtil.postWithParamsForString(getAllStoreUrl,params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //resutl -> GoodsBaseList;

        //返回空列表
        if (result.equals("ST1001")){
            return null;
        }

        storeList = GetStoreFromResult(result);

        return storeList;
    }

    @Override
    public Boolean modifyGoodsCls(String merchantid, String goodsid, String clsType) {
        List<GoodsBase> storeList = new ArrayList<>();
        String modifyGoodsClsUrl = config.getmodifyGoodsClsUrl();
        String result = null;
        List<NameValuePair> params = new ArrayList<>();
        NameValuePair name_shopid = new BasicNameValuePair("shopid",merchantid);
        NameValuePair name_goodsid = new BasicNameValuePair("goodsid",goodsid);
        NameValuePair name_clsType = new BasicNameValuePair("clsType",clsType);
        params.add(name_shopid);
        params.add(name_goodsid);
        params.add(name_clsType);
        try {
            result  = ProtocoUtil.postWithParamsForString(modifyGoodsClsUrl,params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //resutl -> GoodsBaseList;

        //判断MCC返回的值，ST1002为更新失败；
        if (result.equals("ST1002")){
            return false;
        }
        else if (result.equals("1")){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<GoodsBase> getStoreByClsType(String merchantid, String clsType) {
        List<GoodsBase> storeList = new ArrayList<>();
        String getStoreByClsTypeUrl = config.getStoreByClsTypeUrl(); //getStoreByClsType
        String result = null;
        List<NameValuePair> params = new ArrayList<>();
        NameValuePair name_shopid = new BasicNameValuePair("shopid",merchantid);
        NameValuePair name_cls = new BasicNameValuePair("clsType",clsType);

        params.add(name_shopid);
        params.add(name_cls);

        try {
            result  = ProtocoUtil.postWithParamsForString(getStoreByClsTypeUrl,params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //resutl -> GoodsBaseList;

        //返回空列表
        if (result.equals("ST1001")){
            return null;
        }

        storeList = GetStoreFromResult(result);

        return storeList;
    }

    @Override
    public List<GoodsBase> getStoreByGoodsName(String merchantid, String goodsName) {
        List<GoodsBase> storeList = new ArrayList<>();
        String getStoreByGoodsNameUrl = config.getStoreByGoodsNameUrl(); //getStoreByGoodsName
        String result = null;
        List<NameValuePair> params = new ArrayList<>();
        NameValuePair name_shopid = new BasicNameValuePair("shopid",merchantid);

        //针对有中文存在的特殊情况进行处理
        String utfCodeGoodsName = EnCodingUtil.gbEncoding(goodsName);
        NameValuePair name_goodsName = new BasicNameValuePair("goodsName",utfCodeGoodsName);


        params.add(name_shopid);
        params.add(name_goodsName);

        try {
            result  = ProtocoUtil.postWithParamsForString(getStoreByGoodsNameUrl,params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //resutl -> GoodsBaseList;

        //返回空列表
        if (result.equals("ST1001")){
            return null;
        }

        storeList = GetStoreFromResult(result);

        return storeList;
    }

    @Override
    public GoodsBase getStoreByGoodsId(String merchantid, String goodsid) {

        String getStoreByGoodsIdUrl = config.getStoreByGoodsIdUrl(); //getStoreByGoodsId
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
        return JSON.parseObject(result,GoodsBase.class);

    }

    @Override
    public boolean checkStoreExist(String merchantid, String goodsid) {
        String checkStoreByGoodsIdUrl = config.getcheckStoreByGoodsIdUrl(); //checkStoreByGoodsId
        String result = null;
        List<NameValuePair> params = new ArrayList<>();
        NameValuePair name_shopid = new BasicNameValuePair("shopid",merchantid);
        NameValuePair name_goodsid = new BasicNameValuePair("goodsid",goodsid);


        params.add(name_shopid);
        params.add(name_goodsid);

        try {
            result  = ProtocoUtil.postWithParamsForString(checkStoreByGoodsIdUrl,params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //resutl -> GoodsBaseList;

        //返回空列表
        if (result.equals("ST1003")){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public boolean addStoreSingle(String merchantid, GoodsBase goodsInfo) {
        String addStoreByGoodsIdUrl = config.getaddStoreByGoodsIdUrl(); //checkStoreByGoodsId
        String result = null;
        List<NameValuePair> params = new ArrayList<>();
        NameValuePair name_shopid = new BasicNameValuePair("shopid",merchantid);
        NameValuePair name_goodsid = new BasicNameValuePair("goodsid",goodsInfo.getGoodsId());
        //中文防乱码
        NameValuePair name_goodsname = new BasicNameValuePair("goodsname",EnCodingUtil.gbEncoding(goodsInfo.getGoodsName()));
        NameValuePair name_goodscount = new BasicNameValuePair("goodscount",goodsInfo.getGoodsCount());
        NameValuePair name_goodsprice = new BasicNameValuePair("goodsprice",goodsInfo.getGoodsPrice());
        //中文防乱码
        NameValuePair name_goodsbrand = new BasicNameValuePair("goodsbrand",EnCodingUtil.gbEncoding(goodsInfo.getGoodsClassify()));
        NameValuePair name_goodsstander = new BasicNameValuePair("goodsstander",goodsInfo.getGoodsStander());

        //2019-01-18 新增对类型的添加，有则添加，无则添加空字符串
        NameValuePair name_goodscls = new BasicNameValuePair("goodscls",goodsInfo.getGoodsCls());

        params.add(name_shopid);
        params.add(name_goodsid);
        params.add(name_goodsname);
        params.add(name_goodscount);
        params.add(name_goodsprice);
        params.add(name_goodsbrand);
        params.add(name_goodsstander);
        params.add(name_goodscls);


        try {
            result  = ProtocoUtil.postWithParamsForString(addStoreByGoodsIdUrl,params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //resutl -> GoodsBaseList;

        //返回空列表
        if (result.equals("ST1004")){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean conStoreSingle(String merchantid, String goodsid, String goodscount) {
        String conStoreByGoodsIdUrl = config.getconStoreByGoodsIdUrl(); //conStoreByGoodsId
        String result = null;
        List<NameValuePair> params = new ArrayList<>();
        NameValuePair name_shopid = new BasicNameValuePair("shopid",merchantid);
        NameValuePair name_goodsid = new BasicNameValuePair("goodsid",goodsid);
        NameValuePair name_goodscount = new BasicNameValuePair("goodscount",goodscount);

        params.add(name_shopid);
        params.add(name_goodsid);
        params.add(name_goodscount);

        try {
            result  = ProtocoUtil.postWithParamsForString(conStoreByGoodsIdUrl,params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //resutl -> GoodsBaseList;

        //返回空列表
        if (result.equals("ST1004")){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public List<GoodsBase> getBatchGoodsListWithBatchLine(List<String> goodsListLine) {

        List<GoodsBase> goodslist = new ArrayList<>();

        for (String goodsline : goodsListLine){

            String [] goodsArr = goodsline.split(",");
            String goodsid = goodsArr[0];
            String goodsname = goodsArr[1];
            String stander = goodsArr[2];
            String goodsprice = goodsArr[3];
            String instock = goodsArr[4];
            String classify = goodsArr[5];
            String cls = goodsArr[6];

            GoodsBase goods = new GoodsBase(goodsid,goodsname,goodsprice,instock,
                    String.valueOf(Float.valueOf(goodsprice).intValue()/10));
            goods.setGoodsClassify(classify);
            goods.setGoodsStander(stander);
            goods.setGoodsCls(cls);

            goodslist.add(goods);

        }

        return goodslist;
    }

    @Override
    public List<GoodsBase> batchAddStore(String merchantid, List<GoodsBase> goodslist) {

        //1.遍历商品，检查商品是否存在，存在不添加，不存在添加
        List<GoodsBase> goodsAlreadyInUse = new ArrayList<>();

        for (GoodsBase goods :goodslist){

            if (this.checkStoreExist(merchantid,goods.getGoodsId())){
                goodsAlreadyInUse.add(goods);
            }else {

                //进行商品添加-商品添加要新增可以添加商品类别
                this.addStoreSingle(merchantid,goods);
            }
        }
        return goodsAlreadyInUse;
    }

    private List<GoodsBase> GetStoreFromResult(String result) {

        return JSONArray.parseArray(result).toJavaList(GoodsBase.class);

    }
}
