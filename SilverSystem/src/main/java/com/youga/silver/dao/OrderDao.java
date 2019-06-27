package com.youga.silver.dao;




import com.youga.silver.obj.OrderInfo;

import java.util.List;

public interface OrderDao {


    /**
     * 插入订单记录
     */
    public void insertOrder(OrderInfo orderInfo);

    /**
     * 插入已付款订单记录
     */
    public void insertPaidOrder(OrderInfo orderInfo);

    /**
     * 插入已关闭订单记录
     */
    public void insertCloseOrder(OrderInfo orderInfo);

    /**
     * 删除订单记录
     */
    public void deleteOrder(OrderInfo orderInfo, int type);

    /**
     * 查询未付款订单（批量查询）
     */
    public List<OrderInfo> selectUnpaymentOrder(String openid);


    /**
     * 查询已付款待收货订单（批量查询）
     */
    public List<OrderInfo> selectUnEndingOrder(String openid);
    /**
     * 查询历史订单（批量查询）
     */
    public List<OrderInfo> selectCloseOrder(String openid);

    /**
     * 查询未付款订单（单订单查询）
     */
    public OrderInfo selectSingleOrder(String orderid, int Type);







}
