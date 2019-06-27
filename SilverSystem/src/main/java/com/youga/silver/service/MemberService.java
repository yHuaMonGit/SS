package com.youga.silver.service;

import com.youga.silver.obj.MemberInfo;

import java.util.List;

public interface MemberService {
    /***
     * 2019-01-18 新增memberService方法 获取所有的门店会员信息
     * @param shopid
     * @return
     */
    List<MemberInfo> getAllMember(String shopid);

    /***
     * 2019-05-07 新增获取单个用户信息
     * @param msisdn
     * @return
     */
    MemberInfo getMemberInfoByMsisdn(String msisdn);

    /***
     * 2019-05-07 新增更新用户信息
     * @param member
     */
    void updateMemberInfo(MemberInfo member);
}
