package com.xinle.springcloud.service;

import com.xinle.springcloud.common.CommonResult;
import com.xinle.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
@RequestMapping("/payment")
public interface PaymentService {

    @GetMapping("/{id}")
    public CommonResult<Payment> findById(@PathVariable("id") Long id);

    @GetMapping("/get/timeout")
    public String timeout();

}
