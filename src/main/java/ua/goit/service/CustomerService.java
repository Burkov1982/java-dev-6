package ua.goit.service;

import com.zaxxer.hikari.HikariDataSource;
import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.CustomerDAO;
import ua.goit.dao.model.Customer;
import ua.goit.dto.CustomerDTO;
import ua.goit.view.Util;

import java.sql.SQLException;

public class CustomerService implements Service<CustomerDTO>{
    private final Util util = new Util();
    private final HikariDataSource dataSource = DatabaseConnectionManager.getDataSource();
    private final CustomerDAO customerDAO = new CustomerDAO();

    @Override
    public String create(CustomerDTO customerDTO) {
        Customer customer = toCustomer(customerDTO);
        try {
            return customerDAO.create(customer).toString();
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String delete(CustomerDTO customerDTO) {
        Customer customer = toCustomer(customerDTO);
        try {
            customerDAO.delete(customer.getCustomer_id());
            return "Your request has been processed successfully";
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String update(CustomerDTO customerDTO) {
        Customer customer = toCustomer(customerDTO);
        try {
            return customerDAO.update(customer).toString();
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String update(CustomerDTO entity, CustomerDTO newEntity) {
        Customer customer = toCustomer(newEntity);
        try {
            return customerDAO.update(customer).toString();
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String getById(int id) throws SQLException {
        return customerDAO.findById(id).toString();
    }

    @Override
    public String getAll() throws SQLException {
        return util.joinListElements(customerDAO.getAll());
    }

    @Override
    public String getAll(CustomerDTO entity) throws SQLException {
        return util.joinListElements(customerDAO.getAll());
    }

    public static Customer toCustomer(CustomerDTO customerDTO){
        return new Customer(customerDTO.getCustomer_id(), customerDTO.getCustomer_name());
    }

    public static CustomerDTO fromCustomer(Customer customer){
        return new CustomerDTO(customer.getCustomer_id(), customer.getCustomer_name());
    }
}
