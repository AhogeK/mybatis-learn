package com.ahogek.ibatis.logging.stdout;

import com.ahogek.ibatis.logging.Log;

/**
 * java 标准输出日志实现
 *
 * @author AhogeK ahogek@gmail.com
 * @since 2023-06-13 16:24:43
 */
public class StdOutImpl implements Log {

    public StdOutImpl(String clazz) {
        // 什么都不做
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public void error(String s, Throwable e) {
        System.err.println(s);
        e.printStackTrace(System.err);
    }

    @Override
    public void error(String s) {
        System.err.println(s);
    }

    @Override
    public void debug(String s) {
        System.out.println(s);
    }

    @Override
    public void trace(String s) {
        System.out.println(s);
    }

    @Override
    public void warn(String s) {
        System.out.println(s);
    }
}
