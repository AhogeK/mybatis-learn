package com.ahogek.ibatis.logging.nologging;

import com.ahogek.ibatis.logging.Log;

/**
 * 无日志功能实现
 *
 * @author AhogeK ahogek@gmail.com
 * @since 2023-05-31 19:46:08
 */
public class NoLoggingImpl implements Log {

    public NoLoggingImpl(String clazz) {
        // 什么都不做
    }

    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public void error(String s, Throwable e) {
        // 什么都不做
    }

    @Override
    public void error(String s) {
        // 什么都不做
    }

    @Override
    public void debug(String s) {
        // 什么都不做
    }

    @Override
    public void trace(String s) {
        // 什么都不做
    }

    @Override
    public void warn(String s) {
        // 什么都不做
    }
}
