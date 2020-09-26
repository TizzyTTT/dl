package com.dl.service;

import com.dl.dao.UserDao;
import com.dl.entity.User;
import com.dl.entity.UserDO;
import org.hibernate.SQLQuery;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @PersistenceContext
    EntityManager em;

    public void addUser(User user){
        userDao.save(user);
    }


    public List<UserDO> testBean(){
        String sql = "SELECT user_name, password FROM user";
        Query query = em.createNativeQuery(sql);
        List<UserDO> list = query.unwrap(SQLQuery.class)
                .setResultTransformer(Transformers.aliasToBean(UserDO.class))
                .list();
        return list;
    }

}
