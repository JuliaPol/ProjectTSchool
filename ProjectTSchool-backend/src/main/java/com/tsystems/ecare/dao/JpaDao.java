package com.tsystems.ecare.dao;

import java.util.List;

public interface JpaDao<T> {
    List<T> getAll();
    void delete(T entity);
    void update(T entity);
    void insert(T entity);
    T get(Long id);
    void refresh(T entity);
    void flush();
}