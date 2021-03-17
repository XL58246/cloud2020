package com.xinle.springcloud.controller;

import com.xinle.springcloud.common.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final String PAYMENT_PATH = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    private CommonResult findById(@PathVariable("id")Long id){
        return restTemplate.getForObject(PAYMENT_PATH+"/payment/"+id,CommonResult.class);
    }

}
