package com.soul.service;

import com.soul.mapper.UserMapper;
import com.soul.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper usermapper;


    @Override
    public User getUserByname(String name) {
        return usermapper.getUserByName(name);
    }
}
