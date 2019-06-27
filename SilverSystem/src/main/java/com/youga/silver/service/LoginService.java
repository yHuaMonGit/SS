package com.youga.silver.service;

import com.youga.silver.obj.MerchantInfo;

public interface LoginService {
    /***
     * this method for check account passwd is legal
     * legal:true;
     * not:false;
     * @param acc
     * @param pass
     * @return
     */
    boolean checkAccount(String acc, String pass);

    /***
     * this method for get merchant Info;
     * @param acc
     * @return
     */
    MerchantInfo getMerchantInfo(String acc);
}
