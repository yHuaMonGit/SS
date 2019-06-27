package com.youga.silver.service;

import com.youga.silver.obj.OrderInfo;

public interface OrderService {
    /***
     * 新增业务层插入新订单信息的方法
     * @param orderInfo
     * @param shopid
     */
    void insertOrderInfo(OrderInfo orderInfo, String shopid);

    /***
     * 查询是否有新增订单，订单新增一个字段标识是否检阅，
     * 当SS进入订单页面勾选确定检阅后，检阅标识刷新
     * @return
     */
    boolean checkNewOrder();
}
