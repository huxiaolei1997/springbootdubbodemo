package com.xlh.demo.springboot.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 用途描述
 *
 * @author 胡晓磊
 * @company xxx
 * @date 2019年11月22日 22:03 胡晓磊 Exp $
 */
public class Main {
    public static void main(String[] args) {
//        aysncInvoke();
        syncInvoke();
    }

    /**
     * 同步调用
     */
    public static void syncInvoke() {
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
        System.out.println(result);

        Map<String, Object> params = new HashMap<>(16);
        params.put("class", "UserDto");
        params.put("userName", "测试名称");
        Object result2 = genericService.$invoke("addUser", new String[]{"UserDto"}, new Object[]{params});
        System.out.println(JSON.toJSONString(result2));

        System.exit(0);
    }

    public static void aysncInvoke() {
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
        // 注意这里 不能设置 async 为 true ， 否则异步调用第一次返回值为null
        referenceConfig.setAsync(true);
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
        System.out.println("此次调用结果立即返回 null，不要使用" + result);

        Future<String> future = RpcContext.getContext().getFuture();

        try {
            result = future.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        System.out.println(result);
        System.exit(0);
    }
}
