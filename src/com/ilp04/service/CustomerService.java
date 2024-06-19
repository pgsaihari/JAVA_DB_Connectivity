package com.ilp04.service;

import java.util.ArrayList;

import com.ilp04.entity.Customer;

public interface CustomerService {
    ArrayList<Customer> getAllCustomers();
    void insertIntoCustomer(Customer customer, ArrayList<Customer> customerList);
    void updateCustomer(Customer customer, ArrayList<Customer> customerList);
}
