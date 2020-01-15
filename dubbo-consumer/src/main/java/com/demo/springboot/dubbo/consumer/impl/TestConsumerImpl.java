package com.demo.springboot.dubbo.consumer.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.springboot.dubbo.TestService;
import com.demo.springboot.dubbo.consumer.TestConsumer;
import org.springframework.stereotype.Service;

@Service
public class TestConsumerImpl implements TestConsumer {
    @Reference(version = "${dubbo.application.version}")
    private TestService testService;

    @Override
    public String getName(String name) {
        return testService.getName(name);
    }
}
