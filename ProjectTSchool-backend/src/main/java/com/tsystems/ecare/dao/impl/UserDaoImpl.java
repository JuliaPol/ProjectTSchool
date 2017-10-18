package com.tsystems.ecare.dao.impl;

import com.tsystems.ecare.dao.UserDao;
import com.tsystems.ecare.entities.Role;
import com.tsystems.ecare.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends JpaDaoImpl<User> implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public User findByLogin(String login) {
        return entityManager.createNamedQuery(User.USER_FIND_BY_LOGIN, User.class)
                .setParameter("login", login)
                .getSingleResult();
    }

}
