package com.xinle.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
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

    private static final String PAYMENT_PATH = "http://cloudalibaba-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/getById/{id}")
    @SentinelResource(value = "findById",fallback = "fallbackHandler")
    public CommonResult findById(@PathVariable("id")Long id){
        return restTemplate.getForObject(PAYMENT_PATH+"/payment/getById/"+id,CommonResult.class);
    }

    @GetMapping("/error")
    @SentinelResource(value = "error",fallback = "fallbackHandler",blockHandler = "blockHandler")
    public CommonResult error(){

        int a = 10/0;

        return new CommonResult(200,"(*^_^*)",null);
    }


    public CommonResult fallbackHandler(Throwable e){
        return new CommonResult(444,"fallbackHandler,/(ㄒoㄒ)/~~",null);
    }

    public CommonResult blockHandler(BlockException e){
        return new CommonResult(555,"blockHandler,/(ㄒoㄒ)/~~",null);
    }
}
