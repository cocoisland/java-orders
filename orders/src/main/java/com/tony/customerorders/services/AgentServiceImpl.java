package com.tony.customerorders.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

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
		
		return agentrepos.findById(id).orElseThrow(()-> new EntityNotFoundException("Agent Id"+id+" not found."));
	}

	@Transactional
	@Override
	public Agent save(Agent changeAgent) {
		Agent newAgent = new Agent();
		newAgent.setAgentname(changeAgent.getAgentname());
		newAgent.setPhone(changeAgent.getPhone());
		
		for (Customer a: changeAgent.getAgentcustomers()) {
			newAgent.getAgentcustomers().add(new Customer(a.getCustname(),
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

	@Transactional
	@Override
	public Agent update(Agent changeAgent, long id) {
		Agent updateAgent = agentrepos.findById(id).orElseThrow(()-> 
			new EntityNotFoundException("Can not update because Agent Id "+id+" not found."));
		
		if (changeAgent.getAgentname()!=null) {updateAgent.setAgentname(changeAgent.getAgentname());}
		if (changeAgent.getPhone()!=null) {updateAgent.setPhone(changeAgent.getPhone());}
		
		if (changeAgent.getAgentcustomers().size()>0) {
			for (Customer c: changeAgent.getAgentcustomers()) {
				updateAgent.getAgentcustomers().add(c);
			}
		}
		return agentrepos.save(updateAgent);
	}

	@Transactional
	@Override
	public void delete(long id) {
		if (agentrepos.findById(id).isPresent()) {
			// clean agentcustomers List OneToMany relationship 
			Agent tmpAgent = agentrepos.findById(id).orElseThrow();
			for (Customer c: tmpAgent.getAgentcustomers()) {
				tmpAgent.removeAgentcustomers(c);
			}
			
			agentrepos.deleteById(id);
		} else {
			throw new EntityNotFoundException("Can not delete becuase Agent Id "+id+ " is not found.");
		}
		
	}
	
	

}
