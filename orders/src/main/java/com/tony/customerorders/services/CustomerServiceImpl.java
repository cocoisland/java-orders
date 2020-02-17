package com.tony.customerorders.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tony.customerorders.models.Agent;
import com.tony.customerorders.models.Customer;
import com.tony.customerorders.models.Order;
import com.tony.customerorders.repositories.CustomerRepository;

@Transactional
@Service(value = "customerService")	// customerService is defined in CustomerController
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository custrepos;
	
	@Override
	public List<Customer> findAll() {
		List<Customer> rtnList = new ArrayList<>();
		custrepos.findAll()
			.iterator()
			.forEachRemaining(rtnList::add);
		return rtnList;
	}

	@Override
	public Customer findById(long id) {
		//return custrepos.findAllById(id);
		return null;
	}

	@Transactional
	@Override
	public Customer save(Customer customer) {
		Customer newCust = new Customer();
		newCust.setCustname(customer.getCustname());
		newCust.setCustcity(customer.getCustcity());
		newCust.setWorkingarea(customer.getWorkingarea());
		newCust.setCustcountry(customer.getCustcountry());
		newCust.setGrade(customer.getGrade());
		newCust.setOpeningamt(customer.getOpeningamt());
		newCust.setReceiveamt(customer.getReceiveamt());
		newCust.setPaymentamt(customer.getPaymentamt());
		newCust.setOutstandingamt(customer.getOutstandingamt());
		newCust.setPhone(customer.getPhone());
		newCust.setCustomeragent(customer.getCustomeragent());
		
		return custrepos.save(newCust);
	}

	@Override
	public Customer update(Customer customer, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	
}
