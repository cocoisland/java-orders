package com.tony.customerorders.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tony.customerorders.models.Agent;

public interface AgentRepository extends CrudRepository<Agent, Long>
{

}
