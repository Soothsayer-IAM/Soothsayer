package com.soothsayer.authn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = "com.soothsayer")
@EnableEurekaClient
public class SoothsayerAuthNApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoothsayerAuthNApplication.class, args);
    }
}
