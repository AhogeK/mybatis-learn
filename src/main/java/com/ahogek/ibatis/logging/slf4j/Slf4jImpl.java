package com.ahogek.ibatis.logging.slf4j;

import com.ahogek.ibatis.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.spi.LocationAwareLogger;

/**
 * @author AhogeK ahogek@gmail.com
 * @since 2023-05-17 15:59:39
 */
public class Slf4jImpl implements Log {

    private Log log;

    public Slf4jImpl(String clazz) {
        Logger logger = LoggerFactory.getLogger(clazz);

        if (logger instanceof LocationAwareLogger locationAwareLogger) {
            try {
                // slf4j >= 1.6 版本的方法签名
                logger.getClass().getMethod("log", Marker.class, String.class, int.class, String.class,
                        Object[].class, Throwable.class);
                log = new Slf4jLocationAwareLoggerImpl(locationAwareLogger);
                return;
            } catch (NoSuchMethodException e) {
                // 回退到 Slf4jLoggerImpl 失败。
            }
        }

        // Logger 不是 LocationAwareLogger 或 slf4j 版本 < 1.6
        log = new Slf4jLoggerImpl(logger);
    }

    @Override
    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    @Override
    public boolean isTraceEnabled() {
        return log.isTraceEnabled();
    }

    @Override
    public void error(String s, Throwable e) {
        log.error(s, e);
    }

    @Override
    public void error(String s) {
        log.error(s);
    }

    @Override
    public void debug(String s) {
        log.debug(s);
    }

    @Override
    public void trace(String s) {
        log.trace(s);
    }

    @Override
    public void warn(String s) {
        log.warn(s);
    }
}
