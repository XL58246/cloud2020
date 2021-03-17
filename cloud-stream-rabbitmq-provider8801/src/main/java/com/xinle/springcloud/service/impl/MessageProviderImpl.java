package com.xinle.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.xinle.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;

@EnableBinding(Source.class)
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output;

    @Override
    public String send() {

        String uuid = IdUtil.simpleUUID();

        output.send(MessageBuilder.withPayload(uuid).build());

        System.out.println("uuid:"+uuid);

        return uuid;
    }
}
