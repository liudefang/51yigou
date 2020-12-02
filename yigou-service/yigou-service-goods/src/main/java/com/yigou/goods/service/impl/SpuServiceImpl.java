package com.yigou.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.yigou.goods.dao.BrandMapper;
import com.yigou.goods.dao.CategoryMapper;
import com.yigou.goods.dao.SkuMapper;
import com.yigou.goods.dao.SpuMapper;
import com.yigou.goods.pojo.*;
import com.yigou.goods.service.SpuService;
import entity.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Time    : 2020/11/17 4:38 下午
 * Author  : mike.liu
 * File    : SpuServiceImpl.java
 */
@Service
public class SpuServiceImpl implements SpuService {
    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Override
    public PageInfo<Spu> findPage(Spu spu, int page, int size) {
        return null;
    }

    @Override
    public PageInfo<Spu> findPage(int page, int size) {
        return null;
    }

    @Override
    public List<Spu> findList(Spu spu) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Spu spu) {

    }

    @Override
    public void add(Spu spu) {

    }

    @Override
    public Spu findById(Long id) {
        return null;
    }

    @Override
    public List<Spu> findAll() {
        return null;
    }

    /***
     * 保存Goods
     * @param goods
     */
    @Override
    public void saveGoods(Goods goods) {
        //增加Spu
        Spu spu = goods.getSpu();
        if(spu.getId()==null){
            spu.setId(idWorker.nextId());
            spuMapper.insertSelective(spu);
        }else {
            //修改数据
            spuMapper.updateByPrimaryKeySelective(spu);
            //删除该spu的sku
            Sku sku = new Sku();
            sku.setSpuId(spu.getId());
            skuMapper.delete(sku);
        }


        //增加Sku
        Date date = new Date();
        Category category = categoryMapper.selectByPrimaryKey(spu.getCategory3Id());
        Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());
        //获取Sku集合
        List<Sku> skuList = goods.getSkuList();
        //循环将数据加入到数据库
        for (Sku sku : skuList) {
            //构建SKU名称，采用SPU+规格值组装
            if (StringUtils.isEmpty(sku.getSpec())) {
                sku.setSpec("{}");
            }
            //获取Spu的名字
            String name = spu.getName();

            //将规格转换成Map
            Map<String, String> specMap = JSON.parseObject(sku.getSpec(), Map.class);
            //循环组装Sku的名字
            for (Map.Entry<String, String> entry : specMap.entrySet()) {
                name += "  " + entry.getValue();
            }
            sku.setName(name);
            //ID
            sku.setId(idWorker.nextId());
            //SpuId
            sku.setSpuId(spu.getId());
            //创建日期
            sku.setCreateTime(date);
            //修改日期
            sku.setUpdateTime(date);
            //商品分类ID
            sku.setCategoryId(spu.getCategory3Id());
            //分类名字
            sku.setCategoryName(category.getName());
            //品牌名字
            sku.setBrandName(brand.getName());
            //增加
            skuMapper.insertSelective(sku);
        }
    }

    @Override
    public Goods findGoodsById(Long spuId) {
        //查找Spu
        Spu spu = spuMapper.selectByPrimaryKey(spuId);

        //查询List<Sku>
        Sku sku = new Sku();
        sku.setSpuId(spuId);
        List<Sku> skus = skuMapper.select(sku);
        //封装Goods
        Goods goods = new Goods();
        goods.setSkuList(skus);
        goods.setSpu(spu);
        return goods;
    }

    /***
     * 商品审核
     * @param spuId
     */
    @Override
    public void auditSpu(Long spuId) {
        //查询商品
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        //判断商品是否已经删除
        if(spu.getIsDelete().equalsIgnoreCase("1")){
            throw new RuntimeException("该商品已经删除!");
        }
        //实现上架和审核
        spu.setStatus("1");     //审核通过
        spu.setIsMarketable("1");   //上架
        spuMapper.updateByPrimaryKeySelective(spu);
        

    }

    /***
     * 商品下架
     * @param spuId
     */

    @Override
    public void pullSpu(Long spuId) {
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        if(spu.getIsDelete().equals("1")){
            throw new RuntimeException("此商品已经删除!");
        }
        spu.setIsMarketable("0");   //下架状态
        spuMapper.updateByPrimaryKeySelective(spu);

    }

    @Override
    public void logicDeleteSpu(Long id) {

    }

    @Override
    public void restoreSpu(Long id) {

    }
}
