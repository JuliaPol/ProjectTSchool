package com.tsystems.ecare.dao.impl;

import com.tsystems.ecare.dao.UserDao;
import com.tsystems.ecare.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserDaoImpl  {
    @PersistenceContext
    private EntityManager entityManager;

//    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

}
