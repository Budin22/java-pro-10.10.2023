package org.example.dao;

import java.util.List;

public interface CrudDao<T, K> {
    T add(K item);

    boolean deleteById(int id);

    List<T> getAll();

    T getById(int id);
}
