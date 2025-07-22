package com.elementoj.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class GatewayConfiguration {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        return routes
                //名称为gateway-beijing的路由,匹配地址 /bj/** 使用  Nacos  里的 beijing 去处理请求 lb为负载均衡
                .route("elementoj-account", r -> r.path("/acc/**").uri("lb://elementoj-account"))
                .route("elementoj-auth", r -> r.path("/auth/**").uri("lb://elementoj-auth"))
                .route("elementoj-consumer", r -> r.path("/cor/**").uri("lb://elementoj-consumer"))
                .route("elementoj-module-news", r -> r.path("/news/**").uri("http://localhost:7800/"))

//                .route("gateway-after", r ->
//                        //匹配路径为 /show
//                        r.path("/show")
//                                //多个断言之间,使用and方法连接
//                                .and()
//                                //断言时间,只能在此时间后访问
//                                .after(ZonedDateTime.parse("2022-08-25T10:00:00+08:00[Asia/Shanghai]"))
//                                .and()
//                                //断言查询参数,必须包含age,如  /show?age=1
//                                .query("age")
//                                //设置过滤器,在过滤器内添加请求参数,那么实际控制器收到的请求为: /show?age=1&name=tom
//                                .filters(f -> f.addRequestParameter("name", "tom"))
//                                //使用shanghai去处理请求
//                                .uri("lb://shanghai")
//                )
                //将路径为 /personal 的请求,转到石墨文档,石墨文档收到请求后,请求地址为: https://shimo.im/personal
                .build();
    }
}
