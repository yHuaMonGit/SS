package com.youga.silver.dao.impl;

import com.youga.silver.dao.BaseDao;
import com.youga.silver.dao.BillDao;
import com.youga.silver.dao.GoodsDao;
import com.youga.silver.obj.GoodsBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDaoImpl implements BillDao {
    @Override
    public void insertBill(GoodsBase goodsBase) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = BaseDao.getConnection();
            String sql;


                sql = "insert into youga_pet.bill_list_yg000001 (goodsid,goodsname,goodsprice,goodsNum,goodsIntergral) " +
                        "value ( \"" +goodsBase.getGoodsId()+"\""+
                        ",\"" +goodsBase.getGoodsName()+"\""+
                        ",\"" +goodsBase.getGoodsPrice()+
                        "\",\"" +goodsBase.getGoodsCount()+
                        "\",\""+goodsBase.getGoodsIntergral()+ "\");";

                stmt = conn.prepareStatement(sql);
                int result = stmt.executeUpdate();


            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<GoodsBase> getBill() {

        List<GoodsBase> goodsBaseList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDao.getConnection();
            String sql = "select * from  youga_pet.bill_list_yg000001";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
//goodsid,goodsname,goodsprice,instock,stander,classify

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return goodsBaseList;
            }
            while(rs.next())
            {
                GoodsBase goodsBase = new GoodsBase();
                goodsBase.setGoodsId(rs.getString("goodsid"));
                goodsBase.setGoodsName(rs.getString("goodsname"));
                goodsBase.setGoodsPrice(rs.getString("goodsprice"));
                goodsBase.setGoodsIntergral(rs.getString("goodsIntergral"));
                goodsBase.setGoodsCount(rs.getString("goodsNum"));
                goodsBaseList.add(goodsBase);
            }


            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return goodsBaseList;
    }

    @Override
    public void clearBill() {
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql;
            sql = "delete from youga_pet.bill_list ;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();
            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteGoods(String goodsid) {
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql;
            sql = "delete from youga_pet.bill_list where goodsid = \""+goodsid+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();
            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GoodsBase getSingleGoods(String goodsId) {
        GoodsBase goodsBase = new GoodsBase();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDao.getConnection();
            String sql = "select * from  youga_pet.bill_list where goodsid = \""+goodsId+"\";";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return new GoodsBase();
            }
            while(rs.next())
            {
                goodsBase.setGoodsId(rs.getString("goodsid"));
                goodsBase.setGoodsName(rs.getString("goodsname"));
                goodsBase.setGoodsPrice(rs.getString("goodsprice"));
                goodsBase.setGoodsIntergral(rs.getString("goodsIntergral"));
                goodsBase.setGoodsCount(rs.getString("goodsNum"));
            }

            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return goodsBase;
    }

    @Override
    public void updateBill(GoodsBase goods) {
        String retrunCode = null;
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql;
            sql = "update youga_pet.bill_list set goodsNum = \""
                    +goods.getGoodsCount()+"\","+
                    "goodsIntergral = \"" +goods.getGoodsIntergral()+
                    "\" Where goodsid = \""+goods.getGoodsId()+"\";";

            PreparedStatement stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();

            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
