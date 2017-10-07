package com.tsystems.ecare.facade.impl;

import com.tsystems.ecare.dto.IdDTO;
import com.tsystems.ecare.dto.converter.Converter;
import com.tsystems.ecare.entities.User;
import com.tsystems.ecare.facade.Facade;
import com.tsystems.ecare.service.Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return entities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    protected abstract T convertToDto(S entity);

    protected abstract Converter<S, T> getDefaultConverter();

    protected abstract Service<S> getDefaultService();
}
