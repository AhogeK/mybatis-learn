package ahogek.com.github.ibatis.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author AhogeK ahogek@gmail.com
 * @since 2022-05-31 13:14:20
 */
class ClassLoaderWrapperTest {

    private final String CLASS_FOUND = "java.lang.Object";
    private ClassLoaderWrapper wrapper;

    @BeforeEach
    void beforeClassLoaderWrapperTest() {
        wrapper = new ClassLoaderWrapper();
    }

    @Test
    void classForName() throws ClassNotFoundException {
        assertNotNull(wrapper.classForName(CLASS_FOUND));
    }
}
