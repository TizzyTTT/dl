package com.dl.controller;

import com.dl.entity.User;
import com.dl.service.LoginService;
import com.dl.shiro.JWTUtil;
import com.dl.utils.message.Result;
import com.dl.utils.message.ResultFactory;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CurdController {
    @Autowired
    LoginService loginService;

    @PostMapping("/api/addUser")
    public Result addUser(@RequestBody User user){
//        userService.addUser(user);
        return ResultFactory.buildSuccessResultWithMessage("添加用户成功");
    }


    @GetMapping("/token")
    public Result login(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
        String realPassword = loginService.getUserByName(username).getPassword();
        if (realPassword == null) {
            return ResultFactory.buildFailResult("用户名错误");
        } else if (!realPassword.equals(password)) {
            return ResultFactory.buildFailResult("密码错误");
        } else {
            return ResultFactory.buildSuccessResultWithMessage(JWTUtil.createToken(username));
        }
    }

    @RequiresRoles("admin")
    @GetMapping("/adminWithToken")
    public Result hello() {
        return ResultFactory.buildSuccessResultWithMessage("adminWithToken");
    }

}
