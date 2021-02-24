package top.hanjjie.cloud.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hanjjie.cloud.tools.HystrixConsumerService;
import top.hanjjie.cloud.utils.ResultBean;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/api/hystrix/consumer")
public class HystrixConsumerController {

    @Resource
    private HystrixConsumerService hystrixConsumerService;

    /**
     * 获取当前线程名称
     */
    @GetMapping("/health")
    public ResultBean<JSONObject> health() {
        return hystrixConsumerService.health();
    }

    /**
     * 获取当前线程名称（睡 3 秒）
     */
    @GetMapping("/timeout")
    public ResultBean<JSONObject> timeout() {
        return hystrixConsumerService.timeout();
    }

    /**
     * 测试服务熔断
     */
    @GetMapping("/circuit/breaker/{executeTime}")
    public ResultBean<JSONObject> circuitBreaker(@PathVariable("executeTime") Integer executeTime) {
        return hystrixConsumerService.circuitBreaker(executeTime);
    }

}
