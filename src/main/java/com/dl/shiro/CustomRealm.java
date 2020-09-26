package com.dl.shiro;

import com.dl.entity.Permission;
import com.dl.entity.Role;
import com.dl.entity.User;
import com.dl.service.LoginService;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.UUID;

@Slf4j
//自定义Realm用于查询用户的角色和权限信息并保存到权限管理器：
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

//  权限配置
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        获取登录用户名
        String name = (String)principalCollection.getPrimaryPrincipal();
//        获取用户实体对象
        User user = loginService.getUserByName(name);
//        添加角色和权限
        SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
        for(Role role :user.getRoles()){
            authInfo.addRole(role.getRoleName());
            for (Permission permission:role.getPermissions()){
                authInfo.addStringPermission(permission.getPermissionName());
            }
        }
//        返回的内容就是层层嵌套的结构体，主要数据都是string类型 ：）
        return authInfo;
    }

//  认证配置
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("============用户验证==============");
        //从token中获取信息,此token只是shiro用于身份验证的,并非前端传过来的token.
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = loginService.getUserByName(username).getPassword();

        if (null == password) {
            throw new AuthenticationException("doGetAuthenticationInfo中的用户名不对");
        } else if (!password.equals(new String(token.getPassword()))){
            throw new AuthenticationException("doGetAuthenticationInfo中的密码不对");
        }
        //组合一个验证信息
        System.out.println("token.getPrincipal()默认返回的username======"+token.getPrincipal());
        System.out.println("getName()"+getName());
        SimpleAuthenticationInfo info =
                new SimpleAuthenticationInfo(token.getPrincipal(),password,getName());
        return info;

//        if(authenticationToken.getPrincipal().equals(""))return null;
//        String name = authenticationToken.getPrincipal().toString();
//        User user = loginService.getUserByName(name);
//        if(user == null)return null;
//        else {
//            return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword().toString(), getName());
//        }
    }

    @ExceptionHandler
    @ResponseBody
    public String ErrorHandler(AuthorizationException e) {
        log.error("没有通过权限验证！", e);
        return "没有通过权限验证！";
    }
}
