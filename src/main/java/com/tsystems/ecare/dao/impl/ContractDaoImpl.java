package com.tsystems.ecare.dao.impl;

import com.tsystems.ecare.dao.ContractDao;
import com.tsystems.ecare.entities.Contract;
import com.tsystems.ecare.entities.Option;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository("contractDao")
public class ContractDaoImpl extends JpaDaoImpl<Contract> implements ContractDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Option> getAllOption(Long id) {
        List<Option> options = new ArrayList<>();
        List<Option> options1 = entityManager.createNamedQuery("findAllOptionsByRate")
                .setParameter("id", id)
                .getResultList();
        options.addAll(options1);
        List<Option> options2 = entityManager.createNamedQuery("findAllOptionsByContract")
                .setParameter("id", id)
                .getResultList();
        options.addAll(options2);
        return options;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
