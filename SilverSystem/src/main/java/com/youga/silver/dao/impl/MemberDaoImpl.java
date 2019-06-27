package com.youga.silver.dao.impl;


import com.youga.silver.dao.BaseDao;
import com.youga.silver.dao.MemberDao;
import com.youga.silver.obj.MemberInfo;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MemberDaoImpl implements MemberDao {


    @Override
    public String insertMemberInfo(MemberInfo memberInfo) {
        String retrunCode = null;
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql;
            sql = "insert into youga_pet.baking_member_info (memberID,username,msisdn,integral,memberFlag,memberLevel,Amount,balance,serviceOff,goodsOff,petName,hisAmount) " +
                    "value ( \"" +memberInfo.getMemberID()+"\""+
                    ",\"" +memberInfo.getMemberName()+
                    "\",\"" +memberInfo.getMsisdn()+
                    "\",\"" +memberInfo.getIntegral()+
                    "\",\"" +memberInfo.getMemberFlag()+
                    "\",\"" +memberInfo.getMemberLevelId()+
                    "\",\"" +memberInfo.getAmount()+
                    "\",\"" +memberInfo.getBalance()+
                    "\",\"" +memberInfo.getServiceOff()+
                    "\",\"" +memberInfo.getGoodsOff()+
                    "\",\"" +memberInfo.getPetName()+
                    "\",\"" +memberInfo.getHisAmount()+
                    "\");";

            PreparedStatement stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();

            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retrunCode;
    }

    @Override
    public void insertChargeInfo(MemberInfo memberInfo) {
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();

            String sql;
            sql = "insert into youga_pet.member_charge_list (memberID,chargeAmount,chargeDT) " +
                    "value ( \"" +memberInfo.getMemberID()+"\""+
                    ",\"" +memberInfo.getAmount()+
                    "\",\"" +getNowTime()+
                    "\");";
            PreparedStatement stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();
            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getNowTime() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return (df.format(new Date()));// new Date()为获取当前系统时间

    }

    @Override
    public MemberInfo getMemberInfo(String msisdn) {
        Connection conn = null;
        MemberInfo member = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select * from youga_pet.baking_member_info where msisdn = \""+msisdn+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return new MemberInfo();
            }

            while(rs.next())
            {
                member =new MemberInfo(
                        rs.getString("memberID"),
                        rs.getString("username"),
                        rs.getString("memberLevel"),
                        rs.getString("integral"),
                        rs.getString("memberFlag"),
                        rs.getString("msisdn"),
                        rs.getString("Amount"),
                        rs.getString("balance"),
                        rs.getString("serviceOff"),
                        rs.getString("goodsOff")
                );
                member.setPetName(rs.getString("petName"));
                member.setHisAmount(rs.getString("hisAmount"));
                String checkCode = rs.getString("idcard");
                if(null == checkCode || checkCode.length()<16){
                    member.setIdentity("未认证");
                }else
                {
                    checkCode = checkCode.substring(checkCode.length() - 6);
                    member.setIdentity(checkCode);
                }

            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public void updateMemberInfo(MemberInfo memberInfo) {

        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql;
            sql = "update youga_pet.baking_member_info set integral = \""
                    +memberInfo.getIntegral()+"\","+
                    "balance = \"" +memberInfo.getBalance()+
                    "\" Where memberID = \""+memberInfo.getMemberID()+"\";";

            PreparedStatement stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();

            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void chargeMemberInfo(MemberInfo memberInfo) {
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql;
            sql = "update youga_pet.baking_member_info set integral = \""
                    +memberInfo.getIntegral()+"\","+
                    "balance = \"" +memberInfo.getBalance()+
                    "\",memberLevel = \"" +memberInfo.getMemberLevelId()+
                    "\",Amount = \"" +memberInfo.getAmount()+
                    "\",hisAmount = \"" +memberInfo.getHisAmount()+
                    "\",serviceOff = \"" +memberInfo.getServiceOff()+
                    "\",goodsOff = \"" +memberInfo.getGoodsOff()+
                    "\" Where memberID = \""+memberInfo.getMemberID()+"\";";

            PreparedStatement stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();

            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String isMemberExist(String openid) {
        return null;
    }

    @Override
    public String getLastId() {
        Connection conn = null;
        String result = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select memberID from baking_member_info order by memberId desc limit 1;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return "";
            }

            while(rs.next())
            {
                result = rs.getString("memberID");

            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void synchronizeOnlineMember(MemberInfo member) {
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql;
            sql = "update youga_pet.member_info set balance = \""
                    +member.getBalance()+"\","+
                    "integral = \"" +member.getIntegral()+
                    "\",memberlevel = \"" +member.getMemberLevel()+
                    "\" Where msisdn = \""+member.getMsisdn()+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();
            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean isOnlineMember(String msisdn) {

        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select count(1) from youga_pet.member_info where msisdn = \"" + msisdn + "\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int rows = rs.getInt(1);
                if (rows > 0 && rows <= 1) {
                    return true;
                } else if (rows == 0) {
                    return false;
                } else if (rows > 1) {
                    return false;
                }
            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
