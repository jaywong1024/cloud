package top.hanjjie.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hanjjie.cloud.utils.ResultBean;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/api/hystrix")
public class HystrixProviderController {

    /**
     * 获取当前线程名称
     */
    @GetMapping("/health")
    public ResultBean<JSONObject> health() {
        log.info("获取当前线程名称");
        return new ResultBean<>(new JSONObject().fluentPut("thread name", Thread.currentThread().getName()));
    }

    /**
     * 获取当前线程名称（睡 3 秒）
     * 使用 Hystrix @HystrixCommand注解 进行服务降级
     * 此线程运行时长超过 0.5秒 或 出现异常 则调用下面的 timeoutFallback 进行服务降级
     */
    @GetMapping("/timeout")
    @HystrixCommand(fallbackMethod = "timeoutFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
    })
    public ResultBean<JSONObject> timeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("获取当前线程名称（睡 3 秒）");
        return new ResultBean<>(new JSONObject().fluentPut("thread name", Thread.currentThread().getName()));
    }

    /**
     * 获取当前线程名称（睡 3 秒），回退方法
     */
    public ResultBean<JSONObject> timeoutFallback() {
        ResultBean<JSONObject> resultBean = new ResultBean<>();
        resultBean.setCode(ResultBean.FAIL);
        resultBean.setMsg("访问 timeout 接口，超时 或者 出现异常...调用 timeoutFallback 方法进行服务降级");
        resultBean.setData(new JSONObject().fluentPut("thread name", Thread.currentThread().getName()));
        log.error(resultBean.getMsg());
        return resultBean;
    }

    /**
     * 测试服务熔断
     * @param executeTime 执行时长（毫秒）
     *
     * execution.isolation.thread.timeoutInMilliseconds 线程执行时间阈值
     * circuitBreakerFallback                           指定服务降级方法
     * circuitBreaker.enable                            是否开启断路器功能
     * circuitBreaker.requestVolumeThreshold            设置滚动窗口中将使断路器跳闸的最小请求数量
     * circuitBreaker.sleepWindowInMilliseconds         断路器跳闸后，在此值的时间的内，Hystrix 会拒绝新的请求，只有过了这个时间断路器才会打开闸门
     * circuitBreaker.errorThresholdPercentage          设置失败百分比的阈值。如果失败比率超过这个值，则断路器跳闸并且进入 Fallback 逻辑
     *
     * 设置总结：开启断路器，在 10 秒内 10 个请求中有 30% 出现异常或者执行超时（0.5秒），则触发 circuitBreakerFallback 方法作为降级方法
     */
    @GetMapping("/circuit/breaker/{executeTime}")
    @HystrixCommand(fallbackMethod = "circuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),
            @HystrixProperty(name = "circuitBreaker.enable", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30")
    })
    public ResultBean<JSONObject> circuitBreaker(@PathVariable("executeTime") Integer executeTime) {
        try {
            TimeUnit.MILLISECONDS.sleep(executeTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResultBean<>(new JSONObject().fluentPut("uuid", IdUtil.simpleUUID()));
    }

    /**
     * 测试服务熔断，回退方法
     */
    public ResultBean<JSONObject> circuitBreakerFallback(Integer executeTime) {
        ResultBean<JSONObject> resultBean = new ResultBean<>();
        resultBean.setCode(ResultBean.FAIL);
        resultBean.setMsg("访问 circuitBreaker 接口，超时 或者 出现异常...调用 circuitBreakerFallback 方法进行服务降级");
        log.error(resultBean.getMsg());
        return resultBean;
    }

}
