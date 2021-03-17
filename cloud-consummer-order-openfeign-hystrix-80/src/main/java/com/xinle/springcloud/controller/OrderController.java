package com.xinle.springcloud.controller;

import com.xinle.springcloud.common.CommonResult;
import com.xinle.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        return paymentService.paymentInfo_ok(id);
    };

    @GetMapping("/timeout/{id}")
    public String paymentInfo_timeOut(@PathVariable("id") Integer id){
        return paymentService.paymentInfo_timeOut(id);
    };

    @GetMapping("/error/{id}")
    public String paymentInfo_error(@PathVariable("id") Integer id){
        return paymentService.paymentInfo_error(id);
    };

}
