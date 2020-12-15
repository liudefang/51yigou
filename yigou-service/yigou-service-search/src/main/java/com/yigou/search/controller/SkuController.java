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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
