package com.cloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * gateway路由网关的两种配置方式：
 * 1。yml文件
 * 2。代码注入RouteLocator的Bean
 */
@Configuration
public class GateWayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
       routes.route("path_route_baidu", r -> r.path("/guonei").uri("http://news.baidu.com/guonei"));
       return routes.build();
    }
}
