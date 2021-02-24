package top.hanjjie.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class StreamRmqConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamRmqConsumerApplication.class, args);
    }


}
