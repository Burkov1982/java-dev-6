package ua.goit.dao;

import com.zaxxer.hikari.HikariDataSource;
import ua.goit.dao.model.Company;
import ua.goit.dao.model.Link;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyDAO extends AbstractDAO<Company> {

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO companies (company_name, headquarters) VALUES (?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE companies SET company_name = ?, headquarters = ? WHERE company_id = ?";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "SELECT company_id, company_name, headquarters FROM companies WHERE company_id = ?";
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
        return "DELETE FROM companies WHERE company_id = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT company_id, company_name, headquarters FROM companies";
    }

    @Override
    protected void sendEntity(PreparedStatement statement, Company object) throws SQLException {
        if (object.getCompany_id()==0){
            statement.setString(1, object.getCompany_name());
            statement.setString(2, object.getHeadquarters());
        } else{
            statement.setString(1, object.getCompany_name());
            statement.setString(2, object.getHeadquarters());
            statement.setInt(3, object.getCompany_id());
        }
    }

    @Override
    protected Company getEntity(ResultSet resultSet) throws SQLException {
        Company company = new Company();
            company.setCompany_id(resultSet.getInt("company_id"));
            company.setCompany_name(resultSet.getString("company_name"));
            company.setHeadquarters(resultSet.getString("headquarters"));
        return company;
    }
}
