package com.youga.silver.dao.impl;

import com.youga.silver.dao.BaseDao;
import com.youga.silver.dao.GoodsDao;
import com.youga.silver.obj.GoodsBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GoodsDaoImpl implements GoodsDao {
    @Override
    public void AddGoodsRecord(List<String> goodslist) {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = BaseDao.getConnection();
            String sql;

            for(String str:goodslist)
            {
                String [] goods = str.split(",");
                String goodsid = goods[0];
                String goodsname = goods[1];
                String stander = goods[2];
                String goodsprice = goods[3];
                String instock = goods[4];
                String classify = goods[5];

                sql = "insert into youga_pet.goods_store_list_yg000001 (goodsid,goodsname,goodsprice,instock,stander,classify) " +
                        "value ( \"" +goodsid+"\""+
                        ",\"" +goodsname+"\""+
                        ",\"" +goodsprice+
                        "\",\"" +instock+
                        "\",\"" +stander+
                        "\",\""+classify+ "\");";

                stmt = conn.prepareStatement(sql);
                int result = stmt.executeUpdate();
            }

            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GoodsBase SelectGoods(String goodsId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        GoodsBase goodsBase = new GoodsBase();
        try {
            conn = BaseDao.getConnection();
            String sql = "select * from  youga_pet.goods_store_list_yg000001 where goodsid = \""+goodsId+"\";";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
//goodsid,goodsname,goodsprice,instock,stander,classify

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return new GoodsBase();
            }
            while(rs.next())
            {
                goodsBase.setGoodsId(goodsId);
                goodsBase.setGoodsName(rs.getString("goodsname"));
                goodsBase.setGoodsPrice(rs.getString("goodsprice"));
                int grat = Float.valueOf(goodsBase.getGoodsPrice()).intValue()/10;
                goodsBase.setGoodsIntergral(String.valueOf(grat));
                goodsBase.setGoodsStore(rs.getString("instock"));
                goodsBase.setGoodsCount("1");
            }
            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsBase;
    }

    @Override
    public String getGoodsStore(String goodsId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String goodsStore = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select * from  youga_pet.goods_store_list_yg000001 where goodsid = \""+goodsId+"\";";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
//goodsid,goodsname,goodsprice,instock,stander,classify

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return goodsStore;
            }
            while(rs.next())
            {

                goodsStore = rs.getString("instock");

            }
            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsStore;
    }

    @Override
    public void UpdateStore(GoodsBase goods) {
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql;
            sql = "update youga_pet.goods_store_list_yg000001 set instock = \""
                    +goods.getGoodsStore()+
                    "\" Where goodsid = \""+goods.getGoodsId()+"\";";

            PreparedStatement stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();
            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
