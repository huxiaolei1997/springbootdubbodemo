package com.xlh.demo.springboot.dubbo;

import java.util.Map;

/**
 * @author xiaolei hu
 * @date 2018/10/27 12:23
 **/
public interface TestService {
    String getName(String name);

    String getName(UserDto userDto, String name);

    String getAge(Map<String, Integer> params, String name);

    UserVo addUser(UserDto userDto);

    void testInvoke();
}
