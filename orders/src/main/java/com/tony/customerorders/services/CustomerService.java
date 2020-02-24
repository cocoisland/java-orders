package com.tony.customerorders.services;

import java.util.ArrayList;
import java.util.List;

import com.tony.customerorders.models.Customer;
import com.tony.customerorders.models.Order;

public interface CustomerService {
	
	List<Customer> findAll();
	
	Customer findCustomerById(long id);
	
	//ArrayList<Customer> findByNameLike(String nameLike);
	List<Customer> findByNameLike(String nameLike);
	
	List<String> findQueryNameLike(String nameLike);
	
	Customer save(Customer customer);
	
	Customer update(Customer customer, long id);
	
	void delete(long id);

}
