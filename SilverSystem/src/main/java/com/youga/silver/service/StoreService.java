package com.youga.silver.service;

import com.youga.silver.obj.GoodsBase;

import java.util.List;

public interface StoreService {

    /***
     * this method for get all Store Info
     * @param merchantid
     * @return
     */
    List<GoodsBase> getAllStore(String merchantid);

    /***
     * this method for modify goodsType;
     * @param merchantid
     * @param goodsid
     * @param clsType
     */
    Boolean modifyGoodsCls(String merchantid, String goodsid, String clsType);

    /***
     * 2019-1-12 新增按照分类查询库存
     * @param merchantid
     * @param clsType
     * @return
     */
    List<GoodsBase> getStoreByClsType(String merchantid, String clsType);

    /***
     * 2019-01-12 按照商品名称模糊匹配查询商品
     * @param merchantid
     * @param goodsName
     * @return
     */
    List<GoodsBase> getStoreByGoodsName(String merchantid, String goodsName);

    /***
     * 2019-01-13 按照商品id返回商品
     * @param merchantid
     * @param goodsid
     * @return
     */
    GoodsBase getStoreByGoodsId(String merchantid, String goodsid);

    /***
     * 2019-01-14 检查商品是否存在
     *
     * @param merchantid
     * @param goodsid
     * @return
     */
    boolean checkStoreExist(String merchantid, String goodsid);

    /***
     * 2019-01-14 添加新商品
     * @param merchantid
     * @param goodsInfo
     * @return
     */
    boolean addStoreSingle(String merchantid, GoodsBase goodsInfo);

    /***
     * 2019-01-15 商品续货
     * @param merchantid
     * @param goodsid
     * @param goodscount
     * @return
     */
    boolean conStoreSingle(String merchantid, String goodsid, String goodscount);

    /***
     * 2019-01-18 根据批量序列获取商品list
     * @param goodsListLine
     * @return
     */
    List<GoodsBase> getBatchGoodsListWithBatchLine(List<String> goodsListLine);

    /***
     * 2019-01-18 批量添加商品
     * 返回已经存在的重复商品列表
     *
     * @param merchantid
     * @param goodslist
     * @return
     */
    List<GoodsBase> batchAddStore(String merchantid, List<GoodsBase> goodslist);
}
