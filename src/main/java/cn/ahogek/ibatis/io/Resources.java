package cn.ahogek.ibatis.io;

import java.io.IOException;
import java.net.URL;

/**
 * 一个类加载器简化对资源的访问的类
 *
 * @author AhogeK cn@gmail.ahogek
 * @since 2022-05-31 13:01:03
 */
public class Resources {

    private static final ClassLoaderWrapper classLoaderWrapper = new ClassLoaderWrapper();

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
            throw new IOException("Could not find resource " + resource);
        }
        return url;
    }


}
