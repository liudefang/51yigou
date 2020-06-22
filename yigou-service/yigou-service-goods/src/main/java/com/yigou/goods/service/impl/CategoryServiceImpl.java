package com.yigou.goods.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yigou.goods.dao.CategoryMapper;
import com.yigou.goods.pojo.Category;
import com.yigou.goods.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    /**
     * Category条件+分页查询
     * @param category 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Category> findPage(Category category, int page, int size){
        // 分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(category);
        //进行搜索
        return new PageInfo<Category>(categoryMapper.selectByExample(example));
    }
    /**
     * Category分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Category> findPage(int page, int size){
        // 静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<Category>(categoryMapper.selectAll());
    }
    /**
     * Category条件查询
     * @param category
     * @return
     */
    @Override
    
}
