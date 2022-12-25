package com.dmdev.http.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K,T> {
    List<T> findALL();
    Optional<T> findByID(K id);
    boolean delete(K id);
    void update(T entity);
    T save(T entity);

}
