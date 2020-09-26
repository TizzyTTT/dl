package com.dl.controller;

import com.dl.entity.User;
import com.dl.service.UserService;
import com.dl.utils.message.Result;
import com.dl.utils.message.ResultFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurdController {

    @Autowired
    UserService userService;

    @PostMapping("/api/addUser")
    public Result addUser(@RequestBody User user){
        userService.addUser(user);
        return ResultFactory.buildSuccessResultWithMessage("添加用户成功");
    }

}
