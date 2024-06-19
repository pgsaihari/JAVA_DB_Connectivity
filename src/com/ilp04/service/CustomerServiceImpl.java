package com.ilp04.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.ilp04.doa.CustomerDao;
import com.ilp04.entity.Customer;

public class CustomerServiceImpl implements CustomerService {

    @Override
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customerList = new ArrayList<>();
        CustomerDao customerDAO = new CustomerDao();
        Connection connection = customerDAO.getConnection();
        customerList = customerDAO.getAllCustomers(connection);
        customerDAO.closeConnection(connection);
        return customerList;
    }

    @Override
    public void insertIntoCustomer(Customer customer, ArrayList<Customer> customerList) {
        CustomerDao customerDAO = new CustomerDao();
        Connection connection = customerDAO.getConnection();
        customerDAO.insertIntoCustomer(connection, customer, customerList);
        customerDAO.closeConnection(connection);
    }

    @Override
    public void updateCustomer(Customer customer, ArrayList<Customer> customerList) {
        CustomerDao customerDAO = new CustomerDao();
        Connection connection = customerDAO.getConnection();
        customerDAO.updateCustomer(connection, customer, customerList);
        customerDAO.closeConnection(connection);
    }
}
