package com.dl.shiro;

import com.dl.entity.Permission;
import com.dl.entity.Role;
import com.dl.entity.User;
import com.dl.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Slf4j
//自定义Realm用于查询用户的角色和权限信息并保存到权限管理器：
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

//  权限配置
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("————权限认证————");
        String username = JWTUtil.getUsername(principalCollection.toString());

//        Set<String> permissions = adminPermissionService.listPermissionURLsByUser(username);

        User user = loginService.getUserByName(username);
        SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
        for(Role role :user.getRoles()){
            authInfo.addRole(role.getRoleName());
            for (Permission permission:role.getPermissions()){
                authInfo.addStringPermission(permission.getPermissionsName());
            }
        }
//        返回的内容就是层层嵌套的结构体，主要数据都是string类型 ：）
        return authInfo;
    }

//    filter收到了token，token重写了AuthenticationToken（覆盖两个方法就好啦!），所以直接进入下面这个方法体内，

//  认证配置
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null || !JWTUtil.verify(token, username)) {
            throw new AuthenticationException("token认证失败！");
        }
        String password = loginService.getUserByName(username).getPassword();
        if (password == null) {
            throw new AuthenticationException("该用户不存在！");
        }
        return new SimpleAuthenticationInfo(token, token, "MyRealm");
    }

    @ExceptionHandler
    @ResponseBody
    public String ErrorHandler(AuthorizationException e) {
        log.error("没有通过权限验证！", e);
        return "没有通过权限验证！";
    }


//    ====================================================
    /**
     * 必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }



}
