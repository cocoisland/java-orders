package com.tony.customerorders.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tony.customerorders.models.Customer;
import com.tony.customerorders.models.Order;
import com.tony.customerorders.services.OrderService;
import com.tony.customerorders.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerService customerService; // customerService is used in models.CustomerServiceImpl @Service
	
	// GET http://localhost:2020/customers/orders
	@GetMapping(value = "/orders", produces= {"application/json"})
	public ResponseEntity<?> listAllOrders(){
		List<Order> myList = orderService.findAll();
		return new ResponseEntity<>(myList, HttpStatus.OK);
	}
	
	// GET http://localhost:2020/customers/customer/7
	@GetMapping(value = "/customer/{id}", produces= {"application/json"})
	public ResponseEntity<?> getCustId(@PathVariable Long id){
		Customer rtn = customerService.findCustomerById(id);
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	/*
	 * * @param newCustomer A complete new customer to add including agents and orders.
     *                      Agent, Orders must already exist.
     * @return A location header with the URI to the newly created customer and a status of CREATED
     * @see CustomerService#save(Customer) CustomerService.save(Customer)
	 */
	// SAVE/Post http://localhost:2020/customers/customer
	@PostMapping(value = "/customer", consumes= {"application/json"})
	public ResponseEntity<?> addCustomer(@Valid				// ensure Valid json object
								@RequestBody Customer newcustomer)	// jackson serialize RequestBody into Customer object
	{
		// id is not set by Post
		newcustomer.setCustcode(0);
		customerService.save(newcustomer);
		
		// create location header for newly created customer object
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newCustUri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{custcode}")
				.buildAndExpand(newcustomer.getCustcode())
				.toUri();
		responseHeaders.setLocation(newCustUri);
		
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}
	
	/*
	 * * @param updateFullCustomer A complete Customer including Agent and customer orders to be used to
     *                         replace the Customer. Orders types must already exist.
     * @param custcode     The primary key of the customer you wish to replace.
     * @return status of OK
     * @see CustomerService#save(Customer) CustomerService.save(Customer)
	 */
	// PUT http://localhost:2020/customers/customer/77
	@PutMapping(value="customer/{id}", consumes= {"application/json"})
	public ResponseEntity<?> putCust(@RequestBody Customer updatedCustomer, @PathVariable long id) {
		
		customerService.update(updatedCustomer, id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/*
	 * Updates the customer record associated with the given id with the provided data. 
	 * Only the provided fields are affected.
     * Just adds Orders to this restaurant. Orders must already exist
	 */
	@PatchMapping(value="customer/{id}", consumes= {"application/json"})
	public ResponseEntity<?> patchCust(@RequestBody Customer updatedCustomer, @PathVariable long id) {
		customerService.update(updatedCustomer, id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	// DELETE http://localhost:2020/customers/customer/77
	@DeleteMapping(value="/customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable long id) {
		
		customerService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	// http://localhost:2020/customers/customer/77
	// http://localhost:2020/customers/namelike/mes
	// http://localhost:2020/customers/namelike/bo
	@GetMapping(value="/namelike/{nameLike}", produces= {"application/json"})
	public ResponseEntity<?> findCustnamelike(@PathVariable String nameLike) {
		List<Customer> customerNameLike = customerService.findByNameLike(nameLike);
		return new ResponseEntity<>(customerNameLike, HttpStatus.OK);
	}
	
	// http://localhost:2020/customers/querynamelike/bo
	@GetMapping(value="/querynamelike/{nameLike}", produces= {"application/json"})
	public ResponseEntity<?> findQueryName(@PathVariable String nameLike) {
		List<String> nameQuery = customerService.findQueryNameLike(nameLike);
		return new ResponseEntity<>(nameQuery, HttpStatus.OK);
	}
	
}
