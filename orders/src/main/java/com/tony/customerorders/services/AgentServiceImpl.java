package com.tony.customerorders.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tony.customerorders.models.Agent;
import com.tony.customerorders.models.Customer;
import com.tony.customerorders.repositories.AgentRepository;

@Transactional
@Service(value="agentService")	// to be defined in Agent controller
public class AgentServiceImpl implements AgentService {

	@Autowired
	private AgentRepository agentrepos;
	
	@Override
	public List<Agent> findAll() {
		List<Agent> rtnList = new ArrayList<>();
		agentrepos.findAll()
				.iterator()
				.forEachRemaining(rtnList::add);
		return rtnList;
	}

	@Override
	public Agent findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public Agent save(Agent agent) {
		Agent newAgent = new Agent();
		newAgent.setAgentname(agent.getAgentname());
		newAgent.setPhone(agent.getPhone());
		
		for (Customer a: agent.getCustomers()) {
			newAgent.getCustomers().add(new Customer(a.getCustname(),
													a.getCustcity(),
													a.getWorkingarea(),
													a.getCustcountry(),
													a.getGrade(),
													a.getOpeningamt(),
													a.getReceiveamt(),
													a.getPaymentamt(),
													a.getOutstandingamt(),
													a.getPhone(),
													newAgent));
		}
		return agentrepos.save(newAgent);
	}

	@Override
	public Agent update(Agent agent, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}
	
	

}
