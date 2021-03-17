package com.xinle.springcloud.service;

import com.xinle.springcloud.hystrixfallback.PaymentFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE",fallback = PaymentFallBack.class)
public interface PaymentService {

    @GetMapping("/payment/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id);

    @GetMapping("/payment/timeout/{id}")
    public String paymentInfo_timeOut(@PathVariable("id") Integer id);

    @GetMapping("/payment/error/{id}")
    public String paymentInfo_error(@PathVariable("id") Integer id);

}
