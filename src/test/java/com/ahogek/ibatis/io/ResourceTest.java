package com.ahogek.ibatis.io;

import com.ahogek.ibatis.BaseDataTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * @author AhogeK ahogek@gmail.com
 * @since 2022-05-31 12:42:13
 */
class ResourceTest extends BaseDataTest {

    private static final ClassLoader CLASS_LOADER = ResourceTest.class.getClassLoader();

    @Test
    void shouldGetUrlForResource() throws IOException {
        URL url = Resources.getResourceURL(JPETSTORE_PROPERTIES);
        Assertions.assertTrue(url.toString().endsWith("jpetstore/jpetstore-hsqldb.properties"));
    }

    @Test
    void shouldGetUrlAsProperties() throws Exception {
        URL url = Resources.getResourceURL(CLASS_LOADER, JPETSTORE_PROPERTIES);
        Properties props = Resources.getUrlAsProperties(url.toString());
        Assertions.assertNotNull(props.getProperty("driver"));
        Assertions.assertEquals("org.hsqldb.jdbcDriver", props.getProperty("driver"));
    }
}
