package com.demo.springboot.dubbo.consumer;

import com.demo.springboot.dubbo.UserDto;
import com.demo.springboot.dubbo.UserVo;

public interface UserConsumer {
    UserVo getUser(UserDto userDto);
}
