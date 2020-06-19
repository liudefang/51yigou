package com.yigou.goods.service;


import com.github.pagehelper.PageInfo;
import com.yigou.goods.pojo.Brand;

import java.util.List;

public interface BrandService {
    /***
     * 查询所有品牌
     * @return
     */

    List<Brand> findAll();

    //根据ID查询
    Brand findById(Integer id);

    /***
     * 新增品牌
     * @param brand
     */
    void add(Brand brand);

    /***
     * 修改品牌数据
     */
    void update(Brand brand);

    /**
     * 删除品牌
     * @param id
     */
    void delete(Integer id);

    /**
     * 多条件搜索品牌方法
     * @param brand
     * @return
     */
    List<Brand> findList(Brand brand);
    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Brand> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param brand
     * @param page
     * @param size
     * @return
     */
    PageInfo<Brand> findPage(Brand brand, int page, int size);
}
