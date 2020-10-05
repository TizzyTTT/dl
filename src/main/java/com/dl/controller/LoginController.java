package com.dl.controller;


import com.dl.entity.User;
import com.dl.utils.aspect.WebLog;
import com.dl.utils.aspect.WebLogAspect;
import com.dl.utils.message.Result;
import com.dl.utils.message.ResultFactory;
import io.swagger.annotations.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@RestController
@Validated
public class LoginController {

    @GetMapping("/api/admin/login")
    @ApiOperation(value = "登录接口方法说明")
    @WebLog(description = "用户访问了登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",paramType = "query",required = true,dataType = "String"),
            @ApiImplicitParam(name = "password",value = "密码",paramType = "query",required = true,dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code=404,message="这行代码提醒如何写返回码注释")
    })
    public Result login(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println(username +" "+password);
        return ResultFactory.buildFailResult(null);
    }

    @PostMapping("/api/admin/logout")
    @ApiOperation(value = "注销接口方法说明")
    public Result logout(@RequestBody @Valid User user) {
        System.out.println(user.toString());
        return ResultFactory.buildSuccessResult(null);
    }

    @GetMapping("/api/admin/valid")
    @ApiOperation(value = "测试校验功能")
    public Result valid(@RequestParam("age") @Max(value = 22) int age,
                        @RequestParam("name") @NotNull String name,
                        @RequestParam("abc") @NotEmpty String abc){
        System.out.println(age+" "+name+" "+abc);
        return ResultFactory.buildSuccessResult(null);
    }



}




