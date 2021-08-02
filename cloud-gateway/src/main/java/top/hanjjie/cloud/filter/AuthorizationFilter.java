package top.hanjjie.cloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 全局鉴权过滤器
 */
@Slf4j
@Component
public class AuthorizationFilter implements GlobalFilter, Ordered {

    @Value("${filter.authorization.error-msg}")
    private String errorMsg;

    private static final String AUTHORIZATION = "Authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        List<String> authorizationList = exchange.getRequest().getHeaders().get(AUTHORIZATION);
//        只允许请求头中有 Authorization: hanjjie 的人访问
        if (authorizationList == null || StringUtils.isBlank(authorizationList.get(0))
                || !"hanjjie".equals(authorizationList.get(0))) {
            log.warn(errorMsg);
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
//        如果不存在问题，向过滤链下行
        return chain.filter(exchange);
    }

    /**
     * 过滤器在过滤链中的位置
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
