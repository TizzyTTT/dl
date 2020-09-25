package com.dl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
public class User {
    private int id;
//    private UUID uuid;
    private String userName;
    private String passWord;
    private Set<Role> roles;

}
