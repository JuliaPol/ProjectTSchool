package com.tsystems.ecare.facade.impl;

import com.tsystems.ecare.dto.IdDTO;
import com.tsystems.ecare.facade.Facade;
import com.tsystems.ecare.service.Service;

import java.util.List;
import java.util.stream.Collectors;

public abstract class FacadeImpl<S, T extends IdDTO> implements Facade<T, S> {

    @Override
    public List<T> getAll() throws Exception {
        return convertList(getDefaultService().getAll());
    }

    @Override
    public void delete(T dto) throws Exception {
        getDefaultService().delete(convertToEntity(dto));
    }

    @Override
    public void update(T dto) throws Exception {
        getDefaultService().update(convertToEntity(dto));
    }

    @Override
    public void insert(T dto) throws Exception {
        getDefaultService().insert(convertToEntity(dto));
    }

    @Override
    public T get(Long id) throws Exception {
        return convertToDto(getDefaultService().get(id));
    }

    public List<T> convertList(List<S> entities) {
        return entities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    protected abstract S convertToEntity(T dto);

    protected abstract Service<S> getDefaultService();
}
