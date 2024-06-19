package com.ilp04.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ilp04.entity.Customer;
import com.ilp04.service.CustomerService;
import com.ilp04.service.CustomerServiceImpl;

public class CustomerUtility {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Customer> customerList = getAllCustomers();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. View all customers");
            System.out.println("2. Insert a new customer");
            System.out.println("3. Update an existing customer");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    printCustomersInTableFormat(customerList);
                    break;
                case 2:
                    insertCustomer(customerList);
                    break;
                case 3:
                    updateCustomer(customerList);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static ArrayList<Customer> getAllCustomers() {
        CustomerService customerService = new CustomerServiceImpl();
        return customerService.getAllCustomers();
    }

    private static void insertCustomer(ArrayList<Customer> customerList) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the customer code: ");
        int customerCode = scanner.nextInt();

        System.out.print("Enter the first name: ");
        String firstName = scanner.next();

        System.out.print("Enter the last name: ");
        String lastName = scanner.next();

        System.out.print("Enter the address: ");
        scanner.nextLine();  // Consume newline
        String address = scanner.nextLine();

        System.out.print("Enter the phone number: ");
        long phoneNumber = scanner.nextLong();

        System.out.print("Enter the Aadhar number: ");
        long aadharNo = scanner.nextLong();

        Customer customer = new Customer(customerCode, firstName, lastName, address, phoneNumber, aadharNo);
        CustomerService customerService = new CustomerServiceImpl();
        customerService.insertIntoCustomer(customer, customerList);
    }

    private static void updateCustomer(ArrayList<Customer> customerList) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the customer code to update: ");
        int customerCode = scanner.nextInt();

        System.out.print("Enter the new first name: ");
        String firstName = scanner.next();

        System.out.print("Enter the new last name: ");
        String lastName = scanner.next();

        System.out.print("Enter the new address: ");
        scanner.nextLine();  // Consume newline
        String address = scanner.nextLine();

        System.out.print("Enter the new phone number: ");
        long phoneNumber = scanner.nextLong();

        System.out.print("Enter the new Aadhar number: ");
        long aadharNo = scanner.nextLong();

        Customer customer = new Customer(customerCode, firstName, lastName, address, phoneNumber, aadharNo);
        CustomerService customerService = new CustomerServiceImpl();
        customerService.updateCustomer(customer, customerList);
    }

    private static void printCustomersInTableFormat(List<Customer> customers) {
        // Print table header
        System.out.printf("%-20s | %-20s | %-30s | %-15s | %-15s%n", "First Name", "Last Name", "Address", "Phone Number", "Aadhar No");
        System.out.println("--------------------------------------------------------------------------------------------");

        // Print each customer in a tabular format
        for (Customer customer : customers) {
            System.out.printf("%-20s | %-20s | %-30s | %-15s | %-15s%n", 
                customer.getCustomerFirstname(), 
                customer.getCustomerLastname(), 
                customer.getAddress(), 
                customer.getPhNumber(), 
                customer.getAdharNo());
        }
        System.out.println("--------------------------------------------------------------------------------------------");
    }
}
