package com.xinle.springcloud.controller;

import com.xinle.springcloud.common.CommonResult;
import com.xinle.springcloud.entities.Payment;
import com.xinle.springcloud.service.PaymentService;
import lombok.extern.java.Log;
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

    @GetMapping("/{id}")
    private CommonResult<Payment> findById(@PathVariable("id") Long id){

        return new CommonResult<Payment>(200,"SUCCESS,Server Port:"+serverPort,paymentService.findById(id));
    }

    @PostMapping("/")
    private CommonResult<Payment> create(@RequestBody Payment payment){

        System.out.println("***********create***********");
        int result = paymentService.create(payment);
        return new CommonResult<Payment>(100,"SUCCESS",payment);
    }


    @GetMapping("/get/discovery")
    private CommonResult getDiscovery(){

        for(String service:discoveryClient.getServices()){

            System.out.println("*******--"+service+"*********");

            List<ServiceInstance> instances = discoveryClient.getInstances(service);

            for(ServiceInstance instance:instances){
                System.out.println("*******----"+instance.getInstanceId()+"*********");
            }

        }

        return new CommonResult(200,"SUCCESS",discoveryClient);
    }

    @GetMapping("/get/timeout")
    private String timeout(){

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "SUCCESS";
    }

}
