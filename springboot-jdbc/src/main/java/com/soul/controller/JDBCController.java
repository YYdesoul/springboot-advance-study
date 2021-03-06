package com.soul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/userList")
    public List<Map<String, Object>> getUserList() {
        String sql = "select * from user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    @GetMapping("/addUser")
    public int addUser() {
        String sql = "insert into user(id, name, pwd) VALUES (10, '小明', '123456')";
        int update = jdbcTemplate.update(sql);
        return update;
    }

    @GetMapping("/deleteUser/{id}")
    public int deleteUser(@PathVariable int id) {
        String sql = "delete from user where id = ?";
        int update = jdbcTemplate.update(sql, id);
        return update;
    }

    @GetMapping("/updateUser/{id}")
    public int updateUser(@PathVariable int id) {
        String sql = "update user set name = ?, pwd = ? where id = " + id;
        Object[] objects = new Object[2];
        objects[0] = "小明2";
        objects[1] = "123";
        int update = jdbcTemplate.update(sql, objects);
        return update;
    }
}
