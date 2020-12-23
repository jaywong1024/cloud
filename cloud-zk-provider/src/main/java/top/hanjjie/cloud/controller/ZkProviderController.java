package top.hanjjie.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hanjjie.cloud.utils.ResultBean;
import java.util.UUID;

@Slf4j
@RestController
public class ZkProviderController {

    @GetMapping("/testZkConnecting")
    private ResultBean<String> testZkConnecting() {
        log.info("testZkConnecting...");
        return new ResultBean<>(UUID.randomUUID().toString());
    }

}
