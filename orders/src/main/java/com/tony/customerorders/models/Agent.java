package com.tony.customerorders.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "agents")
public class Agent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long agentcode;
	
	@Column(unique = true, nullable = false)
	private String agentname;
	
	private String workingarea;
	private double commission;
	private String phone;
	private String country;
	
	// models.Customer ManyToOne private Agent customersagent
	@OneToMany(mappedBy = "customersagent", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("customersagent") // prevent infinite loop
	private List<Customer> agentcustomers = new ArrayList<>();
	
	public Agent() {
		super();
	}

	public Agent(String agentname, String workingarea, double commission, String phone, String country) {
		super();
		this.agentname = agentname;
		this.workingarea = workingarea;
		this.commission = commission;
		this.phone = phone;
		this.country = country;
	}

	public long getAgentcode() {
		return agentcode;
	}

	public void setAgentcode(long agentcode) {
		this.agentcode = agentcode;
	}

	public String getAgentname() {
		return agentname;
	}

	public void setAgentname(String agentname) {
		this.agentname = agentname;
	}

	public String getWorkingarea() {
		return workingarea;
	}

	public void setWorkingarea(String workingarea) {
		this.workingarea = workingarea;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public String getPhone() {
		return phone;
	}

	public List<Customer> getAgentcustomers() {
		return agentcustomers;
	}

	public void setAgentcustomers(List<Customer> agentcustomers) {
		this.agentcustomers = agentcustomers;
	}
	
	public void removeAgentcustomers(Customer customer) {
		this.agentcustomers.remove(customer);
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
