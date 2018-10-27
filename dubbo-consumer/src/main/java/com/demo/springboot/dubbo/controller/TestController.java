package com.demo.springboot.dubbo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.springboot.dubbo.service.TestService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaolei hu
 * @date 2018/10/27 14:03
 **/
@RestController
@RequestMapping("/test")
public class TestController {
    @Reference(version = "1.0")
    private TestService testService;

    @RequestMapping("/getname/{name}")
    public String getName(@PathVariable("name") String name) {
        String data = testService.getName(name);
        return data;
    }
}
