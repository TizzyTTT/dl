package com.dl.service;

import com.dl.entity.Permission;
import com.dl.entity.Role;
import com.dl.entity.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoginService {

    public User getUserByName(String name){
        return getMapByName(name);
    }

    private User getMapByName(String name){
        Permission Permission1 = new Permission(1, "query");
        Permission Permission2 = new Permission(2, "add");
        Set<Permission> PermissionSet = new HashSet<>();
        PermissionSet.add(Permission1);
        PermissionSet.add(Permission2);
        Role role = new Role(1, "admin", PermissionSet);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        User user = new User(1, "wsl", "123456", roleSet);
        Map<String, User> map = new HashMap<>();
        map.put(user.getUserName(), user);

        Set<Permission> PermissionSet1 = new HashSet<>();
        PermissionSet1.add(Permission1);
        Role role1 = new Role(2, "user", PermissionSet1);
        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(role1);
        User user1 = new User(2, "zhangsan", "123456", roleSet1);
        map.put(user1.getUserName(), user1);

        return map.get(name);
    }


}

