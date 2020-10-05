package com.dl.test;

import com.alibaba.fastjson.JSON;
import com.dl.dao.UserDAO;
import com.dl.entity.User;
import com.dl.entity.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.query.Param;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDAO dao;


    @PersistenceContext
    private EntityManager em;


////    @Before
//    public void before() {
//        User user = new User();
//        user.setId("1");
//        user.setUserName("fengqy");
//        user.setPassword("123456");
//        dao.save(user);
//
//        user = new User();
//        user.setId("3");
//        user.setUserName("bubai");
//        user.setPassword("123456");
//        dao.save(user);
//
//        user.setId("5");
//        user.setUserName("wentian");
//        user.setPassword("123456");
//        dao.save(user);
//    }
////    @Test
//    public void testAdd() {
//        User user = new User();
//        user.setId("2");
//        user.setUserName("renwox");
//        user.setPassword("123456");
//        dao.save(user);
//        user = new User();
//        user.setId("4");
//        user.setUserName("linghuc");
//        user.setPassword("123456");
//        dao.save(user);
//    }


}
