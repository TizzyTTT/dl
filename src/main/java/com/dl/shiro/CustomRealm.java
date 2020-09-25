package com.dl.shiro;

import com.dl.entity.Permission;
import com.dl.entity.Role;
import com.dl.entity.User;
import com.dl.service.LoginService;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
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
        if(authenticationToken.getPrincipal().equals(""))return null;

        String name = authenticationToken.getPrincipal().toString();
        User user = loginService.getUserByName(name);
        if(user == null)return null;
        else {
            /**
             * @param principal   the 'primary' principal associated with the specified realm.
             * @param credentials the credentials that verify the given principal.
             * @param realmName   the realm from where the principal and credentials were acquired.
             *    public SimpleAuthenticationInfo(Object principal, Object credentials, String realmName) {
             *         this.principals = new SimplePrincipalCollection(principal, realmName);
             *         this.credentials = credentials;
             *     }
            */
            return new SimpleAuthenticationInfo(user.getUserName(), user.getPassWord().toString(), getName());
        }
    }

    @ExceptionHandler
    @ResponseBody
    public String ErrorHandler(AuthorizationException e) {
        log.error("没有通过权限验证！", e);
        return "没有通过权限验证！";
    }
}
