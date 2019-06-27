package com.youga.silver.dao;

import com.youga.silver.obj.GoodsBase;

import java.util.List;

public interface BillDao {

    public void insertBill(GoodsBase goodsBase);

    public List<GoodsBase> getBill();

    public void clearBill();

    public void deleteGoods(String goodsid);

    public GoodsBase getSingleGoods(String goodsId);

    public void updateBill(GoodsBase goods);

}
