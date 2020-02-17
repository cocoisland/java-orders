package com.tony.customerorders.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tony.customerorders.models.Customer;
import com.tony.customerorders.models.Order;
import com.tony.customerorders.services.OrderService;
import com.tony.customerorders.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private OrderService orderService;
	private CustomerService customerService; // customerService is used in models.CustomerServiceImpl @Service
	
	// GET http://localhost:2020/customers/orders
	@GetMapping(value = "/orders", produces= {"application/json"})
	public ResponseEntity<?> listAllOrders(){
		List<Order> myList = orderService.findAll();
		return new ResponseEntity<>(myList, HttpStatus.OK);
	}
	
	// GET http://localhost:2020/customers/customer/7
	@GetMapping(value = "/customer/{id}", produces= {"application/json"})
	public ResponseEntity<?> getCustId(@PathVariable long id){
		Customer rtn = customerService.findById(id);
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	// SAVE http://localhost:2020/customers/customer
	@GetMapping(value = "/customer", consumes= {"application/json"})
	public ResponseEntity<?> addCustomer(@Valid				// ensure Valid json object
								@RequestBody Customer newcustomer)	// jackson serialize RequestBody into Customer object
	{
		customerService.save(newcustomer);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	
	// http://localhost:2020/customers/customer/77
	// http://localhost:2020/customers/namelike/mes
	// http://localhost:2020/customers/namelike/cin
}
