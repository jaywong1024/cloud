package top.hanjjie.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hanjjie.cloud.utils.ResultBean;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/api/hystrix")
public class HystrixController {

    /**
     * 获取当前线程名称
     */
    @GetMapping("/health")
    public ResultBean<String> health() {
        log.info("获取当前线程名称");
        return new ResultBean<>(Thread.currentThread().getName());
    }

    /**
     * 获取当前线程名称（睡 3 秒）
     */
    @GetMapping("/timeout")
    public ResultBean<String> timeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("获取当前线程名称（睡 3 秒）");
        return new ResultBean<>(Thread.currentThread().getName());
    }

    /**
     * 获取当前线程名称（抛异常）
     */
    @GetMapping("/error")
    public ResultBean<String> error() {
        log.info("获取当前线程名称（抛异常）");
        throw new RuntimeException("hystrix error");
    }

}
