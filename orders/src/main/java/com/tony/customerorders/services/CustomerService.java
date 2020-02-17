package com.tony.customerorders.services;

import java.util.List;

import com.tony.customerorders.models.Customer;
import com.tony.customerorders.models.Order;

public interface CustomerService {
	
	List<Customer> findAll();
	
	Customer findById(long id);
	
	Customer save(Customer customer);
	
	Customer update(Customer customer, long id);
	
	void delete(long id);

}
