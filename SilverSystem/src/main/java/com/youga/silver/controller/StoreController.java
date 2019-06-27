package com.youga.silver.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.youga.silver.obj.GoodsBase;
import com.youga.silver.obj.MerchantInfo;
import com.youga.silver.service.StoreService;
import com.youga.silver.service.impl.StoreServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StoreController {

    StoreService storeService = new StoreServiceImpl();

    @RequestMapping("/showAllGoods")
    public void showAllGoods(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String merchantid = req.getParameter("shopid");

        List<GoodsBase> storeList = storeService.getAllStore(merchantid);

        if (null == storeList){
            resp.getWriter().print(1);
            return;
        }else {
            resp.setContentType("text/html;charset=UTF-8");
            String JsonValue = JSONObject.toJSONString(storeList);
            resp.getWriter().print(JsonValue);

        }

    }


    @RequestMapping("/modifyCls")
    public void modifyCls(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String merchantid = req.getParameter("shopid");
        String goodsid = req.getParameter("goodsid");
        String clsType = req.getParameter("clsType");

        if (storeService.modifyGoodsCls(merchantid,goodsid,clsType)){
            resp.getWriter().print(0);
        }else {
            resp.getWriter().print(1);
        }
    }

    //classifyGoodsByType
    @RequestMapping("/classifyGoodsByType")
    public void classifyGoodsByType(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String merchantid = req.getParameter("shopid");
        String clsType = req.getParameter("clsType");

        List<GoodsBase> storeList = storeService.getStoreByClsType(merchantid,clsType);

        if (null == storeList){
            resp.getWriter().print(2);
            return;
        }else {
            resp.setContentType("text/html;charset=UTF-8");
            String JsonValue = JSONObject.toJSONString(storeList);
            resp.getWriter().print(JsonValue);

        }
    }


    //storeFindByName
    @RequestMapping("/storeFindByName")
    public void storeFindByName(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String merchantid = req.getParameter("shopid");
        String goodsName = req.getParameter("goodsName");

        List<GoodsBase> storeList = storeService.getStoreByGoodsName(merchantid,goodsName);

        if (null == storeList){
            resp.getWriter().print(3);
            return;
        }else {
            resp.setContentType("text/html;charset=UTF-8");
            String JsonValue = JSONObject.toJSONString(storeList);
            resp.getWriter().print(JsonValue);
        }
    }


    //searchGoodsById
    @RequestMapping("/searchGoodsById")
    public void searchGoodsById(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String merchantid = req.getParameter("shopid");
        String goodsid = req.getParameter("goodsid");

        GoodsBase goodsInfo = storeService.getStoreByGoodsId(merchantid,goodsid);

        if (null == goodsInfo){
            resp.getWriter().print(4);
            return;
        }else {
            resp.setContentType("text/html;charset=UTF-8");
            String JsonValue = JSONObject.toJSONString(goodsInfo);
            resp.getWriter().print(JsonValue);
        }
    }

    //addSingleStore
    @RequestMapping("/addSingleStore")
    public void addSingleStore(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String merchantid = req.getParameter("shopid");
        String goodsid = req.getParameter("goodsid");
        String goodsname = req.getParameter("goodsname");
        String goodscount = req.getParameter("goodscount");
        String goodsprice = req.getParameter("goodsprice");
        String goodsbrand = req.getParameter("goodsbrand");
        String goodsstander = req.getParameter("goodsstander");

        //若商品已经存在则返回2
        if (storeService.checkStoreExist(merchantid,goodsid)){
            resp.getWriter().print(2);
            return;
        }
        GoodsBase goodsInfo = new GoodsBase(goodsid,goodsname,goodsprice,goodscount,
                String.valueOf(Float.valueOf(goodsprice).intValue()/10));
        goodsInfo.setGoodsClassify(goodsbrand);
        goodsInfo.setGoodsStander(goodsstander);
        if (storeService.addStoreSingle(merchantid,goodsInfo)){
            resp.getWriter().print(3);
            return;
        }else {
            resp.getWriter().print(1);
            return;
        }

    }


    //conSingleStore
    @RequestMapping("/conSingleStore")
    public void conSingleStore(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String merchantid = req.getParameter("shopid");
        String goodsid = req.getParameter("goodsid");
        String goodscount = req.getParameter("goodscount");

        //若商品不存在则返回2
        if (!storeService.checkStoreExist(merchantid,goodsid)){
            resp.getWriter().print(2);
            return;
        }


        if (storeService.conStoreSingle(merchantid,goodsid,goodscount)){
            resp.getWriter().print(3);
            return;
        }else {
            resp.getWriter().print(1);
            return;
        }

    }


    //addSingleStore
    @RequestMapping("/batchStoreAdd")
    public void batchStoreAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String merchantid = req.getParameter("shopid");
        String goodsListLine = req.getParameter("str");
        List<String> goodsListarr = JSONArray.parseArray(goodsListLine,String.class);

        List<GoodsBase> goodsAlreadyInUse = new ArrayList<>();
        List<GoodsBase> goodslist = new ArrayList<>();

        goodslist = storeService.getBatchGoodsListWithBatchLine(goodsListarr);
        //若商品已经存在则返回2
        //批量导入商品，若有商品已经被添加过了，则返回已被添加过的商品名称，这个实现在service层实现；

        goodsAlreadyInUse = storeService.batchAddStore(merchantid,goodslist);

        if (goodsAlreadyInUse.size() == 0){
            resp.getWriter().print(1);
            return;
        }else {

            //若商品有重复记录，返回被重复的商品
            resp.setContentType("text/html;charset=UTF-8");
            String JsonValue = JSONObject.toJSONString(goodsAlreadyInUse);
            resp.getWriter().print(JsonValue);
            return;
        }



    }


}
