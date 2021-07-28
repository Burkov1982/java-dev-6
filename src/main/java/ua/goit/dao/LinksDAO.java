package ua.goit.dao;

import com.zaxxer.hikari.HikariDataSource;
import ua.goit.dao.model.Link;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LinksDAO extends AbstractDAO<Link>{
    @Override
    protected String getCreateQuery() {
        return "INSERT INTO %s (%s) VALUES (%s)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE %s SET %s WHERE %s";
    }

    @Override
    protected String getSelectByIdQuery() {
        return null;
    }

    @Override
    protected PreparedStatement enrichPreparedStatement(HikariDataSource dataSource, Link entity, String command) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            if ("GET_ALL".equals(command)) {
                switch (entity.getTable()) {
                    case "customers_companies":
                        return connection.prepareStatement(getAllCustomersCompanies());
                    case "developer_skills":
                        return connection.prepareStatement(getAllDeveloperSkills());
                    case "project_developers":
                        return connection.prepareStatement(getAllProjectDevelopers());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected PreparedStatement enrichUpdatePreparedStatement(HikariDataSource dataSource, Link entity, Link oldEntity) {
        String uniquePart = null;
        String part = null;
        String values = null;
        try (Connection connection = dataSource.getConnection()){

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM %s WHERE %s";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT %s FROM %s";
    }

    private String getAllCustomersCompanies(){
        return "SELECT customer_id, company_id, project_id FROM customers_companies";
    }

    private String getAllProjectDevelopers(){
        return "SELECT project_id, developer_id FROM project_developers";
    }

    private String getAllDeveloperSkills(){
        return "SELECT skill_id, developer_id FROM developer_skills";
    }
    @Override
    protected void sendEntity(PreparedStatement statement, Link object) throws SQLException {

    }

    @Override
    protected Link getEntity(ResultSet resultSet) throws SQLException {
        return null;
    }
}

