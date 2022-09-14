package cn.ahogek.ibatis.io;

import java.net.URL;

/**
 * 用于包装多个类加载器，使他们在一个地方工作
 *
 * @author AhogeK ahogek@gmail.com
 * @since 2022-05-31 13:12:30
 */
public class ClassLoaderWrapper {

    ClassLoader defaultClassLoader;

    ClassLoader systemClassLoader;

    /**
     * 构造器，为系统类加载器赋默认值
     */
    ClassLoaderWrapper() {
        try {
            systemClassLoader = ClassLoader.getSystemClassLoader();
        } catch (SecurityException ignored) {
            // 在 Google App 引擎上存在的访问异常
        }
    }

    /**
     * 使用当前的类路径获取一个资源 URL 对象
     *
     * @param resource - 要定位的资源
     * @return 资源对象或null
     */
    public URL getResourceAsURL(String resource) {
        return getResourceAsURL(resource, getClassLoaders(null));
    }

    /**
     * 通过指定的类加载器从类路径获取资源
     *
     * @param resource    - 需要被寻找的资源
     * @param classLoader - 第一个要被尝试的类加载器
     * @return 资源流或者null
     */
    public URL getResourceAsURL(String resource, ClassLoader classLoader) {
        return getResourceAsURL(resource, getClassLoaders(classLoader));
    }

    /**
     * 通过使用当前的类路径获取资源构建成URL对象
     *
     * @param resource    - 要定位的资源
     * @param classLoader - 要被检查使用的类加载器列表
     * @return 资源对象或null
     */
    URL getResourceAsURL(String resource, ClassLoader[] classLoader) {

        URL url;

        for (ClassLoader cl : classLoader) {

            if (null != cl) {

                // 通过传入的资源进行查找
                url = cl.getResource(resource);

                // 但是有些类加载器可能需要一个前缀 "/"，所以如果我们没有找到资源就把它加上再进行尝试
                if (null == url) {
                    url = cl.getResource("/" + resource);
                }

                // "它永远在我最后要找的地方"
                // 因为只有sb想要在找到后还要继续寻找，所以我们再这结束寻找
                if (null != url) {
                    return url;
                }
            }
        }

        // 在任何地方都没有找到
        return null;
    }

    /**
     * 在类路径寻找类
     *
     * @param name - 需要寻找的完整类名
     * @return - 类
     */
    public Object classForName(String name) throws ClassNotFoundException {
        return classForName(name, getClassLoaders(null));
    }

    /**
     * 通过指定的类加载器在类路径获取类（或者加载失败）
     *
     * @param name        - 需要找的类名
     * @param classLoader - 首要要尝试使用的类加载器
     * @return - 类对象
     * @throws ClassNotFoundException Duh.
     */
    public Class<?> classForName(String name, ClassLoader classLoader) throws ClassNotFoundException {
        return classForName(name, getClassLoaders(classLoader));
    }

    /**
     * 尝试从类加载器组中的加载器加载一个类
     *
     * @param name        - 需要被加载的类
     * @param classLoader - 尝试去加载类的类加载器数组
     * @return 类
     * @throws ClassNotFoundException - 记住聪明的 Judge Smails 说过的话：好吧，世界同样需要挖坑的人
     */
    Class<?> classForName(String name, ClassLoader[] classLoader) throws ClassNotFoundException {
        for (ClassLoader cl : classLoader) {
            if (null != cl) {
                try {
                    return Class.forName(name, true, cl);
                } catch (ClassNotFoundException e) {
                    // 直接忽略这里的操作直到所有的类加载器都无法找到类
                }
            }
        }
        throw new ClassNotFoundException("无法找到 " + name + " 类");
    }

    ClassLoader[] getClassLoaders(ClassLoader classLoader) {
        return new ClassLoader[]{
                classLoader,
                defaultClassLoader,
                Thread.currentThread().getContextClassLoader(),
                getClass().getClassLoader(),
                systemClassLoader
        };
    }
}