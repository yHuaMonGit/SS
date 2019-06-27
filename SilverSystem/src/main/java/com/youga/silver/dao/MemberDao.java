package com.youga.silver.dao;


import com.youga.silver.obj.MemberInfo;

public interface MemberDao {


    /***
     * 插入会员信息
     */
    public String insertMemberInfo(MemberInfo memberInfo);

    /***
     * 2018-12-28 新增充值记录插入,当会员进行充值时，要插入充值记录;
     */

    public void insertChargeInfo(MemberInfo memberInfo);

    /***
     * 查询会员信息
     */
    public MemberInfo getMemberInfo(String msisdn);

    /***
     * 会员信息变更(手机号变更)
     */
    public void updateMemberInfo(MemberInfo memberInfo);

    /***
     * 2018-12-28
     * 新增变更 在会员充值时进行的会员信息更新，需要伴随充值记录插入
     */
    /***
     * 会员信息变更(手机号变更)
     */
    public void chargeMemberInfo(MemberInfo memberInfo);

    /**
     * 查询是否注册会员
     * @param openid
     * @return
     */
    public String isMemberExist(String openid);


    public String getLastId();

    /***
     * this method for synchronized the offline information to online's DB
     * @param member
     */
    void synchronizeOnlineMember(MemberInfo member);

    /***
     * this method is check weather is online member exist;
     * exist:true;
     * not: false;
     * @return
     * @param msisdn
     */
    boolean isOnlineMember(String msisdn);
}
