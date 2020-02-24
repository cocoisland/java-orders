package com.tony.customerorders.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long custcode;
	
	@Column(nullable = false)
	private String custname;
	
	private String custcity;
	private String workingarea;
	private String custcountry;
	private String grade;
	private double openingamt;
	private double receiveamt;
	private double paymentamt;
	private double outstandingamt ;
	private String phone;
	
	@ManyToOne
	@JoinColumn(name = "agentcode", nullable = false)
	@JsonIgnoreProperties("agentcustomers")	// models.Agent OneToMany List<Customer> agentcustomers
											// Jackson prevents objects from infinite loop
	private Agent customersagent;		// join to models.Agent OneToMany mapped by customersagent
	
	// models.Order ManyToOne private Customer custorders
	@OneToMany(mappedBy = "custorders",cascade = CascadeType.ALL)
	@JsonIgnoreProperties("custorders")
	private List<Order> custordersList = new ArrayList<>(); 	// orders used by models.Order ManyToOne 
	

	// empty constructor required by Spring framework
	public Customer() {
		super();
	}

	public Customer(String custname, String custcity, String workingarea, String custcountry, String grade,
			double openingamt, double receiveamt, double paymentamt, double outstandingamt, String phone,
			Agent customersagent) {
		super();
		this.custname = custname;
		this.custcity = custcity;
		this.workingarea = workingarea;
		this.custcountry = custcountry;
		this.grade = grade;
		this.openingamt = openingamt;
		this.receiveamt = receiveamt;
		this.paymentamt = paymentamt;
		this.outstandingamt = outstandingamt;
		this.phone = phone;
		this.customersagent = customersagent; // one customeragent
	}

	public long getCustcode() {
		return custcode;
	}

	public void setCustcode(long custcode) {
		this.custcode = custcode;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getCustcity() {
		return custcity;
	}

	public void setCustcity(String custcity) {
		this.custcity = custcity;
	}

	public String getWorkingarea() {
		return workingarea;
	}

	public void setWorkingarea(String workingarea) {
		this.workingarea = workingarea;
	}

	public String getCustcountry() {
		return custcountry;
	}

	public void setCustcountry(String custcountry) {
		this.custcountry = custcountry;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public double getOpeningamt() {
		return openingamt;
	}

	public void setOpeningamt(double openingamt) {
		this.openingamt = openingamt;
	}

	public double getReceiveamt() {
		return receiveamt;
	}

	public void setReceiveamt(double receiveamt) {
		this.receiveamt = receiveamt;
	}

	public double getPaymentamt() {
		return paymentamt;
	}

	public void setPaymentamt(double paymentamt) {
		this.paymentamt = paymentamt;
	}

	public double getOutstandingamt() {
		return outstandingamt;
	}

	public void setOutstandingamt(double outstandingamt) {
		this.outstandingamt = outstandingamt;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Agent getCustomersagent() {
		return customersagent;
	}

	public void setCustomersagent(Agent customersagent) {
		this.customersagent = customersagent;
	}

	public List<Order> getCustordersList() {
		return custordersList;
	}

	public void setCustordersList(List<Order> custordersList) {
		this.custordersList = custordersList;
	}
	
	public String toString() {
		return("\n\tCustomer {"+ 
				" custname "+custname+" custcity "+custcity+" workingarea "+workingarea+
				" custcountry "+custcountry+" grade "+grade+
				" openingamt "+openingamt+" receiveamt "+receiveamt+" paymentamt "+paymentamt+
				" outstandingamt "+outstandingamt+" phone "+phone+
				" customersagent "+ customersagent +
				" custordersList "+custordersList+
				"}");
	}
	}
