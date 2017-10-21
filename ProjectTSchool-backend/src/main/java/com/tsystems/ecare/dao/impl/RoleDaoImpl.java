package com.tsystems.ecare.dao.impl;

import com.tsystems.ecare.dao.RoleDao;
import com.tsystems.ecare.entities.Role;
import com.tsystems.ecare.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository("roleDao")
public class RoleDaoImpl extends JpaDaoImpl<Role> implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllCustomers() {
        return entityManager.createNamedQuery("Role.findAllUsersByRole")
                .getResultList();
    }
    @Override
    public Role getByName(String name) {
        return entityManager.createNamedQuery("Role.findByName", Role.class)
                .setParameter("name", name).getSingleResult();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
