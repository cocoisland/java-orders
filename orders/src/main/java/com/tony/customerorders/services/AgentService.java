package com.tony.customerorders.services;

import java.util.List;

import com.tony.customerorders.models.Agent;

public interface AgentService {

	List<Agent> findAll();
	
	Agent findById(long id);
	
	Agent save(Agent agent);
	
	Agent update(Agent agent, long id);
	
	void delete(long id);

}
