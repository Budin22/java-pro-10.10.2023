package org.example.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T, K> {
    T add(K item) throws SQLException;

    boolean deleteById(int id) throws SQLException;

    List<T> getAll() throws SQLException;

    T getById(int id) throws SQLException;
}
