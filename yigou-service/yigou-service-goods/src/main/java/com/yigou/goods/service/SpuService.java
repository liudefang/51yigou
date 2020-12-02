package com.yigou.goods.service;
import com.yigou.goods.pojo.Goods;
import com.yigou.goods.pojo.Spu;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:
 * @Description:Spu业务层接口
 * @Date
 *****/
public interface SpuService {

    /***
     * Spu多条件分页查询
     * @param spu
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(Spu spu, int page, int size);

    /***
     * Spu分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(int page, int size);

    /***
     * Spu多条件搜索方法
     * @param spu
     * @return
     */
    List<Spu> findList(Spu spu);

    /***
     * 删除Spu
     * @param id
     */
    void delete(Long id);

    /***
     * 修改Spu数据
     * @param spu
     */
    void update(Spu spu);

    /***
     * 新增Spu
     * @param spu
     */
    void add(Spu spu);

    /**
     * 根据ID查询Spu
     * @param id
     * @return
     */
     Spu findById(Long id);

    /***
     * 查询所有Spu
     * @return
     */
    List<Spu> findAll();

    /**
     * 添加商品(SPU+ SKUlIST)
     * @param goods   update  add
     */
    void saveGoods(Goods goods);

    Goods findGoodsById(Long spuId);

    /***
     * 商品审核
     * @param spuId
     */
    void auditSpu(Long spuId);

    /**
     * 下架
     * @param spuId
     */
    void pullSpu(Long spuId);

    void logicDeleteSpu(Long spuId);


    void restoreSpu(Long spuId);
}
