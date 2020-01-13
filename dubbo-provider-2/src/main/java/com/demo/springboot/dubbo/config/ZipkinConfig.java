//package com.demo.springboot.dubbo.config;
//
//import brave.context.slf4j.MDCScopeDecorator;
//import brave.propagation.CurrentTraceContext;
//import brave.spring.beans.CurrentTraceContextFactoryBean;
//import brave.spring.beans.TracingFactoryBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import zipkin2.reporter.beans.AsyncReporterFactoryBean;
//import zipkin2.reporter.beans.OkHttpSenderFactoryBean;
//import zipkin2.reporter.okhttp3.OkHttpSender;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class ZipkinConfig {
//    @Bean
//    public OkHttpSender sender() {
//        return OkHttpSender.create("http://localhost:9411/api/v2/spans");;
//    }
//
//    @Bean
//    public TracingFactoryBean tracing(AsyncReporterFactoryBean spanReporter) {
//        TracingFactoryBean tracingFactoryBean = new TracingFactoryBean();
//        tracingFactoryBean.setLocalServiceName("demo-provider");
//
//        tracingFactoryBean.setSpanReporter(spanReporter);
//    }
//
//    @Bean
//    public AsyncReporterFactoryBean spanReporter(OkHttpSender sender) {
//        AsyncReporterFactoryBean asyncReporterFactoryBean = new AsyncReporterFactoryBean();
//        asyncReporterFactoryBean.setSender(sender);
//        asyncReporterFactoryBean.setCloseTimeout(500);
//
//        return asyncReporterFactoryBean;
//    }
//
//    @Bean
//    public CurrentTraceContextFactoryBean currentTraceContext() {
//        List<CurrentTraceContext.ScopeDecorator> scopeDecoratorList = new ArrayList<>();
//        CurrentTraceContext.ScopeDecorator mdcScopeDecorator = MDCScopeDecorator.create();
//
//        scopeDecoratorList.add(mdcScopeDecorator);
//
//        CurrentTraceContextFactoryBean currentTraceContextFactoryBean = new CurrentTraceContextFactoryBean();
//        currentTraceContextFactoryBean.setScopeDecorators(scopeDecoratorList);
//
//        return currentTraceContextFactoryBean;
//
//    }
//}
