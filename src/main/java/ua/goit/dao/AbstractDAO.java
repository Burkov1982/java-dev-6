package ua.goit.dao;

import com.zaxxer.hikari.HikariDataSource;
import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.model.Link;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.goit.config.Converter.toLink;

public abstract class AbstractDAO<T> implements DataAccessObject<T> {
    private static final HikariDataSource dataSource = DatabaseConnectionManager.getDataSource();

    protected HikariDataSource getConnectionManager() {
        return dataSource;
    }

    protected abstract String getCreateQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getSelectByIdQuery();

    protected abstract PreparedStatement enrichPreparedStatement(HikariDataSource dataSource, Link entity, String command);

    protected abstract PreparedStatement enrichUpdatePreparedStatement(HikariDataSource dataSource, Link entity, Link oldEntity);

    protected abstract String getDeleteQuery();

    protected abstract String getSelectAllQuery();

    protected abstract void sendEntity(PreparedStatement statement, T object) throws SQLException;

    protected abstract T getEntity(ResultSet resultSet) throws SQLException;

    @Override
    public T create(T entity) throws SQLException {
        String createQuery = getCreateQuery();
        T createdEntity;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)) {
            sendEntity(statement, entity);
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                createdEntity = findById(resultSet.getInt(1));
            } else {
                throw new SQLException("Problems with creating the object");
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
        return createdEntity;
    }

    @Override
    public T findById(Integer id) throws SQLException {
        T entity;
        String selectByIdQuery = getSelectByIdQuery();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectByIdQuery)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = getEntity(resultSet);
            } else {
                throw new SQLException("There is no object with such ID!");
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
        return entity;
    }

    public void deleteLink(Link entity) throws SQLException {
        try (PreparedStatement preparedStatement = enrichPreparedStatement(dataSource, entity, "DELETE")){
            preparedStatement.execute();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String deleteQuery = getDeleteQuery();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    public void updateLink(Link entity, Link oldEntity) throws SQLException {
        try (PreparedStatement preparedStatement = enrichUpdatePreparedStatement(dataSource, entity, oldEntity)){
            preparedStatement.executeUpdate();
        }
    }

    public void createLink(Link entity) throws SQLException {
        try (PreparedStatement preparedStatement = enrichPreparedStatement(dataSource, entity, "CREATE")){
            preparedStatement.execute();
        }
    }

    @Override
    public T update(T entity) throws SQLException {
        String updateQuery = getUpdateQuery();
        T updated;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS)) {
            sendEntity(statement, entity);
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                updated = findById(resultSet.getInt(1));
            } else {
                throw new SQLException("There are problems with updating the object");
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
        return updated;
    }

    public List<Link> getAllLinks(Link link) throws SQLException{
        try (PreparedStatement preparedStatement = enrichPreparedStatement(dataSource, link, "GET_ALL")){
            ResultSet resultSet = preparedStatement.executeQuery();
            return toLink(resultSet, link.getTable());
        }
    }

    @Override
    public List<T> getAll() throws SQLException {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getSelectAllQuery())){
            return getListByPreparedStatement(preparedStatement);
        }
    }

    protected List<T> getListByPreparedStatement(PreparedStatement statement) throws SQLException {
        List<T> entities = new ArrayList<>();
        T entity;
        try{
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                entity = getEntity(resultSet);
                entities.add(entity);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
        return entities;
    }
}
