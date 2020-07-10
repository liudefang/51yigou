//业务层实现类
package com.yigou.goods.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yigou.goods.dao.SpecMapper;
import com.yigou.goods.dao.TemplateMapper;
import com.yigou.goods.pojo.Spec;
import com.yigou.goods.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Time    : 2020/7/10 5:01 下午
 * Author  : mike.liu
 * File    : SpecServiceImpl.java
 */

@Service
public class SpecServiceImpl implements SpecService {
    @Autowired
    private SpecMapper specMapper;

    @Autowired
    private TemplateMapper templateMapper;

    /**
     * Spec条件+分页查询
     * @param spec 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */

    @Override
    public PageInfo<Spec> findPage(Spec spec, int page, int size) {
        //分页
        PageHelper.startPage(page, size);
        //搜索条件构建
        Example example = createExample(spec);
        //执行搜索
        return new PageInfo<Spec>(specMapper.selectByExample(example));
    }

    /**
     * Spec分页查询
     * @param page
     * @param size
     * @return
     */

    @Override
    public PageInfo<Spec> findPage(int page, int size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<Spec>(specMapper.selectAll());
    }

    /**
     * Spec条件查询
     * @param spec
     * @return
     */
    @Override
    public List<Spec> findList(Spec spec) {
        //构建查询条件
        Example example = createExample(spec);
        //根据构建的条件查询数据
        return specMapper.selectByExample(example);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Spec spec) {

    }

    @Override
    public void add(Spec spec) {

    }

    @Override
    public Spec findById(Integer id) {
        return null;
    }

    @Override
    public List<Spec> findAll() {
        return null;
    }

    /**
     * Spec构建查询对象
     * @param spec
     * @return
     */

    private Example createExample(Spec spec) {
        Example example = new Example(Spec.class);
        Example.Criteria criteria = example.createCriteria();
        if(spec !=null){
            //ID
                if(!StringUtils.isEmpty(spec.getId())){
                    criteria.andEqualTo("id",spec.getId());
                }
                // 名称
                if(!StringUtils.isEmpty(spec.getName())){
                    criteria.andLike("name","%"+spec.getName()+"%");
                }
                // 规格选项
                if(!StringUtils.isEmpty(spec.getOptions())){
                    criteria.andEqualTo("options",spec.getOptions());
                }
                // 排序
                if(!StringUtils.isEmpty(spec.getSeq())){
                    criteria.andEqualTo("seq",spec.getSeq());
                }
                // 模板ID
                if(!StringUtils.isEmpty(spec.getTemplateId())){
                    criteria.andEqualTo("templateId",spec.getTemplateId());
                }
        }
        return example;

    }
}
