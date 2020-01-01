package com.demo.springboot.dubbo.converter;

import org.apache.log4j.pattern.LoggingEventPatternConverter;
import org.apache.log4j.spi.LoggingEvent;

/**
 * 用途描述
 *
 * @author 胡晓磊
 * @company xxx
 * @date 2020年01月01日 20:28 胡晓磊 Exp $
 */
public class traceIdConverter extends LoggingEventPatternConverter {
    @Override
    public void format(LoggingEvent event, StringBuffer toAppendTo) {

    }
}
