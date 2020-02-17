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

}
