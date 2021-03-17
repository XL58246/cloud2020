package com.xinle.springcloud.controller;

import com.xinle.springcloud.common.CommonResult;
import com.xinle.springcloud.entities.Payment;
import com.xinle.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Resource
    public PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/ok/{id}")
    private String paymentInfo_ok(@PathVariable("id") Integer id){

        return paymentService.paymentInfo_ok(id);
    }

    @GetMapping("/timeout/{id}")
    private String paymentInfo_timeOut(@PathVariable("id") Integer id){

        return paymentService.paymentInfo_timeOut(id);
    }

    @GetMapping("/error/{id}")
    private String paymentInfo_error(@PathVariable("id") Integer id){

        return paymentService.paymentInfo_error(id);
    }

    @GetMapping("/circuit/{id}")
    private String paymentCircuitBreaker(@PathVariable("id") Integer id){

        return paymentService.paymentCircuitBreaker(id);
    }

}
