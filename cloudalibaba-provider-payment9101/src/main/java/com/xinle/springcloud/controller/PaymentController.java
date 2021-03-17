package com.xinle.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.xinle.springcloud.common.CommonResult;
import com.xinle.springcloud.entities.Payment;
import com.xinle.springcloud.myhandler.CommentHandler;
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
@Slf4j
public class PaymentController {

    @Resource
    public PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/payment/getById/{id}")
    @SentinelResource(value = "findById",blockHandlerClass = CommentHandler.class,blockHandler = "myHandler1")
    public CommonResult<Payment> findById(@PathVariable("id") Long id){

        return new CommonResult<Payment>(200,"SUCCESS,Server Port:"+serverPort,paymentService.findById(id));
    }

    @PostMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){

        System.out.println("***********create***********");
        int result = paymentService.create(payment);
        return new CommonResult<Payment>(100,"SUCCESS",payment);
    }


    @GetMapping("/payment/discovery")
    public CommonResult getDiscovery(){

        for(String service:discoveryClient.getServices()){

            System.out.println("*******--"+service+"*********");

            List<ServiceInstance> instances = discoveryClient.getInstances(service);

            for(ServiceInstance instance:instances){
                System.out.println("*******----"+instance.getInstanceId()+"*********");
            }

        }

        return new CommonResult(200,"SUCCESS",discoveryClient);
    }

    @GetMapping("/payment/timeout")
    public String timeout(){

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "SUCCESS";
    }

    @GetMapping("/payment/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "hotKeyHandler")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                              @RequestParam(value = "p2",required = false) String p2){
        return "testHotKey Success!!";
    }

    public String hotKeyHandler(String p1, String p2, BlockException exception){
        return "hotKeyHandler,/(ㄒoㄒ)/~~";
    }


    @GetMapping("/payment/testComment")
    @SentinelResource(value = "testComment",blockHandlerClass = CommentHandler.class,blockHandler = "myHandler1")
    public String testComment(){
        return "testComment Success!!";
    }

}
