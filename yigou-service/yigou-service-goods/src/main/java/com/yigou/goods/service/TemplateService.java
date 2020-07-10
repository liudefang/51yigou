package com.yigou.goods.service;

import com.github.pagehelper.PageInfo;
import com.yigou.goods.pojo.Template;

import java.util.List;

public interface TemplateService {
    /***
     * Template多条件分页查询
     * @param template
     * @param page
     * @return
     */
    PageInfo<Template> findPage(Template template,int page, int size);

    /***
     * Tempate 分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Template> findPage(int page, int size);

    /***
     * Template多条件搜索方法
     * @param template
     * @return
     */
    List<Template> findList(Template template);

    /***
     * 删除Template
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Template数据
     * @param template
     */
    void update(Template template);

    /***
     * 新增Template
     * @param template
     */
    void add(Template template);

    /**
     * 根据ID查询Template
     * @param id
     * @return
     */
    Template findById(Integer id);

    /***
     * 查询所有Template
     * @return
     */
    List<Template> findAll();

}
