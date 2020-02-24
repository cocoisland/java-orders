package com.tony.customerorders.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="payments")
public class Payment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long paymentid;
	
	@Column(nullable=false)
	private String type;
	
	// orderspayments from models.Order ManyToMany List<Payment> orderspayments
	@ManyToMany(mappedBy="orderspayments")
	@JsonIgnoreProperties("orderspayments")
	private List<Order> paymentsorders = new ArrayList<>();

	
	public Payment() {
		super();
	}


	public Payment(long paymentid, String type, List<Order> paymentsorders) {
		super();
		this.paymentid = paymentid;
		this.type = type;
		this.paymentsorders = paymentsorders;
	}


	public long getPaymentid() {
		return paymentid;
	}


	public void setPaymentid(long paymentid) {
		this.paymentid = paymentid;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public List<Order> getPaymentsorders() {
		return paymentsorders;
	}


	public void setPaymentsorders(List<Order> paymentsorders) {
		this.paymentsorders = paymentsorders;
	}

	
	
}
