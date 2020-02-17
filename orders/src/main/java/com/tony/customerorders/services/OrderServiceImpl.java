package com.tony.customerorders.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tony.customerorders.models.Order;
import com.tony.customerorders.repositories.OrderRepository;

@Service(value="orderService")
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepos;

	@Override
	public List<Order> findAll() {
		List<Order> rtnList = new ArrayList<>();
		orderRepos.findAll()
			.iterator()
			.forEachRemaining(rtnList::add);
		return rtnList;
	}

	@Override
	public Order findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order save(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order update(Order order, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	
}
