package com.tsystems.ecare.dao.impl;

import com.tsystems.ecare.dao.AddressDao;
import com.tsystems.ecare.entities.Address;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("addressDao")
public class AddressDaoImpl extends JpaDaoImpl<Address> implements AddressDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
