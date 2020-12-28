package top.hanjjie.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.hanjjie.cloud.utils.ResultBean;

import javax.annotation.Resource;

@Slf4j
@RestController
public class ConsulConsumerController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${provider.consul}")
    private String providerConsul;

    @GetMapping("/testConsulConnecting")
    private ResultBean<String> testConsulConnecting() {
        return restTemplate.getForObject(providerConsul + "/testConsulConnecting", ResultBean.class);
    }

}
