package com.ilp04.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ilp04.dao.CustomerDAO;
import com.ilp04.entity.Customer;

public class CustomerServiceImpl implements CustomerService {

	@Override
	public ArrayList<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		CustomerDAO customerDAO = new CustomerDAO();
		Connection connection = customerDAO.getConnection();
		customerList = customerDAO.getAllCustomers(connection);
		customerDAO.closeConnection(connection);
		return customerList;
	}

	
	public int insertIntoCustomer(Customer customer) {
		CustomerDAO customerDAO = new CustomerDAO();
        Connection connection = customerDAO.getConnection();
        int result = customerDAO.insertCustomer(customer, connection);
        // Close the connection
        customerDAO.closeConnection(connection);
        return result;
	}
	
	public int updateCustomer(Customer customer) {
		CustomerDAO customerDAO = new CustomerDAO();
        Connection connection = customerDAO.getConnection();
        int result = customerDAO.updateCustomer(customer, connection);
        // Close the connection
        customerDAO.closeConnection(connection);
        return result;
	}

}
