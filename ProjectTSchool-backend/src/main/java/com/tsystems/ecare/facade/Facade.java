package com.tsystems.ecare.facade;

import java.util.List;

public interface Facade<T, S> {
    List<T> convertList(List<S> entities);
    List<S> convertToEntitiesList(List<T> dtos);
    T convertToDto(S entity);
    S convertToEntity(T dto);
    List<T> getAll() throws Exception;
    void delete(T entity) throws Exception;
    void update(T entity) throws Exception;
    void insert(T entity) throws Exception;
    T get(Long id) throws Exception;
}
