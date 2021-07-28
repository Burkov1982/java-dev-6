package ua.goit.service;

import java.sql.SQLException;
import java.util.List;

public interface Service<T> {
    List<T> getAll() throws SQLException;
    List<T> getAll(T entity) throws SQLException;
    String getById(int id) throws SQLException;
    String create(T entity) throws SQLException;
    String delete(T entity) throws SQLException;
    String update(T entity) throws SQLException;
    String update(T entity, T newEntity) throws SQLException;
}
