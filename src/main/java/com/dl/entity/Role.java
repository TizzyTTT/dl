package com.dl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class Role {
    private int id;
    private String roleName;

    private Set<Permission> permissions;
}
