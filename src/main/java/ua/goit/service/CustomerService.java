package ua.goit.service;

import com.zaxxer.hikari.HikariDataSource;
import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.CustomerDAO;
import ua.goit.dao.model.Customer;
import ua.goit.dto.CustomerDTO;
import ua.goit.view.Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.goit.service.Converter.fromCustomer;
import static ua.goit.service.Converter.toCustomer;

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
    public List<CustomerDTO> getAll() throws SQLException {
        List<Customer> customers = customerDAO.getAll();
        List<CustomerDTO> customersDTO = new ArrayList<>();
        for (Customer customer:customers) {
            customersDTO.add(fromCustomer(customer));
        }
        return customersDTO;
    }

    @Override
    public List<CustomerDTO> getAll(CustomerDTO entity) throws SQLException {
        List<Customer> customers = customerDAO.getAll();
        List<CustomerDTO> customersDTO = new ArrayList<>();
        for (Customer customer:customers) {
            customersDTO.add(fromCustomer(customer));
        }
        return customersDTO;
    }

}
