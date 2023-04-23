package com.ahogek.ibatis.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

/**
 * 一个类加载器简化对资源的访问的类
 *
 * @author AhogeK com@gmail.ahogek
 * @since 2022-05-31 13:01:03
 */
public class Resources {

    private static final ClassLoaderWrapper classLoaderWrapper = new ClassLoaderWrapper();

    private Resources() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 返回类路径资源的URL
     *
     * @param resource 需要被寻找的资源
     * @return 资源URL对象
     * @throws IOException 如果资源不能被找到或无法被读取
     */
    public static URL getResourceURL(String resource) throws IOException {
        // issue #625 https://github.com/mybatis/mybatis-3/issues/625
        return getResourceURL(null, resource);
    }

    /**
     * 返回类路径上资源的URL对象
     *
     * @param loader   用于获取资源的类加载器
     * @param resource 要被寻找的资源
     * @return 资源 URL 对象
     * @throws IOException 如果资源没有被找到或无法读取
     */
    public static URL getResourceURL(ClassLoader loader, String resource) throws IOException {
        URL url = classLoaderWrapper.getResourceAsURL(resource, loader);
        if (url == null) {
            throw new IOException("无法找到资源 " + resource);
        }
        return url;
    }

    /**
     * 通过 URL 字符串获取 Properties 对象
     *
     * @param urlString - URL 字符串
     * @return 一个带有来自 URL 的数据的属性对象
     */
    public static Properties getUrlAsProperties(String urlString) throws IOException {
        Properties props = new Properties();
        try (InputStream in = getUrlAsStream(urlString)) {
            props.load(in);
        }
        return props;
    }

    /**
     * 通过 URL 字符串获取输入流对戏那个
     *
     * @param urlString - URL 字符串
     * @return URL 数据的输入流
     * @throws IOException 如果数据未找到或无法读取
     */
    private static InputStream getUrlAsStream(String urlString) throws IOException {
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        return conn.getInputStream();
    }
}
