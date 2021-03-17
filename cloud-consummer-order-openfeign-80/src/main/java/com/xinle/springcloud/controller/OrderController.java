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

    @GetMapping("/{id}")
    private CommonResult findById(@PathVariable("id")Long id){
        return paymentService.findById(id);
    }

    @GetMapping("/get/timeout")
    private String timeout(){
        return paymentService.timeout();
    }

}
