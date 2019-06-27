package com.youga.silver.obj;

public class MemberInfo {


    public static String MEMBER_LEVEL_1 = "青铜";
    public static String MEMBER_LEVEL_2 = "白银";
    public static String MEMBER_LEVEL_3 = "黄金";
    public static String MEMBER_LEVEL_4 = "白金";
    public static String MEMBER_LEVEL_5 = "钻石";
    public static String MEMBER_LEVEL_6 = "王者";


    public String memberID = null ;
    public String memberName = null;
    public String memberLevel = null ;
    //积分
    public String integral = null ;
    public String memberFlag = null ;
    public String msisdn = null ;

    //累计消费金额
    public String amount = null;
    public String balance = null;
    public String serviceOff = null;
    public String goodsOff = null;

    //2018-12-28 新增petName字段适配
    public String petName = null;

    //2018-12-18 新增字段level_id适配新改动
    public String memberLevelId = null;

    //2018-12-18 新增hisAmount历史充值记录字段
    public String hisAmount = null;

    //2019-06-02 新增用户id字段
    public String identity = null;

    public MemberInfo(String memberID, String memberName,String memberLevel, String integral, String memberFlag, String msisdn,  String amount, String balance,String serviceOff,String goodsOff) {
        this.memberID = memberID;
        this.memberName = memberName;
        this.memberLevel = checkMemberLeval(memberLevel);
        this.memberLevelId = memberLevel;
        this.integral = integral;
        this.memberFlag = memberFlag;
        this.msisdn = msisdn;
        this.amount = amount;
        this.balance = balance;
        this.serviceOff = serviceOff;
        this.goodsOff = goodsOff;
    }

    public MemberInfo(String memberID, String memberName,String memberLevel, String integral, String memberFlag, String msisdn,  String amount, String balance) {
        this.memberID = memberID;
        this.memberName = memberName;
        this.memberLevel = checkMemberLeval(memberLevel);
        this.memberLevelId = memberLevel;
        this.integral = integral;
        this.memberFlag = memberFlag;
        this.msisdn = msisdn;
        this.amount = amount;
        this.balance = balance;
    }

    public void setDefaultOff(String memberLevel)
    {
        if(memberLevel.equals("1"))
        {
            setServiceOff("0.95");
            setGoodsOff("1");

        }else if(memberLevel.equals("2"))
        {
            setServiceOff("0.9");
            setGoodsOff("0.97");

        }else if(memberLevel.equals("3"))
        {
            setServiceOff("0.85");
            setGoodsOff("0.92");

        }else if(memberLevel.equals("4"))
        {
            setServiceOff("0.8");
            setGoodsOff("0.87");

        }else if(memberLevel.equals("5"))
        {
            setServiceOff("0.75");
            setGoodsOff("0.83");

        }else if(memberLevel.equals("6"))
        {
            setServiceOff("0.7");
            setGoodsOff("0.8");

        }

    }

    private String checkMemberLeval(String memberLevel) {

        if(memberLevel.equals("1"))
        {
            return MEMBER_LEVEL_1;
        }else if(memberLevel.equals("2"))
        {

            return MEMBER_LEVEL_2;
        }else if(memberLevel.equals("3"))
        {

            return MEMBER_LEVEL_3;
        }else if(memberLevel.equals("4"))
        {

            return MEMBER_LEVEL_4;
        }else if(memberLevel.equals("5"))
        {
            return MEMBER_LEVEL_5;
        }else if(memberLevel.equals("6"))
        {

            return MEMBER_LEVEL_6;
        }else
            {
                return "Error";
            }

    }

    public MemberInfo(){}

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getServiceOff() {
        return serviceOff;
    }

    public void setServiceOff(String serviceOff) {
        this.serviceOff = serviceOff;
    }

    public String getGoodsOff() {
        return goodsOff;
    }

    public void setGoodsOff(String goodsOff) {
        this.goodsOff = goodsOff;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getMemberFlag() {
        return memberFlag;
    }

    public void setMemberFlag(String memberFlag) {
        this.memberFlag = memberFlag;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getMemberLevel(String firstCharge) {

        if(Integer.valueOf(firstCharge)<1000)
        {
            //青铜
            return "1";
        }else if(Integer.valueOf(firstCharge)<2000 && Integer.valueOf(firstCharge)>=1000)
        {
            //白银
            return "2";
        }else if(Integer.valueOf(firstCharge)<3000 && Integer.valueOf(firstCharge)>=2000)
        {
            //黄金
            return "3";
        }else if(Integer.valueOf(firstCharge)<5000 && Integer.valueOf(firstCharge)>=3000)
        {
            //白金
            return "4";
        }else if(Integer.valueOf(firstCharge)<8000 && Integer.valueOf(firstCharge)>=5000)
        {
            //钻石
            return "5";
        }else if(Integer.valueOf(firstCharge)<12000 && Integer.valueOf(firstCharge)>=8000)
        {
            //王者
            return "6";
        }else
        {
            return "Error";
        }


    }

    public String getFirstInter(String firstCharge) {
        return String.valueOf(Integer.valueOf(firstCharge)/10);
    }

    public String getMemberLevelId() {
        return memberLevelId;
    }

    public void setMemberLevelId(String memberLevelId) {
        this.memberLevelId = memberLevelId;
    }

    public String getHisAmount() {
        return hisAmount;
    }

    public void setHisAmount(String hisAmount) {
        this.hisAmount = hisAmount;
    }

    public void calcuCharge(String chargeAmount) {
        //合计充值金额变更
        int allCharge = Integer.valueOf(this.getHisAmount())+Integer.valueOf(chargeAmount);
        this.setHisAmount(String .valueOf(allCharge));
        //段位变更
        String level = this.getMemberLevel(String.valueOf(allCharge));
        this.setMemberLevelId(level);
        //积分变更
        int chargeInter = Integer.valueOf(chargeAmount)/10;
        //优化写法
        int allInter = Float.valueOf(this.getIntegral()).intValue()+chargeInter;
        //int allInter = Integer.valueOf(this.getIntegral())+chargeInter;
        this.setIntegral(String.valueOf(allInter));
        //余额变更
        float newBalance = Float.valueOf(this.getBalance())+Float.valueOf(chargeAmount);
        this.setBalance(String.valueOf(String.format("%.2f", newBalance)));
        //上次充值金额变更
        this.setAmount(chargeAmount);
        /**
         * 计算段位折扣率
         * 1-----0~1000
         * 2-----1000~2000
         * 3-----2000~3000
         * 4-----3000~5000
         * 5-----5000~8000
         * 6-----8000~10000
         */

        if(level.equals("1"))
        {
            if(Float.valueOf(this.getServiceOff()) > 0.95 )
            {
                this.setServiceOff("0.95");
            }

            if(Float.valueOf(this.getGoodsOff()) > 1.00 )
            {
                this.setGoodsOff("1.00");
            }
        }else if(level.equals("2"))
        {
            if(Float.valueOf(this.getServiceOff()) > 0.90 )
            {
                this.setServiceOff("0.90");
            }

            if(Float.valueOf(this.getGoodsOff()) > 0.97 )
            {
                this.setGoodsOff("0.97");
            }

        }else if(level.equals("3"))
        {
            if(Float.valueOf(this.getServiceOff()) > 0.85 )
            {
                this.setServiceOff("0.85");
            }

            if(Float.valueOf(this.getGoodsOff()) > 0.92 )
            {
                this.setGoodsOff("0.92");
            }

        }else if(level.equals("4"))
        {
            if(Float.valueOf(this.getServiceOff()) > 0.8 )
            {
                this.setServiceOff("0.8");
            }
            if(Float.valueOf(this.getGoodsOff()) > 0.87 )
            {
                this.setGoodsOff("0.87");
            }

        }else if(level.equals("5"))
        {
            if(Float.valueOf(this.getServiceOff()) > 0.75 )
            {
                this.setServiceOff("0.75");
            }
            if(Float.valueOf(this.getGoodsOff()) > 0.83 )
            {
                this.setGoodsOff("0.83");
            }

        }else if(level.equals("6"))
        {
            if(Float.valueOf(this.getServiceOff()) > 0.70 )
            {
                this.setServiceOff("0.70");
            }
            if(Float.valueOf(this.getGoodsOff()) > 0.80 )
            {
                this.setGoodsOff("0.80");
            }

        }

    }
}
