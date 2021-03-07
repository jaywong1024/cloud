package top.hanjjie.cloud.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hanjjie.cloud.utils.ResultBean;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("/nacos")
public class NacosProviderController {

    @GetMapping("/provider")
    public ResultBean<JSONObject> nacosProvider() {
        log.info("服务提供接口被调用，返回线程名称");
        return new ResultBean<>(new JSONObject().fluentPut("Thread name", Thread.currentThread().getName()));
    }

    @Value("${my.name}")
    private String myName;

    @GetMapping("/config")
    public ResultBean<JSONObject> nacosConfig() {
        log.info("测试服务配置");
        return new ResultBean<>(new JSONObject().fluentPut("My name", myName));
    }

}
