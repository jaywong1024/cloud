package top.hanjjie.cloud.config;

import feign.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenFeign 配置类
 */
@Configuration
public class OpenFeignConfig {

    @Value("${openfeign.read-timeout}")
    private int connectTimeout;

    @Value("${openfeign.connection-timeout}")
    private int readTimeout;

    @Bean
    public Request.Options options() {
        return new Request.Options(connectTimeout, readTimeout);
    }

}
