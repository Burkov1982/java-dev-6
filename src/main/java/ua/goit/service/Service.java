package ua.goit.service;

import java.sql.SQLException;

public interface Service<T> {
    String getAll() throws SQLException;
    String getAll(T entity) throws SQLException;
    String getById(int id) throws SQLException;
    String create(T entity) throws SQLException;
    String delete(T entity) throws SQLException;
    String update(T entity) throws SQLException;
    String update(T entity, T newEntity) throws SQLException;
}
