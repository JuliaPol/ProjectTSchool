package com.tsystems.ecare.dao.impl;

import com.tsystems.ecare.dao.AddressDao;
import com.tsystems.ecare.entities.Address;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("addressDao")
public class AddressDaoImpl extends JpaDaoImpl<Address> implements AddressDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Address> getAll() {
        return null;
    }

    @Override
    public void delete(Address entity) {

    }

    @Override
    public void update(Address entity) {

    }

    @Override
    public void insert(Address entity) {

    }

    @Override
    public Address get(Long id) {
        return null;
    }

    @Override
    public void refresh(Address entity) {

    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
