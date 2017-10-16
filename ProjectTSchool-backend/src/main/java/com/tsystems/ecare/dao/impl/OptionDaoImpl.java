package com.tsystems.ecare.dao.impl;

import com.tsystems.ecare.dao.OptionDao;
import com.tsystems.ecare.entities.Option;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository("optionDao")
public class OptionDaoImpl extends JpaDaoImpl<Option> implements OptionDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Option> getAllOptionsForCustomer(String number) {
        return entityManager.createNamedQuery(Option.OPTION_FIND_ALL_OPTIONS_FOR_CUSTOMER)
                .setParameter("number", number).getResultList();
    }

    @Override
    public List<Option> findAllOptionsInRateForCustomer(String number) {
        return entityManager.createNamedQuery(Option.OPTION_FIND_ALL_OPTIONS_IN_RATE_FOR_CUSTOMER)
                .setParameter("number", number)
                .getResultList();
    }

    @Override
    public Option findOptionByName(String name) {
        return entityManager.createNamedQuery(Option.OPTION_FIND_OPTION_BY_NAME, Option.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
