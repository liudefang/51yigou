/**
 * -*- encoding: utf-8 -*-
 *
 * @Time : 2020/12/15 23:22
 * @Author : mike.liu
 * @File : SkuController.java
 **/

package com.yigou.search.controller;

import com.yigou.search.service.SkuService;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Api(tags = "SkuController 品牌管理")
@RequestMapping(value = "/search")
public class SkuController {
    @Autowired
    private SkuService skuService;

    /**
     * 导入数据
     * @return
     */
    @GetMapping("/import")
    @ApiOperation(value = "导入数据")
    public Result search(){
        skuService.importSku();
        return new Result(true, StatusCode.OK,"导入数据到索引库中成功！");
    }
    /**
     * 搜索
     * @param searchMap
     * @return
     */
    @PostMapping
    @ApiOperation(value = "搜索分类")
    public Map search(@RequestBody(required = false) Map searchMap){
        return  skuService.search(searchMap);
    }
}
