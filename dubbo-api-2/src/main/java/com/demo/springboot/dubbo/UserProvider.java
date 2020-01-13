package com.demo.springboot.dubbo;

/**
 * @author xiaolei hu
 * @date 2018/10/27 12:23
 **/
public interface UserProvider {
    UserVo getUser(UserDto userDto);
}
