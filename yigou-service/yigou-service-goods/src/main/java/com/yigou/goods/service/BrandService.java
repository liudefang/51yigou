package com.yigou.goods.service;


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
}
