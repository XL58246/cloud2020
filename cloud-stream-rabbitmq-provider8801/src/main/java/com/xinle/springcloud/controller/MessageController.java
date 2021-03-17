package com.xinle.springcloud.controller;

import com.xinle.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MessageController {

    @Resource
    private IMessageProvider iMessageProvider;

    @GetMapping("/send")
    public String send(){
        return iMessageProvider.send();
    }

}
