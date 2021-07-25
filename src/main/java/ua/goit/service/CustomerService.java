package ua.goit.service;

import ua.goit.dao.model.Customer;
import ua.goit.dto.CustomerDTO;
import ua.goit.view.ViewMessages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class CustomerService implements Service<CustomerDTO>{
    private final ViewMessages viewMessages = new ViewMessages();

    @Override
    public void create(CustomerDTO customerDTO) throws SQLException {
        Customer customer = toCustomer(customerDTO);
        customerDAO.create(customer);
    }

    @Override
    public void delete(CustomerDTO customerDTO) throws SQLException {
        customerDAO.delete(toCustomer(customerDTO));
    }

    @Override
    public void update(CustomerDTO customerDTO) throws SQLException {
        Customer customer = toCustomer(customerDTO);
        customerDAO.update(customer);
    }

    @Override
    public void update(CustomerDTO entity, CustomerDTO newEnity) throws SQLException {

    }

    @Override
    public String getById(int id) throws SQLException {
        return customerDAO.findById(id).toString();
    }

    @Override
    public String getAll(){
        return viewMessages.joinListCustomers(customerDAO.getAll());
    }

    @Override
    public String getAll(CustomerDTO entity) {
        return null;
    }

    public static Customer toCustomer(CustomerDTO customerDTO){
        return new Customer(customerDTO.getCustomer_id(), customerDTO.getCustomer_name());
    }

    public static CustomerDTO fromCustomer(Customer customer){
        return new CustomerDTO(customer.getCustomer_id(), customer.getCustomer_name());
    }

    public static LinkedList<Customer> toCustomer(ResultSet resultSet){
        try{
            LinkedList<Customer> customers = new LinkedList<>();
            while (resultSet.next()){
                Customer customer = new Customer();
                customer.setCustomer_id(resultSet.getInt("customer_id"));
                customer.setCustomer_name(resultSet.getString("customer_name"));
                customers.addLast(customer);
            }
            return customers;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }
}
