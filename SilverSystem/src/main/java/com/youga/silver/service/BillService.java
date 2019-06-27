package com.youga.silver.service;

import com.youga.silver.obj.GoodsBase;

import java.util.List;

public interface BillService {

    /***
     * 2019-05-05 优化会员中心读取速度慢的问题
     * 扫描操作发送请求获取
     * @param goodsid,merchantid
     * @return
     */
    List<GoodsBase> getBill(String goodsid,String merchantid);

    /***
     * 2019-05-06 针对订单操作也做一个优化处理
     * @param goodsid
     * @param merchantid
     * @param modify_type_add
     * @return
     */
    void modifyBill(String goodsid, String merchantid, int modify_type_add);

    /***
     * 新增清空bill list
     * @param shopid
     */
    void clearBill(String shopid);
}
