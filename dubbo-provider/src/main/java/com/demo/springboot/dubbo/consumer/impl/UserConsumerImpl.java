package com.demo.springboot.dubbo.consumer.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.springboot.dubbo.UserDto;
import com.demo.springboot.dubbo.UserProvider;
import com.demo.springboot.dubbo.UserVo;
import com.demo.springboot.dubbo.consumer.UserConsumer;
import org.springframework.stereotype.Service;

@Service
public class UserConsumerImpl implements UserConsumer {
    @Reference(version = "${dubbo.application.version}")
    private UserProvider userProvider;

    @Override
    public UserVo getUser(UserDto userDto) {
        return userProvider.getUser(userDto);
    }
}
