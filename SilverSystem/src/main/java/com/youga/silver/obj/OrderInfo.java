package com.youga.silver.obj;

import java.util.ArrayList;
import java.util.List;

public class OrderInfo {

    public String OrderId = null;
    public String openid = null;
    public String msisdn = null;
    public String moneyAmount = "0.0";
    public List<GoodsBase> goodslist = null;

    //2018-12-21 订单新增服务类型
    public List<ServiceBase> serviceBaseList = null;

    public String paymentFlg = null;
    public String endingFlg = null;

    //2018-11-03订单新增地址ID接口
    public String addressid = null;

    //2018-11-05新增两个字段用于订单界面显示
    public String allGoodsNum = null;
    public String orderTime = null;

    //2018-12-10新增会员支付金额接口
    public String memberAmount = "0.0";
    public String memberServiceAmount = "0.0";
    public String memberGoodsAmount = "0.0";

    //2018-12-10新增服务与商品项目分开
    public String serviceAmount = "0.0";
    public String goodsAmount = "0.0";

    //2018-12-11 新增活动项目减幅
    public String activeType = null;
    public String activeOff = null;
    public String activeAmount = null;

    //2018-12-11 新增member信息
    public String memberID=null;
    public String memberFlag=null;
    public String memberLevel=null;

    //2018-12-11 新增member积分
    public String Intergral = null;


    //2018-12-29 新增订单可改价接口
    //新增订单授权减价：
    public String decAmount = null;
    //新增授权人：
    public String decAuthor = null;
    //新增备注项目-对应数据库字段goodsRemarks
    public String note = null;

    public String getAddressid() {
        return addressid;
    }

    public void setAddressid(String addressid) {
        this.addressid = addressid;
    }

    public OrderInfo(String orderId, String openid, String msisdn, String moneyAmount, List<GoodsBase> goodslist, String paymentFlg, String endingFlg) {
        this.OrderId = orderId;
        this.openid = openid;
        this.msisdn = msisdn;
        this.moneyAmount = moneyAmount;
        this.goodslist = goodslist;
        this.paymentFlg = paymentFlg;
        this.endingFlg = endingFlg;
    }

    public OrderInfo() {

    }

    public String getAllGoodsNum() {
        return allGoodsNum;
    }

    public void setMoneyAmount(){
        if(this.goodslist.size() == 0 && this.serviceBaseList.isEmpty())
        {
            this.moneyAmount = "0";
        }
        else
        {
            float numCount = 0f;
            float serviceCount = 0f;
            float goodsCount = 0f;

            if(!serviceBaseList.isEmpty())
            {
                for(ServiceBase service: serviceBaseList)
                {

                    serviceCount+=Float.valueOf(service.getServicePrice())*Integer.valueOf(service.getStander());

                }
            }

            if (!goodslist.isEmpty())
            {
                for (GoodsBase goods: goodslist)
                {

                    //calculate sum of goods
                    goodsCount+=Float.valueOf(goods.getGoodsPrice())*Integer.valueOf(goods.getGoodsCount());

                    //numCount+=Float.valueOf(goods.getGoodsPrice())*Integer.valueOf(goods.getGoodsCount());
                }
            }

            numCount = serviceCount+goodsCount;

            this.moneyAmount = String.valueOf(numCount);
            this.serviceAmount = String.valueOf(serviceCount);
            this.goodsAmount = String.valueOf(goodsCount);
        }
    }

    public void setAllGoodsNum() {

        if(this.goodslist.size() == 0)
        {
            this.allGoodsNum = "0";
        }
        else
            {
                int numCount = 0;
                for (GoodsBase goods: goodslist)
                {
                    numCount+=Integer.valueOf(goods.getGoodsCount());
                }
                this.allGoodsNum = String.valueOf(numCount);
            }

    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(String moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public List<GoodsBase> getGoodslist() {
        return goodslist;
    }

    public void setGoodslist(List<GoodsBase> goodslist) {
        //this.goodslist = goodslist;

        //2018-12-21 在这里区分商品及服务数据,服务项目ID以yg-SN开头
        this.goodslist = new ArrayList<>();
        serviceBaseList = new ArrayList<>();

        for(GoodsBase goods : goodslist)
        {
            if(goods.getGoodsId().startsWith("yg-SN"))
            {
                ServiceBase sb = new ServiceBase();
                sb.setServiceId(goods.getGoodsId());
                sb.setServiceName(goods.getGoodsName());
                sb.setServicePrice(goods.getGoodsPrice());
                sb.setStander(goods.getGoodsCount());
                this.serviceBaseList.add(sb);
            }else{
                this.goodslist.add(goods);
            }
        }

    }

    //2018-12-21 新增servicelist的get方法


    public List<ServiceBase> getServiceBaseList() {
        return serviceBaseList;
    }

    public String getPaymentFlg() {
        return paymentFlg;
    }

    public void setPaymentFlg(String paymentFlg) {
        this.paymentFlg = paymentFlg;
    }

    public String getEndingFlg() {
        return endingFlg;
    }

    public void setEndingFlg(String endingFlg) {
        this.endingFlg = endingFlg;
    }

    public String getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(String serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public String getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(String goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public String getActiveType() {
        return activeType;
    }

    public void setActiveType(String activeType) {
        this.activeType = activeType;
    }

    public String getActiveOff() {
        return activeOff;
    }

    public void setActiveOff(String activeOff) {
        this.activeOff = activeOff;
    }

    public String getActiveAmount() {
        return activeAmount;
    }

    public void setActiveAmount(String activeAmount) {
        this.activeAmount = activeAmount;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getMemberFlag() {
        return memberFlag;
    }

    public void setMemberFlag(String memberFlag) {
        this.memberFlag = memberFlag;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getIntergral() {
        return Intergral;
    }

    public void setIntergral(String intergral) {
        Intergral = intergral;
    }

    public void setMemberAmount(MemberInfo member) {

        this.memberServiceAmount = String.valueOf(Float.valueOf(this.serviceAmount) * Float.valueOf(member.getServiceOff()) );
        this.memberServiceAmount = String.format("%1.2f",Float.valueOf(this.memberServiceAmount));
        this.memberGoodsAmount = String.valueOf(Float.valueOf(this.goodsAmount) * Float.valueOf(member.getGoodsOff()) );
        this.memberGoodsAmount = String.format("%1.2f",Float.valueOf(this.memberGoodsAmount));
        this.memberAmount = String.valueOf(Float.valueOf(this.memberGoodsAmount)+Float.valueOf(this.memberServiceAmount));

        this.memberAmount = String.format("%1.2f",Float.valueOf(this.memberAmount));

    }

    public void setActiveAmount(MemberInfo member)
    {
        if(null==member)
        {

            this.activeAmount = String.valueOf(Float.valueOf(this.moneyAmount)-Float.valueOf(this.activeOff));

        }else
            {
                this.activeAmount = String.valueOf(Float.valueOf(this.memberAmount)-Float.valueOf(this.activeOff));

            }
        //2018-12-30 新增若前台修改减少，则总额计算应当减少
        if(this.getDecAmount()!=""){
            this.activeAmount = String.valueOf(Float.valueOf(this.activeAmount)-Float.valueOf(this.decAmount));
        }
    }

    public void setMemberIntergral(MemberInfo member)
    {
        if(null == member)
        {
            this.Intergral = "0";
        }else
            {
                this.Intergral = String.valueOf(Float.valueOf(this.moneyAmount).intValue() / 10);
            }
    }

    public String getMemberOff()
    {
        if(this.memberAmount.equals("0.0"))
        {
            return "0";
        }
        else
            {
                return String.valueOf(Float.valueOf(this.moneyAmount)-Float.valueOf(this.memberAmount));
            }
    }

    public String getDecAmount() {
        return decAmount;
    }

    public void setDecAmount(String decAmount) {
        this.decAmount = decAmount;
    }

    public String getDecAuthor() {
        return decAuthor;
    }

    public void setDecAuthor(String decAuthor) {
        this.decAuthor = decAuthor;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getGoodslistToString() {

        String result="";
        for (GoodsBase s:this.goodslist){
            result +=s.getGoodsId()+",";
        }

        return result;

    }
}
