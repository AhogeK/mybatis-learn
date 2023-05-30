package com.ahogek.ibatis.logging;

import com.ahogek.ibatis.logging.commons.JakartaCommonsLoggingImpl;
import com.ahogek.ibatis.logging.log4j2.Log4j2Impl;
import com.ahogek.ibatis.logging.slf4j.Slf4jImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author AhogeK ahogek@gmail.com
 * @since 2023-05-17 16:43:38
 */
class LogFactoryTest {

    @AfterAll
    static void restore() {
        LogFactory.useSlf4jLogging();
    }

    @Test
    void shouldUseSlf4jLogging() {
        LogFactory.useSlf4jLogging();
        Log log = LogFactory.getLog(Object.class);
        logSomething(log);
        assertEquals(log.getClass().getName(), Slf4jImpl.class.getName());
    }

    @Test
    void shouldUseCommonsLogging() {
        LogFactory.useCommonsLogging();
        Log log = LogFactory.getLog(Object.class);
        logSomething(log);
        assertEquals(log.getClass().getName(), JakartaCommonsLoggingImpl.class.getName());
    }

    @Test
    void shouldUseLog4j2Logging() {
        LogFactory.useLog4J2Logging();
        Log log = LogFactory.getLog(Object.class);
        logSomething(log);
        assertEquals(log.getClass().getName(), Log4j2Impl.class.getName());
    }

    private void logSomething(Log log) {
        log.warn("这是一条警告信息。");
        log.debug("这是一条调试信息。");
        log.error("这是一条错误信息。");
        log.error("这是一条错误信息。", new RuntimeException("这是一个运行时异常。"));
    }
}
