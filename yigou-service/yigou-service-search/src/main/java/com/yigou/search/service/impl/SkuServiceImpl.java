/**
 * -*- encoding: utf-8 -*-
 *
 * @Time : 2020/12/15 23:21
 * @Author : mike.liu
 * @File : SkuServiceImpl.java
 **/

package com.yigou.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.yigou.goods.feign.SkuFeign;
import com.yigou.goods.pojo.Sku;
import com.yigou.pojo.SkuInfo;
import com.yigou.search.dao.SkuEsMapper;
import com.yigou.search.service.SkuService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private SkuEsMapper skuEsMapper;

    /**
     * 导入sku数据到es
     */
    @Override
    public void importSku(){
        //调用changgou-service-goods微服务
        Result<List<Sku>> skuListResult = skuFeign.findByStatus("1");
        //将数据转成search.Sku
        List<SkuInfo> skuInfos=  JSON.parseArray(JSON.toJSONString(skuListResult.getData()),SkuInfo.class);
        for(SkuInfo skuInfo:skuInfos){
            Map<String, Object> specMap= JSON.parseObject(skuInfo.getSpec()) ;
            skuInfo.setSpecMap(specMap);
        }
        skuEsMapper.saveAll(skuInfos);
    }
}
