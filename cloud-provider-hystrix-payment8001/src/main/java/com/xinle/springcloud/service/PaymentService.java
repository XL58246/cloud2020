package com.xinle.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
@DefaultProperties(defaultFallback = "defaultHandler")
public class PaymentService {

    public String paymentInfo_ok(Integer id){
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_ok,id:"+id;
    }


    @HystrixCommand(fallbackMethod="paymentInfo_timeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_timeOut(Integer id) {

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "线程池："+Thread.currentThread().getName()+" paymentInfo_ok,id:"+id;
    }

    @HystrixCommand
    public String paymentInfo_error(Integer id){
        int a = 1/0;
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_ok,id:"+id;
    }

    public String paymentInfo_timeOutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_ok,id:"+id+";/(ㄒoㄒ)/~~服务器异常。。。。";
    }

    public String defaultHandler(){
        return "线程池："+Thread.currentThread().getName()+";/(ㄒoㄒ)/~~服务器异常。。。。";
    }


    //服务熔断=====
    //当熔断机制开启后，请求次数10次中有6次以上请求错误，将开启熔断窗口期10秒
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),  //是否开启熔断
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),  //请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="10000"),  //熔断开启时间
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60")    //错误率
    })
    public String paymentCircuitBreaker(Integer id){
        if (id<0){
            throw new RuntimeException("id不能为负数!!!");
        }

        String uuid = IdUtil.simpleUUID();
        return "调用成功，流水号："+uuid;
    }

    public String paymentCircuitBreaker_fallback(Integer id){
        return "id不能为负数，请稍后再试，id："+id+";/(ㄒoㄒ)/~~";
    }

}
