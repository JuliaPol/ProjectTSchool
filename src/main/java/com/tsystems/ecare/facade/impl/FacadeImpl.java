package com.tsystems.ecare.facade.impl;

import com.tsystems.ecare.dto.IdDTO;
import com.tsystems.ecare.dto.converter.Converter;
import com.tsystems.ecare.facade.Facade;
import com.tsystems.ecare.service.Service;

import java.util.ArrayList;
import java.util.List;

public abstract class FacadeImpl<S, T extends IdDTO> implements Facade<T> {
    @Override
    public List<T> getAll() throws Exception {
        return convertList(getDefaultService().getAll());
    }

    @Override
    public void delete(T entity) throws Exception {
        getDefaultService().delete(getDefaultConverter().to(entity));
    }

    @Override
    public void update(T entity) throws Exception {
        getDefaultService().update(getDefaultConverter().to(entity));
    }

    @Override
    public void insert(T entity) throws Exception {
        getDefaultService().insert(getDefaultConverter().to(entity));
    }

    @Override
    public T get(Long id) throws Exception {
        return getDefaultConverter().from(getDefaultService().get(id));
    }

    protected List<T> convertList(List<S> entities) {
        List<T> result = new ArrayList<>();
        Converter<S, T> converter = getDefaultConverter();
        for (S s : entities) {
            result.add(converter.from(s));
        }
        return result;
    }

    protected abstract Converter<S, T> getDefaultConverter();

    protected abstract Service<S> getDefaultService();
}
