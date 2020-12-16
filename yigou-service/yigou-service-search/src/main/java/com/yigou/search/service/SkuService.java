/**
 * -*- encoding: utf-8 -*-
 *
 * @Time : 2020/12/15 23:20
 * @Author : mike.liu
 * @File : SkuService.java
 **/


package com.yigou.search.service;

import java.util.Map;

public interface SkuService {
    /***
     * 导入SKU数据
     */
    void importSku();

    /***
     * 搜索
     * @param searchMap
     * @return
     */
    Map search(Map<String, String> searchMap);
}
