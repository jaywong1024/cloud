package top.hanjjie.cloud.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.hanjjie.cloud.utils.ResultBean;

import javax.annotation.Resource;

@RestController
@RequestMapping("/nacos")
public class NacosConsumerController {

    @Value("${provider.nacos}")
    private String nacosProvider;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer")
    public ResultBean<JSONObject> nacosConsumer() {
        ResponseEntity<ResultBean<JSONObject>> nacosProviderResponse = restTemplate.exchange(nacosProvider + "/nacos/provider", HttpMethod.GET, new HttpEntity<>(null), new ParameterizedTypeReference<ResultBean<JSONObject>>() {});
        if (nacosProviderResponse.getStatusCode().is2xxSuccessful()) {
            return nacosProviderResponse.getBody();
        }
        return new ResultBean<>("访问服务提供者失败");
    }

}
