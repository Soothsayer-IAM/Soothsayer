package com.soothsayer.authn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.soothsayer")
@EnableEurekaClient
@EnableFeignClients
public class SoothsayerAuthNApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoothsayerAuthNApplication.class, args);
    }
}
