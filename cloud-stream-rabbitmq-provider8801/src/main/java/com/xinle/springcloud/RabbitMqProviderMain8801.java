package com.xinle.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RabbitMqProviderMain8801 {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqProviderMain8801.class,args);
    }
}