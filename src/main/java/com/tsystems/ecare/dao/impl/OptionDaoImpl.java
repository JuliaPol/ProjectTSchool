package com.tsystems.ecare.dao.impl;

import com.tsystems.ecare.dao.OptionDao;
import com.tsystems.ecare.entities.Option;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("optionDao")
public class OptionDaoImpl extends JpaDaoImpl<Option> implements OptionDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
