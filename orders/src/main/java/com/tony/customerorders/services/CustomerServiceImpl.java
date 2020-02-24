package com.tony.customerorders.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

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
	
	@Autowired
	private OrderService orderService;
	
	@Override
	public List<Customer> findAll() {
		List<Customer> rtnList = new ArrayList<>();
		custrepos.findAll()
			.iterator()
			.forEachRemaining(rtnList::add);
		return rtnList;
	}

	@Override
	public Customer findCustomerById(long id) throws EntityNotFoundException {
		
		//return custrepos.findBycustcode(id);
		return custrepos.findById(id).orElseThrow(()-> new EntityNotFoundException("Customer Id Not Found: "+id) );
	}

	// save new Customer or update existing customer
	@Transactional
	@Override
	public Customer save(Customer webCust) {
		
		Customer newCust = new Customer();
		
		if (webCust.getCustcode() != 0) {
			custrepos.findById(webCust.getCustcode()).orElseThrow(()-> 
				new EntityNotFoundException("Customer data received from the web contained ID "
						+ webCust.getCustcode()+" that was not found in database."));
			
			newCust.setCustcode(webCust.getCustcode());
		}
		
		newCust.setCustname(webCust.getCustname());
		newCust.setCustcity(webCust.getCustcity());
		newCust.setWorkingarea(webCust.getWorkingarea());
		newCust.setCustcountry(webCust.getCustcountry());
		newCust.setGrade(webCust.getGrade());
		newCust.setOpeningamt(webCust.getOpeningamt());
		newCust.setReceiveamt(webCust.getReceiveamt());
		newCust.setPaymentamt(webCust.getPaymentamt());
		newCust.setOutstandingamt(webCust.getOutstandingamt());
		newCust.setPhone(webCust.getPhone());
		
		newCust.setCustomersagent(webCust.getCustomersagent()); // one of many customers to this one agent
		
		for (Order o: webCust.getCustordersList()) {
			Order newOrder = new Order(o.getOrdamount(),
									o.getAdvanceamount(),
									o.getOrderdescription(),
									o.getCustorders());
			
			newCust.getCustordersList().add(newOrder);
		}
		
		return custrepos.save(newCust);
	}

	@Override
	public Customer update(Customer customerInput, long id) {
		
		Customer customerDB = custrepos.findById(id).orElseThrow(()-> 
				new EntityNotFoundException("Can not update because Customer "+id+ "not found."));
		
		if (customerInput.getCustname() != null) { customerDB.setCustname(customerInput.getCustname()); }
		if (customerInput.getCustcity() != null) { customerDB.setCustcity(customerInput.getCustcity()); }
		if (customerInput.getWorkingarea() != null) { customerDB.setWorkingarea(customerInput.getWorkingarea()); }
		if (customerInput.getCustcountry() != null) { customerDB.setCustcountry(customerInput.getCustcountry()); }
		if (customerInput.getGrade() != null) { customerDB.setGrade(customerInput.getGrade()); }
		if (customerInput.getOpeningamt() != 0) { customerDB.setOpeningamt(customerInput.getOpeningamt()); }
		if (customerInput.getReceiveamt() != 0) { customerDB.setReceiveamt(customerInput.getReceiveamt()); }
		if (customerInput.getPaymentamt() != 0) { customerDB.setPaymentamt(customerInput.getPaymentamt()); }
		if (customerInput.getOutstandingamt() != 0) { customerDB.setOutstandingamt(customerInput.getOutstandingamt()); }
		if (customerInput.getPhone() != null) { customerDB.setPhone(customerInput.getPhone()); }
		if (customerInput.getCustomersagent() != null) { customerDB.setCustomersagent(customerInput.getCustomersagent()); }
		
		if (customerInput.getCustordersList().size() > 0) {
			for (Order o: customerInput.getCustordersList()) {
				Order newOrder = orderService.findById(o.getOrdnum());
				
				customerDB.getCustordersList().add(newOrder);
			}
		}
		return custrepos.save(customerDB);
	}

	@Transactional
	@Override
	public void delete(long id) {
		if (custrepos.findById(id).isPresent()) {
			custrepos.deleteById(id);
		} else {
			throw new EntityNotFoundException("Can not delete. Customer Id "+id+" not found.");
		}
		
	}

	
	@Override
	public List<Customer> findByNameLike(String nameLike) {
		
		ArrayList<Customer> rtn = custrepos.findByCustnameContainingIgnoringCase(nameLike);
		return rtn;
		//return custrepos.findBycustnameContaining(nameLike);
		
	}
	
	@Override
	public List<String> findQueryNameLike(String nameLike){
		return custrepos.findcustnameByQuery(nameLike);
	}
}
