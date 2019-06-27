package com.youga.silver.dao;

import com.youga.silver.obj.GoodsBase;

import java.util.List;

public interface GoodsDao  {

    /***
     * add goods record
     */
    public void AddGoodsRecord(List<String> goodslist);

    public GoodsBase SelectGoods(String goodsId);

    public String getGoodsStore(String goodsId);

    public void UpdateStore(GoodsBase goods);
}
