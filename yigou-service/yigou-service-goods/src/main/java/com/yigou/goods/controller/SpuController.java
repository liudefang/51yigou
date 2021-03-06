package com.yigou.goods.controller;

import com.github.pagehelper.PageInfo;
import com.yigou.goods.pojo.Goods;
import com.yigou.goods.pojo.Spu;
import com.yigou.goods.service.SpuService;
import entity.Page;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Time    : 2020/11/17 5:03 下午
 * Author  : mike.liu
 * File    : SpuController.java
 */
@Controller
@Api(tags = "SpuController",description = "商品管理")

@RequestMapping("/spu")
@CrossOrigin
public class SpuController {
    @Autowired
    private SpuService spuService;
    /**
     * Spu分页条件搜索实现
     * @param spu
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")

    public Result<PageInfo> findPage(@RequestBody(required = false) Spu spu, @PathVariable int page, @PathVariable int size){
        //调用SpuService实现条件查询Spu
        PageInfo<Spu> pageInfo = spuService.findPage(spu, page, size);
        return new Result<>(true,StatusCode.OK,"查询成功!",pageInfo);
    }
    /**
     * Spu分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size){
        //调用SpuService实现分页查询Spu
        PageInfo<Spu> pageInfo = spuService.findPage(page,size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功!", pageInfo);
    }
    /**
     * 多条件搜索品牌数据
     * @param spu
     * @return
     */
    @PostMapping(value = "/search")
    public Result<List<Spu>> findList(@RequestBody(required = false) Spu spu){
        //调用SpuService实现条件查询Spu
        List<Spu> list = spuService.findList(spu);
        return new Result<List<Spu>>(true, StatusCode.OK,"查询成功!",list);
    }
    /**
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id){
        //调用SpuService实现根据主键删除
        spuService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功!");
    }
    /**
     * 修改Spu数据
     * @param spu
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Spu spu,@PathVariable Long id){
        //设置主键
        spu.setId(id);
        //调用SpuService实现修改Spu
        spuService.update(spu);
        return new Result(true,StatusCode.OK,"修改成功!");
    }

    /**
     * 新增Spu数据
     * @param spu
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Spu spu){
        //调用SpuService实现添加Spu
        spuService.add(spu);
        return new Result(true,StatusCode.OK,"添加成功!");
    }
    /***
     * 添加Goods
     * @param goods
     * @return
     */

    @PostMapping("/save")
    public Result save(@RequestBody Goods goods){
        spuService.saveGoods(goods);
        return new Result(true, StatusCode.OK,"保存成功");
    }

    /**
     * 根据ID查询Spu数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Spu> findById(@PathVariable Long id){
        //调用SpuService实现根据主键查询Spu
        Spu spu = spuService.findById(id);
        return new Result<Spu>(true,StatusCode.OK,"查询成功!",spu);
    }
    /***
     * 查询Spu全部数据
     * @return
     */
    @GetMapping
    public Result<List<Spu>> findAll(){
        //调用SpuService实现查询所有Spu
        List<Spu> list = spuService.findAll();
        return new Result<List<Spu>>(true, StatusCode.OK,"查询成功",list) ;
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
    /**
     * 批量上架
     * @param ids
     * @return
     */
    @PutMapping("/put/many")
    public Result putMany(@RequestBody Long[] ids){
        int count = spuService.putMany(ids);
        return new Result(true, StatusCode.OK,"上架"+count+"个商品");
    }

    /**
     * 逻辑删除
     * @Param id
     * @return
     */
    @DeleteMapping("/logic/delete/{id}")
    public Result logicDelete(@PathVariable Long id){
        spuService.logicDeleteSpu(id);
        return new Result(true, StatusCode.OK,"逻辑删除成功!");
    }
    /**
     * 恢复数据
     * @param id
     * @return
     */
    @PutMapping("/restore/{id}")
    public Result restoreSpu(@PathVariable Long id){
        spuService.restoreSpu(id);
        return new Result(true,StatusCode.OK,"恢复数据成功!");
    }

}
