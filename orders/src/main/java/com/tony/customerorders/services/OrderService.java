package com.tony.customerorders.services;

import java.util.List;

import com.tony.customerorders.models.Order;

public interface OrderService {

	List<Order> findAll();
	
	Order findById(long id);
	
	Order save(Order order);
	
	Order update(Order order, long id);
	
	void delete(long id);

}
