package com.demo.springboot.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiaolei hu
 * @date 2018/10/27 13:51
 **/
//@SpringBootApplication(scanBasePackages = {"com.demo.springboot.dubbo"})
@SpringBootApplication
public class DubboConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
    }
}
