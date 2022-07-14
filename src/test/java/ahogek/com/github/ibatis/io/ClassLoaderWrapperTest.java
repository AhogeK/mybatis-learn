package ahogek.com.github.ibatis.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author AhogeK ahogek@gmail.com
 * @since 2022-05-31 13:14:20
 */
class ClassLoaderWrapperTest {

    private final String CLASS_FOUND = "java.lang.Object";
    private final String CLASS_NOT_FOUND = "some.random.class.that.does.not.Exist";
    private ClassLoaderWrapper wrapper;
    private ClassLoader loader;

    @BeforeEach
    void beforeClassLoaderWrapperTest() {
        wrapper = new ClassLoaderWrapper();
        loader = getClass().getClassLoader();
    }

    @Test
    void classForName() throws ClassNotFoundException {
        assertNotNull(wrapper.classForName(CLASS_FOUND));
    }

    @Test
    void classForNameNotFound() {
        Assertions.assertThrows(ClassNotFoundException.class,
                () -> assertNotNull(wrapper.classForName(CLASS_NOT_FOUND)));
    }

    @Test
    void classForNameWithClassLoader() throws ClassNotFoundException {
        assertNotNull(wrapper.classForName(CLASS_FOUND, loader));
    }
}
