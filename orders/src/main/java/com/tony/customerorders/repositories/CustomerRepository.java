package com.tony.customerorders.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tony.customerorders.models.Agent;
import com.tony.customerorders.models.Customer;
import com.tony.customerorders.models.Order;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
 
	// declare JPA findBy auto-custom query generation.
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	ArrayList<Customer> findByCustnameContainingIgnoringCase(String nameLike);

	// hibernate query
	@Query(value="select custname from customers as c where c.custname like concat('%', :nameLike, '%')", 
			nativeQuery=true)
	ArrayList<String> findcustnameByQuery(String nameLike);
	
	//@Query("select custname from customer c where c.custname like %:nameLike%")
	//ArrayList<String> findcustnameByQuery(String nameLike);
	
	@Transactional
	@Modifying
	@Query(value="Delete from customers where custcode = :id", nativeQuery=true)
	void deleteCustomerByQueryId(long id);
	
	@Transactional
	@Modifying
	@Query(value="Insert into customers(custname, customersagent) values(:name, :agent)", nativeQuery=true)
	void insertCustomer(String name, Agent agent);
}
