package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.JpaDao;
import com.tsystems.ecare.entities.Contract;
import com.tsystems.ecare.service.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of {@link Service} interface.
 */

public abstract class ServiceImpl<T> implements Service<T> {
    @Override
    public List<T> getAll() throws Exception {
        return getDefaultDao().getAll();
    }

    @Transactional
    @Override
    public void delete(T entity) throws Exception {
        getDefaultDao().delete(entity);
    }

    @Transactional
    @Override
    public void update(T entity) throws Exception {
        getDefaultDao().update(entity);
    }

    @Transactional
    @Override
    public void insert(T entity) throws Exception {
        getDefaultDao().insert(entity);
    }

    @Override
    public T get(Long id) throws Exception {
        return getDefaultDao().get(id);
    }

    protected abstract JpaDao<T> getDefaultDao();
}
