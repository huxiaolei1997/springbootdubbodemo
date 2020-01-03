package com.xlh.demo.springboot.dubbo.controller;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.xlh.demo.springboot.dubbo.TestService;
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
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("consumer");
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://139.196.140.168:2181");
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setInterface("com.xlh.demo.springboot.dubbo.TestService");

        // 泛化调用的时候注意version，version 不对会导致找不到对应的提供者，造成调用失败
        referenceConfig.setVersion("1.0");
        applicationConfig.setRegistry(registryConfig);
        referenceConfig.setApplication(applicationConfig);
        // 泛化调用 true
        referenceConfig.setGeneric(true);
        // 注意这里 不能设置 async 为 true ， 否则调用返回值为null
        // referenceConfig.setAsync(true);
        referenceConfig.setTimeout(7000);

        /**
         * ReferenceConfig 实例很重，封装了与注册中心的连接以及与提供者的连接，需要缓存。
         * 否则重复生成 ReferenceConfig 可能造成性能问题并且会有内存和连接泄漏。
         * 在 API 方式编程时，容易忽略此问题。
         * 不要使用 GenericService genericService = referenceConfig.get();
         */
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        // cache.get方法中会缓存 Reference对象，并且调用ReferenceConfig.get方法启动ReferenceConfig
        GenericService genericService = cache.get(referenceConfig);

        Object result = genericService.$invoke("getName", new String[]{String.class.getName()}, new Object[]{"world"});
        return result.toString();
//        String data = testService.getName(name);
//        return data;
    }
}
