package com.demo.springboot.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.rpc.*;
import com.demo.springboot.dubbo.TraceIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

/**
 * 用途描述
 *
 * @author 胡晓磊
 * @company xxx
 * @date 2020年01月01日 18:39 胡晓磊 Exp $
 */
@Activate(group = {Constants.CONSUMER}, order = -9999)
@Slf4j
public class ProviderTraceFilter implements Filter {
    private static final String TRACE_ID = "traceId";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try {
            String traceId = invocation.getAttachment(TRACE_ID);
//        MDC.put(TRACE_ID, traceId);
            if (!StringUtils.isBlank(traceId)) {
//            log.info("当前traceId:{}", traceId);
                log.info("当前traceId:{}", traceId);
                RpcContext.getContext().setAttachment(TRACE_ID, traceId);
                TraceIdUtil.setTraceId(traceId);
            } else {
//            log.warn("当前traceId无");
                // 调用无traceID
                RpcContext.getContext().setAttachment(TRACE_ID, TraceIdUtil.getRpcTraceId());
                TraceIdUtil.setTraceId(traceId);
            }

        } catch (Exception e) {
            log.error("ProviderTraceFilter fail, please check...", e.getMessage(), e);
        }

        Result result = invoker.invoke(invocation);

        try {
            TraceIdUtil.cleanTrace();
        } catch (Exception var6) {
            log.error("clean the dubbo trace error:", var6.getMessage(), var6);
        }

        return result;
    }
}
