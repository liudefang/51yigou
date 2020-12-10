package com.yigou.goods.controller;

import com.yigou.goods.pojo.Goods;
import com.yigou.goods.service.SpuService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Time    : 2020/11/17 5:03 下午
 * Author  : mike.liu
 * File    : SpuController.java
 */
@RestController
@RequestMapping("/spu")
@CrossOrigin
public class SpuController {
    /***
     * 添加Goods
     * @param goods
     * @return
     */
    @Autowired SpuService spuService;

    @PostMapping("/save")
    public Result save(@RequestBody Goods goods){
        spuService.saveGoods(goods);
        return new Result(true, StatusCode.OK,"保存成功");
    }

    /***
     * 根据ID查询Goods
     * @param id
     * @return
     */
    @GetMapping("/goods/{id}")
    public Result<Goods> findGoodsById(@PathVariable Long id){
        //根据ID查询Goods(SPU+SKU)信息
        Goods goods = spuService.findGoodsById(id);
        return new Result<Goods>(true,StatusCode.OK,"查询成功",goods);
    }

    /***
     * 审核
     * @return
     */
    @PutMapping("/audit/{id}")
    public Result audit(@PathVariable Long id){
        spuService.auditSpu(id);
        return new Result(true,StatusCode.OK,"审核成功!");
    }
    /***
     * 下架
     * @param spuId
     * @return
     */
    @PutMapping("/pull/{spuId}")
    public Result pull(@PathVariable Long spuId){
        spuService.pullSpu(spuId);
        return new Result(true,StatusCode.OK,"下架成功!");
    }

    /**
     * 商品上架
     * @param  id
     * @return
     */
    @PutMapping("/put/{id}")
    public Result put(@PathVariable Long id){
        spuService.putSpu(id);
        return new Result(true, StatusCode.OK, "上架商品成功!");
    }

}
