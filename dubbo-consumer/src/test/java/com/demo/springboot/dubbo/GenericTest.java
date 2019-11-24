//package com.demo.springboot.dubbo;
//
//import com.alibaba.dubbo.config.ReferenceConfig;
//import com.alibaba.dubbo.rpc.service.GenericService;
//import org.junit.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 用途描述
// *
// * @author 胡晓磊
// * @company xxx
// * @date 2019年11月22日 20:52 胡晓磊 Exp $
// */
//@SpringBootTest
//public class GenericTest {
//
//    @Test
//    public void test001() {
//        // 引用远程服务
//// 该实例很重量，里面封装了所有与注册中心及服务提供方连接，请缓存
//        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
//// 弱类型接口名
//        reference.setInterface("com.xxx.XxxService");
//        reference.setVersion("1.0.0");
//// 声明为泛化接口
//        reference.setGeneric(true);
//
//// 用org.apache.dubbo.rpc.service.GenericService可以替代所有接口引用
//        GenericService genericService = reference.get();
//
//// 基本类型以及Date,List,Map等不需要转换，直接调用
//        Object result = genericService.$invoke("getName", new String[]{"java.lang.String"}, new Object[]{"world"});
//
//        Map<String, Object> person = new HashMap<String, Object>();
//        person.put("name", "xxx");
//        person.put("password", "yyy");
//    }
//}
//
