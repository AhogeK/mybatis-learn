package com.ahogek.ibatis.datasource.unpooled;

import org.junit.jupiter.api.Test;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author AhogeK ahogek@gmail.com
 * @since 2023-10-30 15:46:44
 */
class UnpooledDataSourceTest {

    @Test
    void shouldNotRegisterTheSameDriverMultipleTimes() throws Exception {
        UnpooledDataSource dataSource = new UnpooledDataSource("org.hsqldb.jdbcDriver", "jdbc:hsqldb:mem:multipledrivers",
                "sa", "");
        dataSource.getConnection().close();
        int before = countRegisteredDrivers();
        dataSource = new UnpooledDataSource("org.hsqldb.jdbcDriver", "jdbc:hsqldb:mem:multipledrivers", "sa", "");
        dataSource.getConnection().close();
        assertEquals(before, countRegisteredDrivers());
    }

    int countRegisteredDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        int count = 0;
        while (drivers.hasMoreElements()) {
            drivers.nextElement();
            count++;
        }
        return count;
    }
}
