package com.dl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    private int id;
    //    private UUID uuid;
    @Column(length = 32)
    private String userName;
    @Column(length = 32)
    private String password;

    @Transient
    private Set<Role> roles;


}
