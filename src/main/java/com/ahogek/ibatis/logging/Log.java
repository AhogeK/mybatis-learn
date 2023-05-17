package com.ahogek.ibatis.logging;

/**
 * @author AhogeK ahogek@gmail.com
 * @since 2023-05-17 14:45:21
 */
public interface Log {

    boolean isDebugEnabled();

    boolean isTraceEnabled();

    void error(String s, Throwable e);

    void error(String s);

    void debug(String s);

    void trace(String s);

    void warn(String s);
}
