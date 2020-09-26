package com.dl.dao;

import com.dl.entity.User;
import com.dl.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface UserDao extends JpaRepository<User,Integer> {
}
