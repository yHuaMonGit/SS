package com.youga.silver.dao.impl;

import com.youga.silver.dao.BaseDao;
import com.youga.silver.dao.ServiceDao;
import com.youga.silver.obj.ServiceBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDaoImpl implements ServiceDao {
    @Override
    public List<ServiceBase> getServiceFromType(String ulType) {
        List<ServiceBase> serviceBaseList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDao.getConnection();
            String sql = "select * from  youga_pet.offline_service_list where family = \""+ulType+"\";";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
//goodsid,goodsname,goodsprice,instock,stander,classify

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return serviceBaseList;
            }
            while(rs.next())
            {
                ServiceBase serviceBase = new ServiceBase(
                        rs.getString("serviceId"),
                        rs.getString("serviceName"),
                        rs.getString("servicePrice"),
                        rs.getString("family")
                );
                serviceBaseList.add(serviceBase);
            }

            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serviceBaseList;
    }

    @Override
    public ServiceBase getSingleService(String serviceId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ServiceBase serviceBase = new ServiceBase();
        try {
            conn = BaseDao.getConnection();
            String sql = "select * from  youga_pet.offline_service_list where serviceId = \""+serviceId+"\";";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
//goodsid,goodsname,goodsprice,instock,stander,classify

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return new ServiceBase();
            }
            while(rs.next())
            {
                serviceBase = new ServiceBase(
                        rs.getString("serviceId"),
                        rs.getString("serviceName"),
                        rs.getString("servicePrice"),
                        rs.getString("family")
                );

            }
            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serviceBase;
    }
}
