package ua.goit.dao;

import com.zaxxer.hikari.HikariDataSource;
import ua.goit.dao.model.Link;
import ua.goit.service.LinkService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LinksDAO extends AbstractDAO<Link>{

    private final LinkService linkService = new LinkService();

    public LinksDAO(HikariDataSource dataSource) {
        super(dataSource);
    }

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
        String uniquePart = null;
        String part = null;
        String values = null;
        try (Connection connection = dataSource.getConnection()){


            switch (entity.getTable()){
                case "customers_companies" -> {
                    uniquePart = String.format("customer_id = %s, company_id = %s, project_id = %s",
                            entity.getCustomer_id(), entity.getCompany_id(), entity.getProject_id());
                    part = "customer_id, company_id, project_id";
                    values = String.format("%d, %d, %d", entity.getCustomer_id(), entity.getCompany_id(),
                            entity.getProject_id());
                }
                case "project_developers" -> {
                    uniquePart = String.format("project_id = %s, developer_id = %s",
                            entity.getProject_id(), entity.getDeveloper_id());
                    part = "project_id, developer_id";
                    values = String.format("%d, %d", entity.getProject_id(), entity.getDeveloper_id());
                }
                case "developer_skills" -> {
                    uniquePart = String.format("skill_id = %s, developer_id = %s",
                            entity.getSkill_id(), entity.getDeveloper_id());
                    part = "skill_id, developer_id";
                    values = String.format("%d, %d", entity.getSkill_id(), entity.getDeveloper_id());
                }
            }
            switch (command){
                case "DELETE" -> {
                    return connection.prepareStatement(String.format(getDeleteQuery(), entity.getTable(), uniquePart));
                }
                case "CREATE" -> {
                    return connection.prepareStatement(String.format(getCreateQuery(), entity.getTable(),
                            part, values));
                }
                case "GET_ALL" -> {
                    return connection.prepareStatement(String.format(getSelectAllQuery(), entity.getTable()));
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


            switch (entity.getTable()){
                case "customers_companies" -> {
                    uniquePart = String.format("customer_id = %s AND company_id = %s AND project_id = %s",
                            entity.getCustomer_id(), entity.getCompany_id(), entity.getProject_id());
                    part = "customer_id, company_id, project_id";
                    values = String.format("%d, %d, %d", entity.getCustomer_id(), entity.getCompany_id(),
                            entity.getProject_id());
                }
                case "project_developers" -> {
                    uniquePart = String.format("project_id = %s, developer_id = %s",
                            entity.getProject_id(), entity.getDeveloper_id());
                    part = "project_id, developer_id";
                    values = String.format("%d, %d", entity.getProject_id(), entity.getDeveloper_id());
                }
                case "developer_skills" -> {
                    uniquePart = String.format("skill_id = %s, developer_id = %s",
                            entity.getSkill_id(), entity.getDeveloper_id());
                    part = "skill_id, developer_id";
                    values = String.format("%d, %d", entity.getSkill_id(), entity.getDeveloper_id());
                }
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

    @Override
    protected void sendEntity(PreparedStatement statement, Link object) throws SQLException {

    }

    @Override
    protected Link getEntity(ResultSet resultSet) throws SQLException {
        return null;
    }
}

