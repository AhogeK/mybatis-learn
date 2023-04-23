package com.ahogek.ibatis.io;

import com.ahogek.ibatis.BaseDataTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
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

    @Test
    void shouldGetResourceAsProperties() throws Exception {
        Properties props = Resources.getResourceAsProperties(CLASS_LOADER, JPETSTORE_PROPERTIES);
        Assertions.assertNotNull(props.getProperty("driver"));
        Assertions.assertEquals("org.hsqldb.jdbcDriver", props.getProperty("driver"));
    }

    @Test
    void shouldGetUrlAsStream() throws Exception {
        URL url = Resources.getResourceURL(CLASS_LOADER, JPETSTORE_PROPERTIES);
        try (InputStream in = Resources.getUrlAsStream(url.toString())) {
            Assertions.assertNotNull(in);
        }
    }

    @Test
    void shouldGetUrlAsReader() throws Exception {
        URL url = Resources.getResourceURL(CLASS_LOADER, JPETSTORE_PROPERTIES);
        try (Reader in = Resources.getUrlAsReader(url.toString())) {
            Assertions.assertNotNull(in);
        }
    }

    @Test
    void shouldGetResourceAsStreamWithoutClassLoader() throws Exception {
        try (InputStream in = Resources.getResourceAsStream(JPETSTORE_PROPERTIES)) {
            Assertions.assertNotNull(in);
        }
    }

    @Test
    void shouldGetResourceAsStream() throws Exception {
        try (InputStream in = Resources.getResourceAsStream(CLASS_LOADER, JPETSTORE_PROPERTIES)) {
            Assertions.assertNotNull(in);
        }
    }

    @Test
    void shouldGetResourceAsReaderWithoutClassLoader() throws Exception {
        try (Reader in = Resources.getResourceAsReader(JPETSTORE_PROPERTIES)) {
            Assertions.assertNotNull(in);
        }
    }

    @Test
    void shouldGetResourceAsReader() throws Exception {
        try (Reader in = Resources.getResourceAsReader(CLASS_LOADER, JPETSTORE_PROPERTIES)) {
            Assertions.assertNotNull(in);
        }
    }

    @Test
    void shouldGetResourceAsFile() throws Exception {
        File file = Resources.getResourceAsFile(JPETSTORE_PROPERTIES);
        Assertions.assertTrue(file.getAbsolutePath().replace('\\', '/')
                .endsWith("jpetstore/jpetstore-hsqldb.properties"));
    }
}
