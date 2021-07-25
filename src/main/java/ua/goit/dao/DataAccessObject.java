package ua.goit.dao;

import java.sql.SQLException;
import java.util.List;

public interface DataAccessObject<T> {
    List<T> getAll() throws SQLException;
    T findById(Integer id) throws SQLException;
    T create(T entity) throws SQLException;
    T update(T entity) throws SQLException;
    void delete(Integer id) throws SQLException;
}
