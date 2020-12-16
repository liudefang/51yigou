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
import io.netty.util.internal.StringUtil;
import org.bouncycastle.math.raw.Nat;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private ElasticsearchTemplate esTemplate;
    public Map search(Map<String, String> searchMap){
        //1.获取关键字的值
        String keywords = searchMap.get("keywords");
        if (StringUtils.isEmpty(keywords)){
            keywords = "华为";    //赋值给一个默认值
        }
        //2.创建查询对象的构建对象
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //3.设置查询的条件
        //设置分组条件  商品分类
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuCategorygroup").field("categoryName").size(50));
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("name",keywords));
        //4.构建查询对象
        NativeSearchQuery query = nativeSearchQueryBuilder.build();
        //5.执行查询
        AggregatedPage<SkuInfo> skuPage = esTemplate.queryForPage(query, SkuInfo.class);

        //获取分组结果
        StringTerms stringTermsCategory = (StringTerms) skuPage.getAggregation("skuCategorygroup");

        List<String> categoryList =getStringsCategoryList(stringTermsCategory);
        //6.返回结果
        Map resultMap = new HashMap<>();
        resultMap.put("categoryList", categoryList);
        resultMap.put("rows", skuPage.getContent());
        resultMap.put("total", skuPage.getTotalElements());
        resultMap.put("totalPages", skuPage.getTotalPages());
        return resultMap;
    }
    private List<String> getStringsCategoryList(StringTerms stringTerms) {
        List<String> categoryList = new ArrayList<>();
        if (stringTerms != null) {
            for (StringTerms.Bucket bucket : stringTerms.getBuckets()) {
                String keyAsString = bucket.getKeyAsString();//分组的值
                categoryList.add(keyAsString);
            }
        }
        return categoryList
}
