package com.tony.customerorders.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tony.customerorders.models.Order;
import com.tony.customerorders.models.Payment;
import com.tony.customerorders.repositories.OrderRepository;

@Service(value="orderService")
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepos;
	
	@Autowired
	private PaymentService paymentService;

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
		
		return orderRepos.findById(id).orElseThrow(()-> new EntityNotFoundException("Order Id "+id+" not found."));
	}

	@Transactional
	@Override
	public Order save(Order changeOrder) {
		Order newOrder = new Order();
		
		// if changeOrder contained Id then verify the Id with database.
		if (changeOrder.getOrdnum() != 0) {
			orderRepos.findById(changeOrder.getOrdnum()).orElseThrow(()->
				new EntityNotFoundException("Order received from the web contained ID "+changeOrder.getOrdnum()+
						"that can not be verified in database"));
			
			newOrder.setOrdnum(changeOrder.getOrdnum());
		}
	
		newOrder.setOrdamount(changeOrder.getOrdamount());
		newOrder.setOrderdescription(changeOrder.getOrderdescription());
		newOrder.setCustorders(changeOrder.getCustorders());
		
		//ManyToMany order payment relationship
		for (Payment p: changeOrder.getOrderspayments()) {
			Payment tmpPayment = paymentService.findPaymentById(p.getPaymentid());
			newOrder.addPayment(tmpPayment);
		}
		
		return orderRepos.save(newOrder);
	}

	@Transactional
	@Override
	public Order update(Order changeOrder, long id) {
		Order newOrder = orderRepos.findById(id).orElseThrow(()->
			new EntityNotFoundException("Can not update because Order Id "+id+" not found."));
		
		if (changeOrder.getOrdamount() != 0) 
			{newOrder.setOrdamount(changeOrder.getOrdamount());}
		if (changeOrder.getOrderdescription()!= null) 
			{newOrder.setOrderdescription(changeOrder.getOrderdescription());}
		if (changeOrder.getCustorders() != null) {newOrder.setCustorders(changeOrder.getCustorders());}
		
		if (changeOrder.getOrderspayments().size() > 0) {
			for (Payment p: changeOrder.getOrderspayments()) {
				// paymentService can find instanced object payment in memory
				// paymentRepos can only find payment entity in database
				Payment tmpPayment = paymentService.findPaymentById(p.getPaymentid());
				newOrder.addPayment(tmpPayment);
			}
		}
		
		return orderRepos.save(newOrder);
	}

	@Transactional
	@Override
	public void delete(long id) {
		if (orderRepos.findById(id).isPresent()) {
			// what happen to order id in orderspayments ManyToMany relationship?
			Order tmpOrder = orderRepos.findById(id).orElseThrow();
			for (Payment p: tmpOrder.getOrderspayments()) {
				tmpOrder.removePayment(p);
			}
			orderRepos.deleteById(id);
		} else {
			throw new EntityNotFoundException("Can not delete because Order Id"+id+" not found.");
		}
		
	}

	
}
