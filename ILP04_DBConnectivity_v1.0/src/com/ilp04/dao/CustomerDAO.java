package com.ilp04.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ilp04.entity.Customer;

public class CustomerDAO {
	
	//get connection
	public Connection getConnection()
	{
		String connectionURL = "jdbc:mysql://localhost:3306/bankdb?useSSL=false";
		String userName = "root";
		String password = "experion@123";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(connectionURL,userName,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	//close connection
	public Connection closeConnection(Connection connection) 
	{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	//get all customer details
	public ArrayList<Customer> getAllCustomers(Connection connection)
	{
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		Statement statement;
		ResultSet resultSet;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from customer");
			while(resultSet.next())
			{
				int customerCode = resultSet.getInt(1);
				String customerFirstname = resultSet.getString(2);
				String customerLastname = resultSet.getString(3);
				String address = resultSet.getString(4);
				long phoneNumber = resultSet.getLong(5);
				long adharNo = resultSet.getLong(6);
				Customer customer = new Customer(customerCode, customerFirstname, customerLastname, address,
						phoneNumber, adharNo);
				customerList.add(customer);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return customerList;
		
	}
	
	//insert new customer
	public int insertCustomer(Customer customer, Connection connection) {
		Statement statement = null;
	    int result = 0;
	    try {
	        statement = connection.createStatement();
	        String insertQuery = "INSERT INTO customer (customer_code, customer_firstname, customer_lastname, address, phone_number, adhar_no) " +
	                            "VALUES (" + customer.getCustomerCode() + ", '" + customer.getCustomerFirstname() + "', '" + 
	                            customer.getCustomerLastname() + "', '" + customer.getAddress() + "', " + 
	                            customer.getPhoneNumber() + ", " + customer.getAdharNo() + ")";
	        result = statement.executeUpdate(insertQuery);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (statement != null) {
	                statement.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return result;
	}
	
	//update existing customer
	public int updateCustomer(Customer customer, Connection connection) {
	    int result = 0;
	    String query = "UPDATE customer SET customer_firstname = ?, customer_lastname = ?, address = ?, phone_number = ?, adhar_no = ? WHERE customer_code = ?";
	    try {      
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customer.getCustomerFirstname());
            preparedStatement.setString(2, customer.getCustomerLastname());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setLong(4, customer.getPhoneNumber());
            preparedStatement.setLong(5, customer.getAdharNo());
            preparedStatement.setInt(6, customer.getCustomerCode());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return result;
	}
}

