package com.ahogek.ibatis.logging;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author AhogeK ahogek@gmail.com
 * @since 2023-05-17 14:43:12
 */
public class LogFactory {

    /**
     * 该标记用于支持标记的日志实现。
     */
    public static final String MARKER = "AHOGEK_MYBATIS";

    /**
     * 日志构造器
     */
    private static Constructor<? extends Log> logConstructor;

    static {
        tryImplementation(LogFactory::useSlf4jLogging);
        tryImplementation(LogFactory::useCommonsLogging);
        tryImplementation(LogFactory::useLog4J2Logging);
        tryImplementation(LogFactory::useJdkLogging);
        tryImplementation(LogFactory::useNoLogging);
    }

    private LogFactory() {
        // 关闭构造方法
    }

    /**
     * 尝试使用指定的日志实现
     *
     * @param runnable 需要执行的日志实现任务
     */
    private static void tryImplementation(Runnable runnable) {
        if (logConstructor == null) {
            runnable.run();
        }
    }

    /**
     * 使用 slf4j 日志实现
     */
    public static synchronized void useSlf4jLogging() {
        setImplementation(com.ahogek.ibatis.logging.slf4j.Slf4jImpl.class);
    }

    /**
     * 使用 apache commons 日志实现
     */
    public static synchronized void useCommonsLogging() {
        setImplementation(com.ahogek.ibatis.logging.commons.JakartaCommonsLoggingImpl.class);
    }

    /**
     * 使用 log4j2 日志实现
     */
    public static synchronized void useLog4J2Logging() {
        setImplementation(com.ahogek.ibatis.logging.log4j2.Log4j2Impl.class);
    }

    /**
     * 使用 jdk 日志实现
     */
    public static synchronized void useJdkLogging() {
        setImplementation(com.ahogek.ibatis.logging.jdk14.Jdk14LoggingImpl.class);
    }

    /**
     * 使用标准输出日志实现
     */
    public static synchronized void useStdOutLogging() {
        setImplementation(com.ahogek.ibatis.logging.stdout.StdOutImpl.class);
    }

    /**
     * 使用无日志实现
     */
    public static synchronized void useNoLogging() {
        setImplementation(com.ahogek.ibatis.logging.nologging.NoLoggingImpl.class);
    }

    /**
     * 设置日志实现
     *
     * @param implClass 日志实现类
     */
    private static void setImplementation(Class<? extends Log> implClass) {
        try {
            Constructor<? extends Log> candidate = implClass.getConstructor(String.class);
            Log log = candidate.newInstance(LogFactory.class.getName());
            if (log.isDebugEnabled()) {
                log.debug("日志初始化使用 " + implClass + " 适配器实现。");
            }
            logConstructor = candidate;
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new LogException("设置日志实现时发生错误。原因是：" + e, e);
        }
    }

    /**
     * 根据类名获取日志
     *
     * @param clazz 类名
     * @return 日志
     */
    public static Log getLog(Class<?> clazz) {
        return getLog(clazz.getName());
    }

    /**
     * 根据字符串类名获取日志
     *
     * @param logger 字符串类名
     * @return 日志
     */
    public static Log getLog(String logger) {
        try {
            return logConstructor.newInstance(logger);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new LogException("创建" + logger + "日志失败, 原因：" + e, e);
        }
    }
}
