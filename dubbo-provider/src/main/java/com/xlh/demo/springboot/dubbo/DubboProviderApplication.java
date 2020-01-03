package com.xlh.demo.springboot.dubbo;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiaolei hu
 * @date 2018/10/27 13:51
 **/
@EnableDubbo
@SpringBootApplication(scanBasePackages = {"com.xlh"})
@DubboComponentScan(basePackages = {"com.xlh"})
public class DubboProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }
}
