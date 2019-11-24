package com.demo.springboot.dubbo;

/**
 * @author xiaolei hu
 * @date 2018/10/27 12:23
 **/
public interface TestService {
    String getName(String name);

    UserVo addUser(UserDto userDto);
}
