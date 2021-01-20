package top.hanjjie.cloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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
     * 此线程运行时长超过 0.5秒 则调用下面的 timeoutFallback 回退方法
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
     * 获取当前线程名称（睡 3 秒），回退方法，返回连接超时
     */
    public ResultBean<JSONObject> timeoutFallback() {
        log.error("获取当前线程名称（睡 3 秒），回退方法，返回连接超时");
        ResultBean<JSONObject> timeoutResultBean = new ResultBean<>();
        timeoutResultBean.setCode(ResultBean.TIMEOUT);
        timeoutResultBean.setMsg("获取当前线程名称（睡 3 秒），回退方法，返回连接超时");
        timeoutResultBean.setData(new JSONObject().fluentPut("thread name", Thread.currentThread().getName()));
        return timeoutResultBean;
    }

    /**
     * 获取当前线程名称（抛异常）
     */
    @GetMapping("/error")
    public ResultBean<JSONObject> error() {
        log.info("获取当前线程名称（抛异常）");
        return new ResultBean<>(new JSONObject().fluentPut("thread name", Thread.currentThread().getName()));
    }

}
