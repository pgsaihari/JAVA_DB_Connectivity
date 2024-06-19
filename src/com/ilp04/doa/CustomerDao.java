package com.ilp04.doa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ilp04.entity.Customer;

public class CustomerDao {

    public Connection getConnection() {
        String connectionURL = "jdbc:mysql://localhost:3306/bankdb?useSSL=false";
        String userName = "root";
        String password = "experion@123";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionURL, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Customer> getAllCustomers(Connection connection) {
        ArrayList<Customer> customerList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");
            while (resultSet.next()) {
                int customerCode = resultSet.getInt(1);
                String customerFirstname = resultSet.getString(2);
                String customerLastname = resultSet.getString(3);
                String address = resultSet.getString(4);
                long phNumber = resultSet.getLong(5);
                long adharNo = resultSet.getLong(6);
                Customer customer = new Customer(customerCode, customerFirstname, customerLastname, address, phNumber, adharNo);
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public void insertIntoCustomer(Connection connection, Customer customer, ArrayList<Customer> customerList) {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO customer (customer_firstname, customer_lastname, address, phone_no, adhar_no) VALUES ('" +
                customer.getCustomerFirstname() + "', '" +
                customer.getCustomerLastname() + "', '" +
                customer.getAddress() + "', " +
                customer.getPhNumber() + ", " +
                customer.getAdharNo() + ")";
            statement.executeUpdate(query);
            customerList.add(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCustomer(Connection connection, Customer customer, ArrayList<Customer> customerList) {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE customer SET customer_firstname = '" + customer.getCustomerFirstname() +
                           "', customer_lastname = '" + customer.getCustomerLastname() +
                           "', address = '" + customer.getAddress() +
                           "', phone_no = " + customer.getPhNumber() +
                           ", adhar_no = " + customer.getAdharNo() +
                           " WHERE customer_code = " + customer.getCustomerCode();
            statement.executeUpdate(query);

            for (int i = 0; i < customerList.size(); i++) {
                if (customerList.get(i).getCustomerCode() == customer.getCustomerCode()) {
                    customerList.set(i, customer);
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
