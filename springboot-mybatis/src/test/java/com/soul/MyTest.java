package com.soul;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MyTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void testDataSource() throws SQLException {
        System.out.println(dataSource.getClass());
    }

    @Test
    void contextLoads() throws SQLException {
        // 默认数据源：class com.zaxxer.hikari.HikariDataSource
//        System.out.println(dataSource.getClass());

        // 获得数据库连接
        String sql = "select * from user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
    }
}
