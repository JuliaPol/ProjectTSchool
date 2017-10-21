package com.tsystems.ecare.dao.impl;

import com.tsystems.ecare.dao.ContractDao;
import com.tsystems.ecare.entities.Contract;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.entities.User;
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
        List<Option> options1 = entityManager.createNamedQuery(Contract.CONTRACT_FIND_ALL_OPTIONS_BY_RATE)
                .setParameter("id", id)
                .getResultList();
        options.addAll(options1);
//        List<Option> options2 = entityManager.createNamedQuery("findAllOptionsByContract")
//                .setParameter("id", id)
//                .getResultList();
        // options.addAll(options2);
        return options;
    }

    @Override
    public List<String> findAllContactsByUserId(String login) {
        return entityManager.createNamedQuery(Contract.CONTRACT_FIND_ALL_CONTACTS_BY_USER_ID, String.class)
                .setParameter("login", login)
                .getResultList();
    }

    @Override
    public Contract getContractByNumber(String number) {
        return entityManager.createNamedQuery(Contract.CONTRACT_FIND_CONTRACT_BY_NUMBER, Contract.class)
                .setParameter("number", number).getSingleResult();
    }

    @Override
    public User findUserByNumber(String number) {
        return entityManager.createNamedQuery(Contract.CONTRACT_FIND_USER_BY_NUMBER, User.class)
                .setParameter("number", number)
                .getSingleResult();
    }

    @Override
    public List<User> searchByNumber(String likeNumber, int limit) {
        return entityManager.createNamedQuery(Contract.CONTRACT_SEARCH_BY_NUMBER, User.class)
                .setMaxResults(limit)
                .setParameter("number", String.join("",likeNumber,"%"))
                .getResultList();
    }

    @Override
    public List<User> searchByName(String name, int limit) {
        return entityManager.createNamedQuery(Contract.CONTRACT_SEARCH_BY_NAME, User.class)
                .setMaxResults(limit)
                .setParameter("name", String.join("",name,"%"))
                .getResultList();
    }

    @Override
    public void updateContractWithDeletedRate(Long id) {
        entityManager.createNamedQuery(Contract.CONTRACT_UPDATE_WITH_DELETED_RATE)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
