package com.tsystems.ecare.dao.impl;

import com.tsystems.ecare.dao.JpaDao;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class JpaDaoImpl<T> implements JpaDao<T> {
    private Class<T> persistentClass;


    public JpaDaoImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public List<T> getAll() {
        return getEntityManager().createQuery("SELECT obj FROM " + persistentClass.getSimpleName() + " obj", this.persistentClass).getResultList();
    }

    @Override
    public void delete(T entity) {
        getEntityManager().remove(getEntityManager().contains(entity) ? entity : getEntityManager().merge(entity));
    }

    @Override
    public void update(T entity) {
        getEntityManager().merge(entity);
    }

    @Override
    public void insert(T entity) {
        getEntityManager().persist(entity);
    }

    @Override
    public T get(Long id) {
        return getEntityManager().find(persistentClass, id);
    }

    @Override
    public void refresh(T entity) {
        getEntityManager().refresh(entity);
    }

    public abstract EntityManager getEntityManager();
}
