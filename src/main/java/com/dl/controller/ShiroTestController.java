package com.dl.controller;

import com.dl.entity.User;
import com.dl.service.LoginService;
import com.dl.utils.aspect.WebLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
// 该controller废弃不用！！！！！！！！！
@RestController
@Slf4j
public class ShiroTestController {

    @Autowired
    LoginService loginService;

//    通过weblog测试可以知道，进入方法体一定会触发log，若参数格式不对或被拦截，则无法触发log
//    http://localhost:8080/login?userName=wsl&passWord=123456&id=1
    @GetMapping("/login")
    @WebLog(description = "shiro 登录接口")
    public String login(User user){
        if(user.getUserName().equals("") || user.getPassword().equals("")){
            return "请输入用户名和密码";
        }
//  单位信息 用户信息  2->1 （单位获得唯一编码,排列组合,,,,）
//   用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword());
        try{
//            throw new AuthenticationException();
            subject.login(token);
//            subject.logout();
        }catch (UnknownAccountException e) {
            log.error("用户名不存在！", e);
            return "用户名不存在！";
        } catch (AuthenticationException e) {
            log.error("账号或密码错误！", e);
            return "账号或密码错误！";
        } catch (AuthorizationException e) {
            log.error("没有权限！", e);
            return "没有权限";
        }
        return "login success";

    }

    @RequiresRoles("admin")
    @GetMapping("/admin")
    @WebLog(description = "需要角色admin")
    public String admin() {
        return "admin success!";
    }

    @WebLog(description = "需要功能配置query")
    @RequiresPermissions("query")
    @GetMapping("/index")
    public String index() {
        return "index success!";
    }

    @WebLog(description = "需要功能配置add")
    @RequiresPermissions("add")
    @GetMapping("/add")
    public String add() {
        return "add success!";
    }
}


