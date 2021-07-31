package ua.goit.service;

import java.sql.SQLException;
import java.util.List;

public interface Service<T> {
    List<T> getAll() throws SQLException;
    String getAll(T entity) throws SQLException;
    T getById(int id) throws SQLException;
    T create(T entity) throws SQLException;
    String delete(T entity) throws SQLException;
    T update(T entity) throws SQLException;
    String update(T entity, T newEntity) throws SQLException;
}
