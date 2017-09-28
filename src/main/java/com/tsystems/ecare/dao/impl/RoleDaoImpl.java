package com.tsystems.ecare.dao.impl;

import com.tsystems.ecare.dao.RoleDao;
import com.tsystems.ecare.entities.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImpl  {
    @PersistenceContext
    private EntityManager entityManager;

//    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
