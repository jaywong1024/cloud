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
@RequestMapping("/cfg")
public class ConfigClientController {

    @Value("${config.info}")
    private String cfgInfo;

    @Value("${config.version}")
    private String cfgVersion;

    @GetMapping("/info")
    public ResultBean<JSONObject> getCfgInfo() {
        JSONObject resultConfig = new JSONObject().fluentPut("info", cfgInfo).fluentPut("version", cfgVersion);
        log.info("返回从 服务配置中 获取的 config info 和 version: {}", resultConfig);
        return new ResultBean<>(resultConfig);
    }

}
