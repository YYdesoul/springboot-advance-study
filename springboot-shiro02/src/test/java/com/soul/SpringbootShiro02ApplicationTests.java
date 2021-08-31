package com.soul;

import com.soul.service.UserService;
import com.soul.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootShiro02ApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void testGetUserByName() {
        System.out.println(userService.getUserByname("小红"));
    }

}
