package top.hanjjie.cloud.config;

import feign.Logger;
import feign.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenFeign 配置类
 */
@Configuration
public class OpenFeignConfig {

    @Value("${openfeign.timeout.read}")
    private int connectTimeout;

    @Value("${openfeign.timeout.connection}")
    private int readTimeout;

    @Value("${openfeign.log.level}")
    private String loggerLevel;

    @Bean
    public Request.Options options() {
        return new Request.Options(connectTimeout, readTimeout);
    }

    @Bean
    public Logger.Level level() {
        return Logger.Level.valueOf(loggerLevel);
    }

}
