package com.soothsayer.authn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.soothsayer")
public class SoothsayerAuthNApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoothsayerAuthNApplication.class, args);
    }
}
