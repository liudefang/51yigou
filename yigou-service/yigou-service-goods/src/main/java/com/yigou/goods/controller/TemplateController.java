package com.yigou.goods.controller;

import com.github.pagehelper.PageInfo;
import com.yigou.goods.pojo.Template;
import com.yigou.goods.service.TemplateService;
import entity.Result;
import entity.StatusCode;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Time    : 2020/7/10 3:42 下午
 * Author  : mike.liu
 * File    : TemplateController.java
 */

@RestController
@RequestMapping("/template")
@CrossOrigin
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    /***
     * Template分页条件搜索实现
     * @param template
     * @param page
     * @param size
     * @return
     */

    @PostMapping(value="/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody(required=false) Template template, @PathVariable int page, @PathVariable int size){
        //执行搜索
        PageInfo<Template> pageInfo = templateService.findPage(template, page, size);
        return new Result(true, StatusCode.OK,"查询成功", pageInfo);
    }

    /***
     * Template分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size){
        //分页查询
        PageInfo<Template> pageInfo = templateService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param template
     * @return
     */
    @PostMapping(value = "/search")
    public Result<List<Template>> findList(@RequestBody(required = false) Template template){
        List<Template> list = templateService.findList(template);
        return new Result<List<Template>>(true,StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id){
        templateService.delete(id);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    /***
     * 修改Template数据
     * @param template
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Template template,@PathVariable Integer id){
        //设置主键
        template.setId(id);
        //修改数据
        templateService.update(template);
        return new Result(true, StatusCode.OK,"修改成功");
    }

    /***
     * 新增Template数据
     * @param template
     * @return
     */
    public Result add(@RequestBody Template template){
        templateService.add(template);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /***
     * 根据ID查询Template数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Template> findById(@PathVariable Integer id){
        //根据ID查询
        Template template = templateService.findById(id);
        return new Result<Template>(true, StatusCode.OK, "查询成功", template);
    }

    /***
     * 查询Template全部数据
     * @return
     */
    @GetMapping
    public Result<Template> findAll() {
        List<Template> list = templateService.findAll();
        return new Result<Template>(true, StatusCode.OK, "查询成功", list);
    }
}
