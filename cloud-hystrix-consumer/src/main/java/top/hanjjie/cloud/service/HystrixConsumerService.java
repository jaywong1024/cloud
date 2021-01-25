package top.hanjjie.cloud.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import top.hanjjie.cloud.config.OpenFeignConfig;
import top.hanjjie.cloud.config.OpenFeignFallbackFactory;
import top.hanjjie.cloud.utils.ResultBean;

/**
 * Openfeign 业务类
 */
@Component
@FeignClient(value = "${provider.hystrix-provider}",
        configuration = OpenFeignConfig.class, fallbackFactory = OpenFeignFallbackFactory.class)
@RequestMapping("/api/hystrix")
public interface HystrixConsumerService {

    /**
     * 获取当前线程名称
     */
    @GetMapping("/health")
    ResultBean<JSONObject> health();

    /**
     * 获取当前线程名称（睡 3 秒）
     */
    @GetMapping("/timeout")
    ResultBean<JSONObject> timeout();

    /**
     * 测试服务熔断
     */
    @GetMapping("/circuit/breaker/{executeTime}")
    ResultBean<JSONObject> circuitBreaker(@PathVariable("executeTime") Integer executeTime);

}
