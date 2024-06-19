package com.ilp04.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp04.entity.Customer;
import com.ilp04.service.CustomerService;
import com.ilp04.service.CustomerServiceImpl;

public class CustomerUtility {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
        CustomerService customerService = new CustomerServiceImpl();
        int choice;

        do {
            System.out.println("Menu:");
            System.out.println("1. Display All Customers");
            System.out.println("2. Insert Customer");
            System.out.println("3. Update Customer");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    getAllCustomers(customerService);
                    break;
                case 2:
                    insertCustomer(scanner, customerService);
                    break;
                case 3:
                    updateCustomer(scanner, customerService);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
	}

	private static void getAllCustomers(CustomerService customerService) {
		ArrayList<Customer> customerList = customerService.getAllCustomer();
        for (Customer customer : customerList) {
            System.out.println("Customer Code: " + customer.getCustomerCode());
            System.out.println("First Name: " + customer.getCustomerFirstname());
            System.out.println("Last Name: " + customer.getCustomerLastname());
            System.out.println("Address: " + customer.getAddress());
            System.out.println("Phone Number: " + customer.getPhoneNumber());
            System.out.println("Adhar No: " + customer.getAdharNo());
            System.out.println();
	    }
	
	}
	
	private static void insertCustomer(Scanner scanner, CustomerService customerService) {
        System.out.print("Enter Customer Code: ");
        int customerCode = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over
        System.out.print("Enter First Name: ");
        String customerFirstname = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String customerLastname = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        long phoneNumber = scanner.nextLong();
        System.out.print("Enter Adhar No: ");
        long adharNo = scanner.nextLong();
        Customer customer = new Customer(customerCode, customerFirstname, customerLastname, address, phoneNumber, adharNo);
        int result = customerService.insertIntoCustomer(customer);
        if (result > 0) {
            System.out.println("Customer inserted successfully.");
        } else {
            System.out.println("Failed to insert customer.");
        }
    }
	
	private static void updateCustomer(Scanner scanner, CustomerService customerService) {
        System.out.print("Enter Customer Code to update: ");
        int customerCode = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over
        System.out.print("Enter New First Name: ");
        String customerFirstname = scanner.nextLine();
        System.out.print("Enter New Last Name: ");
        String customerLastname = scanner.nextLine();
        System.out.print("Enter New Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter New Phone Number: ");
        long phoneNumber = scanner.nextLong();
        System.out.print("Enter New Adhar No: ");
        long adharNo = scanner.nextLong();
        Customer customer = new Customer(customerCode, customerFirstname, customerLastname, address, phoneNumber, adharNo);
        int result = customerService.updateCustomer(customer);
        if (result > 0) {
            System.out.println("Customer updated successfully.");
        } else {
            System.out.println("Failed to update customer.");
        }
    }
}
