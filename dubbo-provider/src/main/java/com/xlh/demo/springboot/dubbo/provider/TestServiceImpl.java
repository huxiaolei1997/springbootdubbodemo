package com.xlh.demo.springboot.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.xlh.demo.springboot.dubbo.TestService;
import com.xlh.demo.springboot.dubbo.UserDto;
import com.xlh.demo.springboot.dubbo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.Map;

/**
 * @author xiaolei hu
 * @date 2018/10/27 13:03
 **/
//@org.springframework.stereotype.Service // 不要用 spring 的注解
// 相当于我们在 xml 里面配置的 东西
//@Service(version = "${dubbo.application.version}", application = "${dubbo.application.id}", protocol = "${dubbo.protocol.id}", registry = "${dubbo.registry.id") // 在这里用 dubbo 里面的 service 注解，作用就是创建这个类型的对象，然后作为服务提供者发布出去
@Service(version = "${dubbo.application.version}") // 在这里用 dubbo 里面的 service 注解，作用就是创建这个类型的对象，然后作为服务提供者发布出去
@Slf4j
public class TestServiceImpl implements TestService {
    @Override
    public String getName(String name) {
        log.info("ddddssdfdfd" + name);
        return "Your name is " + name;
    }

    @Override
    public String getAge(Map<String, Integer> params, String name) {
        return "Your ages is " + params.get("age");
    }

    @Override
    public String getName(UserDto userDto, String name) {
        return "Your name is" + userDto.getUserName();
    }

    @Override
    public UserVo addUser(UserDto userDto) {
        System.out.println("UserDto is " + JSON.toJSONString(userDto));
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userDto, userVo);
        return userVo;
    }

    @Override
    public void testInvoke() {
        System.out.println("testInvoke");
    }
}
