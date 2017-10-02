package com.soothsayer.mfa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SoothSayerMfaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SoothSayerMfaApplication.class, args);
    }
}
