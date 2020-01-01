package com.demo.springboot.dubbo;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.Optional;
import java.util.UUID;

/**
 * 用途描述
 *
 * @author 胡晓磊
 * @company xxx
 * @date 2020年01月01日 18:43 胡晓磊 Exp $
 */
public class TraceIdUtil {

    private static final TransmittableThreadLocal<String> TRACE_ID = new TransmittableThreadLocal<>();

    private static final String HTTP_TYPE = "HTTP-";
    private static final String RPC_TYPE = "RPC-";

    public static String getTraceId() {
        return TRACE_ID.get();
    }

    /**
     * 获取
     *
     * @return traceid
     */
    public static String getTraceId(String type) {
        if (TRACE_ID.get() == null) {
            setTraceId(type + UUID.randomUUID().toString());
        }
        return Optional.ofNullable(TRACE_ID.get()).orElse("");
    }

    public static String getHttpTraceId() {
        return TraceIdUtil.getTraceId(HTTP_TYPE);
    }

    public static String getRpcTraceId() {
        return TraceIdUtil.getTraceId(RPC_TYPE);
    }

    public static void setTraceId(String traceId) {
        TRACE_ID.set(traceId);
    }

    public static void cleanTrace() {
        TRACE_ID.remove();
    }
}
