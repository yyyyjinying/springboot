package com.cloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

/**
 * 自定义网关全局filter鉴权
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter,Ordered {
    //令牌头名字
    private static final String AUTHORIZE_TOKEN = "Authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("*******come in MyLogGateWayFilter: "+new Date());
//        String name = exchange.getRequest().getQueryParams().getFirst("name");

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String path = request.getURI().getPath();

        // 若果是认证授权放行
        if (path.startsWith("/oauth/") || path.startsWith("/user/login")) {
            return chain.filter(exchange);
        }

        String token = request.getHeaders().getFirst(AUTHORIZE_TOKEN);

        // 请求header中authorization没有值 判断header是否有token拼接authorization
        if (StringUtils.isEmpty(token)) {
            if(request.getHeaders().containsKey("token")) {
                token = request.getHeaders().get("token").get(0);
            }
        }

        // cookie 是否有token
        if(StringUtils.isEmpty(token)){
            HttpCookie first = request.getCookies().getFirst(AUTHORIZE_TOKEN);
            token = first.getValue();
        }

        if (StringUtils.isEmpty(token)) {
            //4.4. 如果没有数据    没有登录,要重定向到登录到页面
            response.setStatusCode(HttpStatus.SEE_OTHER);//303 302
            //location 指定的就是路径
            response.getHeaders().set("Location","/oauth/login");
            return response.setComplete();
        }

        ServerHttpRequest headerRequest = request.mutate().header(AUTHORIZE_TOKEN,  "bearer " + token).build();
        ServerWebExchange build = exchange.mutate().request(headerRequest).build();


        return chain.filter(build);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
