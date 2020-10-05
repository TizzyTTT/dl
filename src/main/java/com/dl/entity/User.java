package com.dl.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private String id;
	private String userName;
	private String password;
	/**
	 * 用户对应的角色集合
	 */
	private Set<Role> roles;
}
