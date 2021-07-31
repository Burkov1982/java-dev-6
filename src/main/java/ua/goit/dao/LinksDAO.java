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
            } else if ("CREATE".equals(command)) {
                switch (entity.getTable()) {
                    case "customers_companies" -> {
                        PreparedStatement statement = connection.prepareStatement(createCustomersCompanies());
                        statement.setInt(1, entity.getCustomer_id());
                        statement.setInt(2, entity.getCompany_id());
                        statement.setInt(3, entity.getProject_id());
                        return statement;
                    }
                    case "developer_skills" -> {
                        PreparedStatement statement1 = connection.prepareStatement(createDeveloperSkills());
                        statement1.setInt(1, entity.getSkill_id());
                        statement1.setInt(2, entity.getDeveloper_id());
                        return statement1;
                    }
                    case "project_developers" -> {
                        PreparedStatement statement2 = connection.prepareStatement(createProjectDevelopers());
                        statement2.setInt(1, entity.getProject_id());
                        statement2.setInt(2, entity.getDeveloper_id());
                        return statement2;
                    }
                }
            } else if ("DELETE".equals(command)) {
                switch (entity.getTable()) {
                    case "customers_companies" -> {
                        PreparedStatement statement = connection.prepareStatement(deleteCustomersCompanies());
                        statement.setInt(1, entity.getCustomer_id());
                        statement.setInt(2, entity.getCompany_id());
                        statement.setInt(3, entity.getProject_id());
                        return statement;
                    }
                    case "developer_skills" -> {
                        PreparedStatement statement1 = connection.prepareStatement(deleteDeveloperSkills());
                        statement1.setInt(1, entity.getSkill_id());
                        statement1.setInt(2, entity.getDeveloper_id());
                        return statement1;
                    }
                    case "project_developers" -> {
                        PreparedStatement statement2 = connection.prepareStatement(deleteProjectDevelopers());
                        statement2.setInt(1, entity.getProject_id());
                        statement2.setInt(2, entity.getDeveloper_id());
                        return statement2;
                    }
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private String deleteProjectDevelopers() {
        return "DELETE FROM project_developers WHERE project_id=? AND developer_id=?";
    }

    private String deleteDeveloperSkills() {
        return "DELETE FROM developer_skills WHERE skill_id=? AND developer_id=?";
    }

    private String deleteCustomersCompanies() {
        return "DELETE FROM customers_companies WHERE customer_id=? AND company_id=? AND project_id=?";
    }

    private String createProjectDevelopers() {
        return "INSERT INTO project_developers (project_id, developer_id) VALUES (?, ?)";
    }

    private String createDeveloperSkills() {
        return "INSERT INTO developer_skills (skill_id, developer_id) VALUES (?, ?)";
    }

    private String createCustomersCompanies() {
        return "INSERT INTO customers_companies (customer_id, company_id, project_id) VALUES (?, ?, ?)";
    }

    @Override
    protected PreparedStatement enrichUpdatePreparedStatement(HikariDataSource dataSource, Link entity, Link oldEntity) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            switch (entity.getTable()) {
                case "customers_companies":
                    PreparedStatement statement = connection.prepareStatement
                            ("UPDATE customers_companies SET customer_id=?, company_id=?, project_id=? WHERE" +
                                    "customer_id=? AND company_id=? AND project_id=?");
                    statement.setInt(1, entity.getCustomer_id());
                    statement.setInt(2, entity.getCompany_id());
                    statement.setInt(3, entity.getProject_id());
                    statement.setInt(4, oldEntity.getCustomer_id());
                    statement.setInt(5, oldEntity.getCompany_id());
                    statement.setInt(6, oldEntity.getProject_id());
                    return statement;
                case "developer_skills":
                    PreparedStatement statement1 = connection.prepareStatement
                            ("UPDATE developer_skills SET skill_id=?, developer_id=? " +
                                    "WHERE skill_id=? AND developer_id=?");
                    statement1.setInt(1, entity.getSkill_id());
                    statement1.setInt(2, entity.getDeveloper_id());
                    statement1.setInt(3, oldEntity.getSkill_id());
                    statement1.setInt(4, oldEntity.getDeveloper_id());
                    return statement1;
                case "project_developers":
                    PreparedStatement preparedStatement = connection.prepareStatement
                            ("UPDATE project_developers SET project_id=?, developer_id=? " +
                                    "WHERE project_id=? AND developer_id=?");
                    preparedStatement.setInt(1, entity.getProject_id());
                    preparedStatement.setInt(2, entity.getDeveloper_id());
                    preparedStatement.setInt(3, oldEntity.getProject_id());
                    preparedStatement.setInt(4, oldEntity.getDeveloper_id());
                    return preparedStatement;
            }
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

