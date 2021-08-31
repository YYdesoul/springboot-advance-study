package com.soul.service;

import com.soul.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public User getUserByname(String name);


}
