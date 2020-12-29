/**
 * -*- encoding: utf-8 -*-
 *
 * @Time : 2020/12/27 17:36
 * @Author : mike.liu
 * @File : GatewayWebApplication.java
 **/

package com.yigou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@SpringBootApplication
@EnableEurekaClient
public class GatewayWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayWebApplication.class,args);
    }

    /***
     * Ip限流
     * @return
     */
//    @Bean(name="ipKeyResolver")
//    public KeyResolver userKeyResolver(){
//        return new KeyResolver(){
//            @Override
//            public Mono<String> resolve(ServerWebExchange exchange){
//                //1.获取请求request对象
//                ServerHttpRequest request = exchange.getRequest();
//                //2.从request中获取ip地址
//                String hostString = request.getRemoteAddress().getHostString(); //Ip地址
//
//                //3.返回
//                return Mono.just(hostString);
//            }
//        };
//
//    }
}
