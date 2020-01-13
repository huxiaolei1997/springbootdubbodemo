package com.demo.springboot.dubbo.serviceImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.demo.springboot.dubbo.TestService;
import com.demo.springboot.dubbo.UserDto;
import com.demo.springboot.dubbo.UserProvider;
import com.demo.springboot.dubbo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.*;

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

    @Reference(version = "1.0")
    private UserProvider userProvider;

    @Override
    public String getName(String name) {
        log.info("ddddssdfdfd" + name);
        Thread thread = new Thread(() -> log.info("子线程中获取traceId: {}", name));
        thread.start();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 1, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        threadPoolExecutor.execute(() -> log.info("线程池中获取traceId-1: {}", name));

        threadPoolExecutor.execute(() -> log.info("线程池中获取traceId-2: {}", name));

        UserDto userDto = new UserDto();
        userDto.setUserName(name);
        UserVo userVo = userProvider.getUser(userDto);
//        threadPoolExecutor.start
        return "Your name is " + userVo.getUserName();
    }

    @Override
    public UserVo addUser(UserDto userDto) {
        System.out.println("UserDto is " + JSON.toJSONString(userDto));
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userDto, userVo);
        return userVo;
    }
}
