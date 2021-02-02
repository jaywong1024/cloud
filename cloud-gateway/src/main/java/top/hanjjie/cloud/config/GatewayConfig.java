package top.hanjjie.cloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Gateway 配置
 */
@Configuration
public class GatewayConfig {

    /**
     * 自定义一个路由，将访问 http://host:port/bilibili 的请求全部转发到 https://www.bilibili.com/
     */
    @Bean
    public RouteLocator toBilibili(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder builder = routeLocatorBuilder.routes();
        builder.route("to_bilibili", r -> r.path("/bilibili").uri("https://www.bilibili.com/"));
        return builder.build();
    }

    /**
     * 自定义一个路由，将访问 http://host:port/translate 的请求全部转发到 https://translate.google.cn/
     */
    @Bean
    public RouteLocator toTranslate(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder builder = routeLocatorBuilder.routes();
        builder.route("to_translate", r -> r.path("/translate").uri("https://translate.google.cn/"));
        return builder.build();
    }

}
