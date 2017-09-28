package com.tsystems.ecare.dao.impl;

import com.tsystems.ecare.dao.ContractDao;
import com.tsystems.ecare.entities.ContractEntity;
import com.tsystems.ecare.entities.OptionEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContractDaoImpl extends JpaDaoImpl<ContractEntity> implements ContractDao{
    @PersistenceContext
    private EntityManager entityManager;

    public List<OptionEntity> getAllOption(Long id) {
        List<OptionEntity> options = new ArrayList<>();
        List<OptionEntity> options1 = entityManager.createNamedQuery("findAllOptionsByRate")
                .setParameter("id", id)
                .getResultList();
        options.addAll(options1);
        List<OptionEntity> options2 = entityManager.createNamedQuery("findAllOptionsByContract")
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
