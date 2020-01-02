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
 * @date 2020年01月01日 18:38 胡晓磊 Exp $
 */
@Activate(group = {Constants.CONSUMER}, order = Integer.MIN_VALUE)
@Slf4j
public class ConsumerTraceFilter implements Filter {
    private static final String TRACE_ID = "traceId";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        try {
            String traceId = TraceIdUtil.getRpcTraceId();
            MDC.put(TRACE_ID, traceId);
            log.info("consumer当前traceId:{}", traceId);
            RpcContext.getContext().setAttachment(TRACE_ID, traceId);
        }
        catch (Exception var8) {
            log.error("ConsumerTraceFilter fail, please check...", var8.getMessage(), var8);
        }

        try {
            Result result = invoker.invoke(invocation);
            return result;
        } catch (Exception e) {
            log.error("invoke dubbo service error ", e.getMessage(), e);
            throw e;
        }
    }
}
