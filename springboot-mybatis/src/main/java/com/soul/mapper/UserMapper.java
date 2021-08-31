package com.soul.mapper;

import com.soul.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper // 方式一：表示是Mybatis的mapper类，方式二在启动类
@Repository // Dao层，托管到SpringBoot
public interface UserMapper {

    public List<User> getAllUsers();
}
