package com.xinle.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RabbitMqConsummerMain8803 {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqConsummerMain8803.class,args);
    }
}