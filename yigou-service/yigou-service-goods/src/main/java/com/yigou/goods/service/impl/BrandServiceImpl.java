package com.yigou.goods.service.impl;

import com.yigou.goods.dao.BrandMapper;
import com.yigou.goods.pojo.Brand;
import com.yigou.goods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 实现类
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 全部数据
     * @return
     */
    public List<Brand> findAll(){
        return brandMapper.selectAll();
    }

    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }
    /**
     * 增加品牌
     * @param brand
     */
    @Override
    public void add(Brand brand){
        brandMapper.insertSelective(brand);
    }
}
