package com.xinle.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaPaymentMain9102 {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaPaymentMain9102.class,args);
    }
}
