package cn.ahogek.ibatis.io;

import cn.ahogek.ibatis.BaseDataTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

/**
 * @author AhogeK cn@gmail.ahogek
 * @since 2022-05-31 12:42:13
 */
public class ResourceTest extends BaseDataTest {

    private static final ClassLoader CLASS_LOADER = ResourceTest.class.getClassLoader();

    @Test
    void shouldGetUrlForResource() throws IOException {
        URL url = Resources.getResourceURL(JPETSTORE_PROPERTIES);
        Assertions.assertTrue(url.toString().endsWith("jpetstore/jpetstore-hsqldb.properties"));
    }
}
