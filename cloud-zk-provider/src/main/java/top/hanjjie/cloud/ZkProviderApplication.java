package top.hanjjie.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 主启动类
 * @EnableDiscoveryClient 用于使用 consul 或者 zookeeper 作为注册中心时注册服务
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZkProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZkProviderApplication.class, args);
    }

}
