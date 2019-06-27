package com.youga.silver.service;

import com.youga.silver.obj.MerchantInfo;

public interface MerchantService {
    /***
     * this method for check shopId is correct;
     * correct :true;
     * not : false;
     * @param shopid
     * @return
     */
    boolean checkShopid(String shopid);

    /***
     * get merchant by shop id
     * @param shopid
     * @return
     */
    MerchantInfo getMerchantInfoById(String shopid);
}
