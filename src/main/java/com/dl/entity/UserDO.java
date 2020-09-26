package com.dl.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
public class UserDO {
    private String user_name;
    private String password;
}
