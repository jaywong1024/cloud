package top.hanjjie.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hanjjie.cloud.utils.ResultBean;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/app")
@RestController
@Slf4j
public class DiscoveryController {

    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 发现服务信息
     */
    @GetMapping("/discovery")
    public ResultBean<Map<String, Object>> discovery() {
        Map<String, Object> result = new HashMap<>(discoveryClient.getServices().size());
        discoveryClient.getServices().forEach(s -> result.put(s, discoveryClient.getInstances(s)));
        return new ResultBean<>(result);
    }

}
