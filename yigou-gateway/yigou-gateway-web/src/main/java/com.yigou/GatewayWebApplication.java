/**
 * -*- encoding: utf-8 -*-
 *
 * @Time : 2020/12/24 22:41
 * @Author : mike.liu
 * @File : GatewayWebApplication.java
 **/

package com.yigou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient

public class GatewayWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayWebApplication.class,args);
    }
}
