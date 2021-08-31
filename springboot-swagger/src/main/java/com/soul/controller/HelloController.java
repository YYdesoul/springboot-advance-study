package com.soul.controller;

import com.soul.pojo.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @ApiOperation(value = "方法名", notes = "方法描述")    // 方法描述
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "id", paramType = "query", required = true, value = "id"),
            @ApiImplicitParam(dataType = "int", name = "name", paramType = "query", required = true, value = "名字")
    })
    @ApiResponses({  // 设置状态码
            @ApiResponse(code = 1000,
                        message = "状态码描述",
                        response = User.class       // 反射返回类型
                        )
    })
    @GetMapping("/hello/{id}/{name}")
    public User hello(@ApiParam(required = true) @PathVariable int id,    // @ApiParam设置swagger，如果设置了ApiImplicitParam,这里的注释会被覆盖
                       @ApiParam(value = "名字") @PathVariable String name) { // value: 设置描述
        User user = new User(id, name);
        return user;
    }
}
