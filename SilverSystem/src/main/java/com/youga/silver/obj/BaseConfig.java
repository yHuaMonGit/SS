package com.youga.silver.obj;

public class BaseConfig {

   // String host = "localhost";
    String host = "39.100.36.179";


    public String getMCCAccVerifyUrl() {

        return "http://"+host+":8081/MerchantControlCenter/accVerify";
    }

    public String getMerchantInfoUrl() {

        return "http://"+host+":8081/MerchantControlCenter/getmerchant";

    }

    public String getCheckShopidUrl() {

        return "http://"+host+":8081/MerchantControlCenter/merchantIdVerify";

    }

    public String getAllStoreUrl() {

        return "http://"+host+":8081/MerchantControlCenter/getAllStore";

    }

    public String getmodifyGoodsClsUrl() {
        return "http://"+host+":8081/MerchantControlCenter/modifyGoodsCls";
    }

    public String getMerchantInfoById() {
        return "http://"+host+":8081/MerchantControlCenter/getMerchantById";
    }

    public String getStoreByClsTypeUrl() {
        return "http://"+host+":8081/MerchantControlCenter/getStoreByClsType";
    }

    public String getStoreByGoodsNameUrl() {
        return "http://"+host+":8081/MerchantControlCenter/getStoreByGoodsName";
    }

    public String getStoreByGoodsIdUrl() {
        return "http://"+host+":8081/MerchantControlCenter/getStoreByGoodsId";
    }

    public String getcheckStoreByGoodsIdUrl() {
        return "http://"+host+":8081/MerchantControlCenter/checkStoreByGoodsId";
    }

    public String getaddStoreByGoodsIdUrl() {
        return "http://"+host+":8081/MerchantControlCenter/addStoreByGoodsId";
    }

    public String getconStoreByGoodsIdUrl() {
        return "http://"+host+":8081/MerchantControlCenter/conStoreByGoodsId";
    }

    public String getAllMemberUrl() {
        return "http://"+host+":8081/MerchantControlCenter/getAllMember";
    }

    public String getBillUrl() {
        return "http://"+host+":8081/MerchantControlCenter/getBillById";
    }

    public String modifyBillByID() {
        return "http://"+host+":8081/MerchantControlCenter/modifyBillByID";
    }

    public String getMemberInfoByMsisdn() {
        return "http://"+host+":8081/MerchantControlCenter/getMemberInfoByMsisdn";
    }

    public String updateMemberInfo() {
        return "http://"+host+":8081/MerchantControlCenter/updateMemberInfo";
    }

    public String insertOrderInfo() {
        return "http://"+host+":8081/MerchantControlCenter/insertOrderInfo";
    }

    public String clearBill() {
        return "http://"+host+":8081/MerchantControlCenter/clearBill";
    }

    public String checkNewOrder() {
        return "http://"+host+":8081/MerchantControlCenter/checkNewOrder";
    }
}
