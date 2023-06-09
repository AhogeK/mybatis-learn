package com.ahogek.ibatis.logging.slf4j;

import com.ahogek.ibatis.logging.Log;
import org.slf4j.Logger;

/**
 * Slf4j < 1.6 版本实现
 *
 * @author AhogeK ahogek@gmail.com
 * @since 2023-05-17 16:25:01
 */
public class Slf4jLoggerImpl implements Log {

    private final Logger log;

    public Slf4jLoggerImpl(Logger logger) {
        log = logger;
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
