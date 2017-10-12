package com.tsystems.ecare.dao.impl;

import com.tsystems.ecare.dao.RateDao;
import com.tsystems.ecare.entities.Rate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("rateDao")
public class RateDaoImpl extends JpaDaoImpl<Rate> implements RateDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Rate findByName(String name) {
        return entityManager.createNamedQuery(Rate.RATE_FIND_BY_NAME, Rate.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<Rate> findAllForCustomer(String number) {
        return entityManager.createNamedQuery(Rate.RATE_FIND_ALL_FOR_CUSTOMER, Rate.class)
                .setParameter("number", number).getResultList();
    }

    @Override
    public Rate findForCustomerByNumber(String number) {
        return entityManager.createNamedQuery(Rate.RATE_FIND_FOR_CUSTOMER_BY_NUMBER, Rate.class)
                .setParameter("number", number)
                .getSingleResult();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
