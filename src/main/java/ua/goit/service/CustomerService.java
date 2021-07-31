package ua.goit.service;

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
    private final CustomerDAO customerDAO = new CustomerDAO();

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        Customer customer = toCustomer(customerDTO);
        try {
            return fromCustomer(customerDAO.create(customer));
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public String delete(CustomerDTO customerDTO) {
        Customer customer = toCustomer(customerDTO);
        try {
            customerDAO.delete(customer.getCustomer_id());
            return "Your request has been processed successfully";
        } catch (SQLException e) {
            return "Please delete the entries in the Link section associated with this identifier.";
        }
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
        Customer customer = toCustomer(customerDTO);
        try {
            return fromCustomer(customerDAO.update(customer));
        } catch (SQLException e) {
            return null;
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
    public CustomerDTO getById(int id) {
        try {
            return fromCustomer(customerDAO.findById(id));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CustomerDTO> getAll() {
        List<Customer> customers = null;
        try {
            customers = customerDAO.getAll();
            List<CustomerDTO> customersDTO = new ArrayList<>();
            for (Customer customer:customers) {
                customersDTO.add(fromCustomer(customer));
            }
            return customersDTO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAll(CustomerDTO entity) throws SQLException {
        List<Customer> customers = customerDAO.getAll();
        List<CustomerDTO> customersDTO = new ArrayList<>();
        for (Customer customer:customers) {
            customersDTO.add(fromCustomer(customer));
        }
        return util.joinListElements(customersDTO);
    }

}
