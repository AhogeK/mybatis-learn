package ahogek.com.github.ibatis.io;

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
     * 在类路径寻找类
     *
     * @param name - 需要寻找的类名
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
