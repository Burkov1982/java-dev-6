package ua.goit.dao;

import com.zaxxer.hikari.HikariDataSource;
import ua.goit.dao.model.Customer;
import ua.goit.dao.model.Link;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO extends AbstractDAO<Customer> {
    @Override
    protected String getCreateQuery() {
        return "INSERT INTO customers (customer_name) VALUES (?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE customers SET customer_name = ? WHERE customer_id = ?";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "SELECT customer_id, customer_name FROM customers WHERE customer_id = ?";
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
        return "DELETE FROM customers WHERE customer_id = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT customer_id, customer_name FROM customers";
    }

    @Override
    protected void sendEntity(PreparedStatement statement, Customer object) throws SQLException {
        if (object.getCustomer_id()==null){
            statement.setString(1, object.getCustomer_name());
        } else {
            statement.setString(1, object.getCustomer_name());
            statement.setInt(2, object.getCustomer_id());
        }
    }

    @Override
    protected Customer getEntity(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomer_id(resultSet.getInt("customer_id"));
        customer.setCustomer_name(resultSet.getString("customer_name"));
        return customer;
    }
}
