package com.yigou.goods.controller;

import com.github.pagehelper.PageInfo;
import com.yigou.goods.pojo.Category;
import com.yigou.goods.service.CategoryService;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Time    : 2020/7/15 3:28 下午
 * Author  : mike.liu
 * File    : CategoryController.java
 */
@Controller
@Api(tags = "CategoryController",description = "分类管理")
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /***
     * Category分页条件搜索实现
     * @param category
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody(required = false) Category category,@PathVariable int page, @PathVariable int size){
        //进行搜索
        PageInfo<Category> pageInfo = categoryService.findPage(category, page, size);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Category分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size){
        //分页查询
        PageInfo<Category> pageInfo = categoryService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);

    }

    /***
     * 多条件搜索品牌数据
     * @param category
     * @return
     */
    @PostMapping(value = "/search")
    public Result<List<Category>> findList(@RequestBody(required = false) Category category){
        List<Category> list = categoryService.findList(category);
        return new Result<List<Category>>(true,StatusCode.OK,"查询成功",list);

    }
    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id){
        categoryService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
    /***
     * 修改Category数据
     * @param category
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Category category,@PathVariable Integer id){
        //设置主键值
        category.setId(id);
        //修改数据
        categoryService.update(category);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Category数据
     * @param category
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Category category){
        categoryService.add(category);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Category数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Category> findById(@PathVariable Integer id){
        //根据ID查询
        Category category = categoryService.findById(id);
        return new Result<Category>(true,StatusCode.OK,"查询成功",category);
    }

    /***
     * 查询Category全部数据
     * @return
     */
    @GetMapping
    public Result<Category> findAll(){
        List<Category> list = categoryService.findAll();
        return new Result<Category>(true, StatusCode.OK,"查询成功",list);
    }
    /**
     * 根据父ID查询
     */
    @RequestMapping(value ="/list/{pid}")
    public Result<Category> findByPrantId(@PathVariable(value = "pid") Integer pid){
        //根据父节点ID查询
        List<Category> list = categoryService.findByParentId(pid);
        return new Result<Category>(true, StatusCode.OK,"查询成功", list);
    }
}
