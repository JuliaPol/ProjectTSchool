package com.tsystems.ecare.dao.impl;

import com.tsystems.ecare.dao.CustomerDao;
import com.tsystems.ecare.entities.CustomerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomerDaoImpl extends JpaDaoImpl<CustomerEntity> implements CustomerDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

}
