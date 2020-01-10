package com.demo.springboot.dubbo;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.yyigou.ddc.common.dubbo.trace.interceptor.RequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xiaolei hu
 * @date 2018/10/27 13:51
 **/
//@SpringBootApplication(scanBasePackages = {"com.demo.springboot.dubbo"})
@EnableDubbo
@DubboComponentScan(basePackages = {"com.demo.springboot"})
@SpringBootApplication(scanBasePackages = {"com.demo.springboot"})
public class DubboConsumerApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor());
    }
}
