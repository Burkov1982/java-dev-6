package ua.goit.dao;

import com.zaxxer.hikari.HikariDataSource;
import ua.goit.dao.model.Developer;
import ua.goit.dao.model.Link;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeveloperDAO extends AbstractDAO<Developer> {
    protected DeveloperDAO(HikariDataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO developers (first_name, last_name, gender, salary) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE developers SET first_name = ?, last_name = ?, gender = ?, salary = ? WHERE developer_id = ?";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "SELECT developer_id, first_name, last_name, gender, salary FROM developers WHERE developer_id = ?";
    }

    @Override
    protected PreparedStatement enrichPreparedStatement(HikariDataSource dataSource, Link entity, String command) {
        return null;
    }

    @Override
    protected PreparedStatement enrichUpdatePreparedStatement(HikariDataSource dataSource, Link entity, Link oldEntity) {
        return null;
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM developers WHERE developer_id = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT developer_id, first_name, last_name, gender, salary FROM developers";
    }

    @Override
    protected void sendEntity(PreparedStatement statement, Developer object) throws SQLException {
        if (object.getDeveloper_id()==null){
            statement.setString(1, object.getFirst_name());
            statement.setString(2, object.getLast_name());
            statement.setString(3, object.getGender());
            statement.setInt(4, object.getSalary());
        } else {
            statement.setString(1, object.getFirst_name());
            statement.setString(2, object.getLast_name());
            statement.setString(3, object.getGender());
            statement.setInt(4, object.getSalary());
            statement.setInt(5, object.getDeveloper_id());
        }
    }

    @Override
    protected Developer getEntity(ResultSet resultSet) throws SQLException {
        Developer developer = new Developer();
        developer.setDeveloper_id(resultSet.getInt("developer_id"));
        developer.setFirst_name(resultSet.getString("first_name"));
        developer.setLast_name(resultSet.getString("last_name"));
        developer.setGender(resultSet.getString("gender"));
        developer.setSalary(resultSet.getInt("salary"));
        return developer;
    }
}
