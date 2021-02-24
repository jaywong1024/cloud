package top.hanjjie.cloud.tools.fallback;

import com.alibaba.fastjson.JSONObject;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.hanjjie.cloud.config.OpenFeignGlobalFallback;
import top.hanjjie.cloud.tools.HystrixConsumerService;
import top.hanjjie.cloud.utils.ResultBean;

import javax.annotation.Resource;

/**
 * Openfeign 服务降级方法
 */
@Slf4j
@Component
public class HystrixConsumerServiceFallback implements FallbackFactory<HystrixConsumerService> {

    @Resource
    private OpenFeignGlobalFallback openFeignGlobalFallback;

    @Override
    public HystrixConsumerService create(Throwable throwable) {

        ResultBean<JSONObject> fallback = openFeignGlobalFallback.fallback(throwable);

        return new HystrixConsumerService() {
            @Override
            public ResultBean<JSONObject> health() {
                return fallback;
            }

            @Override
            public ResultBean<JSONObject> timeout() {
                return fallback;
            }

            @Override
            public ResultBean<JSONObject> circuitBreaker(Integer executeTime) {
                return fallback;
            }
        };
    }
}
