package com.tsystems.ecare.dao.impl;

import com.tsystems.ecare.dao.RateDao;
import com.tsystems.ecare.entities.Rate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("rateDao")
public class RateDaoImpl extends JpaDaoImpl<Rate> implements RateDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Rate findByName(String name) {
        return entityManager.createNamedQuery("findByName", Rate.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
