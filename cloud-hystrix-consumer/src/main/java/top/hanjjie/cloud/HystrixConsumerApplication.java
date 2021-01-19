package top.hanjjie.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class HystrixConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixConsumerApplication.class, args);
    }

}
