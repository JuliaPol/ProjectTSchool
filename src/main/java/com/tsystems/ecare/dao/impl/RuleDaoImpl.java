package com.tsystems.ecare.dao.impl;

import com.tsystems.ecare.dao.RuleDao;
import com.tsystems.ecare.entities.Rule;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RuleDaoImpl extends JpaDaoImpl<Rule> implements RuleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
