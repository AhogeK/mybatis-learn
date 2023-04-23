package com.ahogek.ibatis.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * 一个类加载器简化对资源的访问的类
 *
 * @author AhogeK ahogek@gmail.com
 * @since 2022-05-31 13:01:03
 */
public class Resources {

    private static final ClassLoaderWrapper classLoaderWrapper = new ClassLoaderWrapper();

    /**
     * 在调用getResourceAsReader时要使用的字符集。null表示使用系统默认值
     */
    private static Charset charset;

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
     * 将类路径上的资源作为Stream对象返回
     *
     * @param resource 要查找的资源
     * @return 被找到的资源
     * @throws IOException 如果无法找到或读取该资源
     */
    public static InputStream getResourceAsStream(String resource) throws IOException {
        return getResourceAsStream(null, resource);
    }

    /**
     * 将类路径上的资源作为Stream对象返回
     *
     * @param loader   用于获取资源的类加载器
     * @param resource 要查找的资源
     * @return 被找到的资源
     * @throws IOException 如果无法找到或读取该资源
     */
    public static InputStream getResourceAsStream(ClassLoader loader, String resource) throws IOException {
        InputStream in = classLoaderWrapper.getResourceAsStream(resource, loader);
        if (in == null) {
            throw new IOException("无法找到资源 " + resource);
        }
        return in;
    }

    /**
     * 将类路径上的资源作为Reader对象返回
     *
     * @param loader   用于获取资源的类加载器
     * @param resource 要查找的资源
     * @return 被找到的资源
     * @throws IOException 如果无法找到或读取该资源
     */
    public static Reader getResourceAsReader(ClassLoader loader, String resource) throws IOException {
        Reader reader;
        if (charset == null) {
            reader = new InputStreamReader(getResourceAsStream(loader, resource));
        } else {
            reader = new InputStreamReader(getResourceAsStream(loader, resource), charset);
        }
        return reader;
    }

    /**
     * 将类路径上的资源作为Properties对象返回
     *
     * @param loader   用于获取资源的类加载器
     * @param resource 要查找的资源
     * @return 被找到的资源
     * @throws IOException 如果无法找到或读取该资源
     */
    public static Properties getResourceAsProperties(ClassLoader loader, String resource) throws IOException {
        Properties props = new Properties();
        try (InputStream in = getResourceAsStream(loader, resource)) {
            props.load(in);
        }
        return props;
    }

    /**
     * 通过 URL 字符串获取 Properties 对象
     *
     * @param urlString URL 字符串
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
     * @param urlString URL 字符串
     * @return URL 数据的输入流
     * @throws IOException 如果数据未找到或无法读取
     */
    public static InputStream getUrlAsStream(String urlString) throws IOException {
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        return conn.getInputStream();
    }

    /**
     * 通过 URL 字符串获取 Reader 对象
     *
     * @param urlString URL 字符串
     * @return URL 数据的 Reader 对象
     * @throws IOException 如果数据未找到或无法读取
     */
    public static Reader getUrlAsReader(String urlString) throws IOException {
        Reader reader;
        if (charset == null) {
            reader = new InputStreamReader(getUrlAsStream(urlString));
        } else {
            reader = new InputStreamReader(getUrlAsStream(urlString), charset);
        }
        return reader;
    }

    public static Charset getCharset() {
        return charset;
    }

    public static void setCharset(Charset charset) {
        Resources.charset = charset;
    }
}
