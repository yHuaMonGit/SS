package com.youga.silver.dao.impl;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.youga.silver.dao.BaseDao;
import com.youga.silver.dao.GoodsDao;
import com.youga.silver.dao.OrderDao;
import com.youga.silver.obj.GoodsBase;
import com.youga.silver.obj.OrderInfo;
import com.youga.silver.obj.ServiceBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrderDaoImpl implements OrderDao {

    GoodsDao goodsDao = new GoodsDaoImpl();
    @Override
    public void insertOrder(OrderInfo orderInfo) {

        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String goodsList = "";
            String serviceList = "";

            if (!orderInfo.goodslist.isEmpty())
            {
                for(GoodsBase goods : orderInfo.goodslist)
                {
                    goodsList = goodsList+goods.getGoodsId()+",";
                }
            }

            if (!orderInfo.serviceBaseList.isEmpty())
            {
                for(ServiceBase service : orderInfo.serviceBaseList)
                {
                    serviceList = serviceList+service.getServiceId()+",";
                }
            }


            String sql;
            sql = "insert into youga_pet.offline_order_tobe_paid_info (orderId,orderOutTime,msisdn,memberID,memberLevel," +
                    "memberFlag,iniAmount,actAmount,activeOffType,activeOff,goodslist,serviceOff,goodsOff,servicelist,goodsRemarks,decAmount,decAuthor) " +
                    "value ( \"" +orderInfo.getOrderId()+"\""+
                    ",\"" +orderInfo.getOrderTime()+"\""+
                    ",\"" +orderInfo.getMsisdn()+"\""+
                    ",\"" +orderInfo.getMemberID()+"\""+
                    ",\"" +orderInfo.getMemberLevel()+"\""+
                    ",\"" +orderInfo.getMemberFlag()+"\""+
                    ",\"" +orderInfo.getMoneyAmount()+"\""+
                    ",\"" +orderInfo.getActiveAmount()+"\""+
                    ",\"" +orderInfo.getActiveType()+"\""+
                    ",\"" +orderInfo.getActiveOff()+
                    "\",\"" +goodsList+
                    "\",\"" +orderInfo.getServiceAmount()+
                    "\",\"" +orderInfo.getGoodsAmount()+
                    "\",\"" +serviceList+
                    "\",\"" +orderInfo.getNote()+
                    "\",\"" +orderInfo.getDecAmount()+
                    "\",\"" +orderInfo.getDecAuthor()+
                    "\");";

            PreparedStatement stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();
            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //插入商品-订单信息
        insertGoods(orderInfo);

        //2018-12-22 插入服务-订单信息
        insertService(orderInfo);

    }

    private void insertService(OrderInfo orderInfo) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "";

            if (orderInfo.serviceBaseList.isEmpty())
            {
                return;
            }

            for(ServiceBase service : orderInfo.serviceBaseList)
            {

                sql = "insert into youga_pet.offline_order_service_info (orderId,serviceId,serviceName,serviceNum) " +
                        "value ( \"" +orderInfo.getOrderId()+"\""+
                        ",\"" +service.getServiceId()+"\""+
                        ",\"" +service.getServiceName()+
                        "\",\"" +service.getStander()+ "\");";
                stmt  = conn.prepareStatement(sql);
                stmt.executeUpdate();
            }

            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void insertPaidOrder(OrderInfo orderInfo) {

        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String goodsList = "";
            for(GoodsBase goods : orderInfo.goodslist)
            {
                goodsList = goodsList+","+goods.getGoodsId();
            }
            String sql;
            sql = "insert into youga_pet.offline_order_end_paid_info (orderId,orderOutTime,msisdn,memberID,integral,memberLevel,memberFlag,iniAmount,actAmount,activeOffType,activeOff,goodslist,serviceOff,goodsOff) " +
                    "value ( \"" +orderInfo.getOrderId()+"\""+
                    ",\"" +orderInfo.getOrderTime()+"\""+
                    ",\"" +orderInfo.getMsisdn()+"\""+
                    ",\"" +orderInfo.getMemberID()+"\""+
                    ",\"" +orderInfo.getIntergral()+"\""+
                    ",\"" +orderInfo.getMemberLevel()+"\""+
                    ",\"" +orderInfo.getMemberFlag()+"\""+
                    ",\"" +orderInfo.getMoneyAmount()+"\""+
                    ",\"" +orderInfo.getActiveAmount()+"\""+
                    ",\"" +orderInfo.getActiveType()+"\""+
                    ",\"" +orderInfo.getActiveOff()+
                    "\",\"" +goodsList+
                    "\",\"" +orderInfo.getServiceAmount()+
                    "\",\"" +orderInfo.getGoodsAmount()+ "\");";

            PreparedStatement stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();
            BaseDao.closeAll(conn, stmt);

        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println("insert repeat");
        }catch(SQLException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void insertCloseOrder(OrderInfo orderInfo) {

        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String goodsList = "";
            for(GoodsBase goods : orderInfo.goodslist)
            {
                goodsList = goodsList+","+goods.getGoodsId();
            }
            String sql;
            sql = "insert into youga_pet.order_close_info (orderid,openid,msisdn,moneyAmount,goodslist,paymentflg,endingflag,addressid) " +
                    "value ( \"" +orderInfo.getOrderId()+"\""+
                    ",\"" +orderInfo.getOpenid()+"\""+
                    ",\"" +orderInfo.getMsisdn()+"\""+
                    ",\"" +orderInfo.getMoneyAmount()+
                    "\",\"" +goodsList+
                    "\",\"" +orderInfo.getPaymentFlg()+
                    "\",\"" +orderInfo.getEndingFlg()+
                    "\",\"" +orderInfo.getAddressid()+ "\");";

            PreparedStatement stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();
            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(OrderInfo orderInfo, int type) {
        Connection conn = null;
        String Tablename = "";

        if(type == 0)
        {
            Tablename = "youga_pet.order_Tobe_Paid";
        }else if ( type == 1)
        {
            Tablename = "youga_pet.order_payment_end_info";
        }

        try {
            conn = BaseDao.getConnection();
            String sql;
            sql = "delete from "+Tablename+" where orderid = " +"\"" +orderInfo.getOrderId()+ "\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();
            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public void insertGoods(OrderInfo orderInfo) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "";

            if (orderInfo.goodslist.isEmpty())
            {
                return;
            }

            for(GoodsBase goods : orderInfo.goodslist)
            {

                sql = "insert into youga_pet.offline_order_goodsList_info (orderId,goodsId,goodsName,goodsNum) " +
                        "value ( \"" +orderInfo.getOrderId()+"\""+
                        ",\"" +goods.getGoodsId()+"\""+
                        ",\"" +goods.getGoodsName()+
                        "\",\"" +goods.getGoodsCount()+ "\");";
                stmt  = conn.prepareStatement(sql);
                stmt.executeUpdate();
            }

            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderInfo> selectUnpaymentOrder(String openid) {
        Connection conn = null;
        List<OrderInfo> orderInfoList = new ArrayList<>();
        try {
            conn = BaseDao.getConnection();
            String sql = "select * from order_Tobe_Paid where openid = \""+openid+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {

                OrderInfo orderInfo=new OrderInfo(
                        rs.getString("orderid"),
                        rs.getString("openid"),
                        rs.getString("msisdn"),
                        rs.getString("moneyAmount"),
                        selectGoods(rs.getString("orderid")),
                        rs.getString("paymentflg"),
                        rs.getString("endingflag")
                );

                orderInfoList.add(orderInfo);
            }
            BaseDao.closeAll(conn, stmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderInfoList;
    }

    public List<GoodsBase> selectGoods(String orderid) {

        Connection conn = null;
        List<GoodsBase> goodsBaseList = new ArrayList<>();
        try {
            conn = BaseDao.getConnection();
            String sql = "select * from youga_pet.offline_order_goodsList_info where orderid = \""+orderid+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return goodsBaseList;
            }

            while(rs.next())
            {
                GoodsBase goodsBase = goodsDao.SelectGoods(rs.getString("goodsid"));
                goodsBase.setGoodsCount( rs.getString("goodsNum"));
                goodsBaseList.add(goodsBase);
            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsBaseList;
    }

    @Override
    public List<OrderInfo> selectUnEndingOrder(String openid) {
        Connection conn = null;
        List<OrderInfo> orderInfoList = new ArrayList<>();
        try {
            conn = BaseDao.getConnection();
            String sql = "select * from youga_pet.order_payment_end_info where openid = \""+openid+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {

                OrderInfo orderInfo=new OrderInfo(
                        rs.getString("orderid"),
                        rs.getString("openid"),
                        rs.getString("msisdn"),
                        rs.getString("moneyAmount"),
                        selectGoods(rs.getString("orderid")),
                        rs.getString("paymentflg"),
                        rs.getString("endingflag")
                );
                orderInfoList.add(orderInfo);
            }
            BaseDao.closeAll(conn, stmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderInfoList;
    }

    @Override
    public List<OrderInfo> selectCloseOrder(String openid) {
        Connection conn = null;
        List<OrderInfo> orderInfoList = new ArrayList<>();
        try {
            conn = BaseDao.getConnection();
            String sql = "select * from youga_pet.order_close_info where openid = \""+openid+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {

                OrderInfo orderInfo=new OrderInfo(
                        rs.getString("orderid"),
                        rs.getString("openid"),
                        rs.getString("msisdn"),
                        rs.getString("moneyAmount"),
                        selectGoods(rs.getString("orderid")),
                        rs.getString("paymentflg"),
                        rs.getString("endingflag")
                );
                orderInfoList.add(orderInfo);
            }
            BaseDao.closeAll(conn, stmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderInfoList;
    }

    @Override
    public OrderInfo selectSingleOrder(String orderid,int type) {
        Connection conn = null;
        String Tablename = "";
        OrderInfo orderInfo = null;

        if(type == 0)
        {
            Tablename = "youga_pet.order_Tobe_Paid";
        }else if ( type == 1)
        {
            Tablename = "youga_pet.order_payment_end_info";
        }else if ( type == 2)
        {
            Tablename = "youga_pet.order_close_info";
        }else
            {
                return new OrderInfo();
            }

        try {
            conn = BaseDao.getConnection();
            String sql = "select * from "+Tablename+" where orderid = \""+orderid+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return new OrderInfo();
            }

            while(rs.next())
            {
                orderInfo =new OrderInfo(
                        rs.getString("orderid"),
                        rs.getString("openid"),
                        rs.getString("msisdn"),
                        rs.getString("moneyAmount"),
                        selectGoods(rs.getString("orderid")),
                        rs.getString("paymentflg"),
                        rs.getString("endingflag")
                );
                orderInfo.setAddressid(rs.getString("addressid"));
            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderInfo;
    }


}
