package com.demo.springboot.dubbo.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.demo.springboot.dubbo.TestService;

/**
 * @author xiaolei hu
 * @date 2018/10/27 13:03
 **/
//@org.springframework.stereotype.Service // 不要用 spring 的注解
// 相当于我们在 xml 里面配置的 东西
//@Service(version = "${dubbo.application.version}", application = "${dubbo.application.id}", protocol = "${dubbo.protocol.id}", registry = "${dubbo.registry.id") // 在这里用 dubbo 里面的 service 注解，作用就是创建这个类型的对象，然后作为服务提供者发布出去
@Service(version = "${dubbo.application.version}") // 在这里用 dubbo 里面的 service 注解，作用就是创建这个类型的对象，然后作为服务提供者发布出去
public class TestServiceImpl implements TestService {
    @Override
    public String getName(String name) {
        System.out.println("ddddssdfdfd" + name);
        return "Your name is " + name;
    }
}
