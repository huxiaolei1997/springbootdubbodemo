//package com.demo.springboot.dubbo.config;
//
//import brave.context.slf4j.MDCScopeDecorator;
//import brave.propagation.CurrentTraceContext;
//import brave.spring.beans.CurrentTraceContextFactoryBean;
//import brave.spring.beans.TracingFactoryBean;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import zipkin2.reporter.beans.AsyncReporterFactoryBean;
//import zipkin2.reporter.okhttp3.OkHttpSender;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class ZipkinConfig {
//    @Value("${zipkin.url}")
//    private String url;
//
//    @Value("${zipkin.serviceName}")
//    private String serviceName;
//    /*
//        连接超时时间
//     */
//    @Value("${zipkin.connectTimeout}")
//    private int connecTimeout;
//
//    /*
//        是否启动压缩
//     */
//    @Value("${zipkin.compressionEnabled}")
//    private boolean compressionEnabled;
//
//    /*
//        上传 span 的间隔时间
//     */
//    @Value("${zipkin.flushInterval}")
//    private int flushInterval;
//
//    /*
//        读取超时时间
//     */
//    @Value("${zipkin.readTimeout}")
//    private int readTimeout;
//
//    @Value("${zipkin.samplerRate}")
//    private float samplerRate;
//
//
//    /**
//     * 配置 span 收集器
//     * @return
//     */
//    @Bean
//    public SpanCollector spanCollector() {
//        Config config = Config.builder()
//                .connectTimeout(connecTimeout)
//                .compressionEnabled(compressionEnabled)
//                .flushInterval(flushInterval)
//                .readTimeout(readTimeout)
//                .build();
//
//        return HttpSpanCollector.create(url, config, new EmptySpanCollectorMetricsHandler());
//    }
//
//    /**
//     * 配置采集率
//     * @param spanCollector
//     * @return
//     */
//    @Bean
//    public Brave brave(SpanCollector spanCollector) {
//        Builder builder = new Builder(serviceName);
//        builder.spanCollector(spanCollector)
//                .traceSampler(Sampler.create(samplerRate))
//                .build();
//        return builder.build();
//    }
//
//    /**
//     * @Description: 设置server的（服务端收到请求和服务端完成处理，并将结果发送给客户端）过滤器
//     * @Param:
//     * @return: 过滤器
//     */
//    @Bean
//    public BraveServletFilter braveServletFilter(Brave brave) {
//        BraveServletFilter filter = new BraveServletFilter(brave.serverRequestInterceptor(),
//                brave.serverResponseInterceptor(), new DefaultSpanNameProvider());
//        return filter;
//    }
//
//    /**
//     * @Description: 设置client的 rs和cs的拦截器
//     * @Param:
//     * @return: OkHttpClient 返回请求实例
//     */
//    @Bean
//    public OkHttpClient okHttpClient(Brave brave) {
//        OkHttpClient httpClient = new OkHttpClient.Builder()
//                .addInterceptor(new BraveOkHttpRequestResponseInterceptor(
//                        brave.clientRequestInterceptor(),
//                        brave.clientResponseInterceptor(),
//                        new DefaultSpanNameProvider())).build();
//        return httpClient;
//    }
////    @Bean
////    public OkHttpSender sender() {
////        return OkHttpSender.create("http://localhost:9411/api/v2/spans");
////    }
////
////    @Bean
////    public TracingFactoryBean tracing(AsyncReporterFactoryBean spanReporter) {
////        TracingFactoryBean tracingFactoryBean = new TracingFactoryBean();
////        tracingFactoryBean.setLocalServiceName("demo-provider");
////
////        tracingFactoryBean.setSpanReporter(spanReporter);
////    }
////
////    @Bean
////    public AsyncReporterFactoryBean spanReporter(OkHttpSender sender) {
////        AsyncReporterFactoryBean asyncReporterFactoryBean = new AsyncReporterFactoryBean();
////        asyncReporterFactoryBean.setSender(sender);
////        asyncReporterFactoryBean.setCloseTimeout(500);
////
////        return asyncReporterFactoryBean;
////    }
////
////    @Bean
////    public CurrentTraceContextFactoryBean currentTraceContext() {
////        List<CurrentTraceContext.ScopeDecorator> scopeDecoratorList = new ArrayList<>();
////        CurrentTraceContext.ScopeDecorator mdcScopeDecorator = MDCScopeDecorator.create();
////
////        scopeDecoratorList.add(mdcScopeDecorator);
////
////        CurrentTraceContextFactoryBean currentTraceContextFactoryBean = new CurrentTraceContextFactoryBean();
////        currentTraceContextFactoryBean.setScopeDecorators(scopeDecoratorList);
////
////        return currentTraceContextFactoryBean;
////
////    }
//}
