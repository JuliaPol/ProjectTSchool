package com.tsystems.ecare.service;

import java.util.List;


/**
 * Service for CRUD  functions
 * @param <T>
 */

public interface Service<T> {
    List<T> getAll() throws Exception;
    void delete(T entity) throws Exception;
    void update(T entity) throws Exception;
    void insert(T entity) throws Exception;
    T get(Long id) throws Exception;
}
