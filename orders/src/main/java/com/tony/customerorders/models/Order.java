package com.tony.customerorders.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long ordnum;
	
	private double ordamount;
	private double advanceamount;
	private String orderdescription;
	
	@ManyToOne
	@JoinColumn(name = "custcode", nullable = false)
	@JsonIgnoreProperties("orders") 		// models.Customer OneToMany List<Order> orders
	private Customer custorders;	// used by models.Customer OneToMany mapped by custorders
	
	@ManyToMany
	@JoinTable(name = "orders_payments", 
			joinColumns = {@JoinColumn(name="ordnum")},
			inverseJoinColumns = {@JoinColumn(name="paymentid")}
			)
	@JsonIgnoreProperties("paymentsorders")		// paymentsorders from model.Payment ManyToMany List<Order> paymentsorders
	private List<Payment> orderspayments = new ArrayList<>();

	public Order() {
		super();
	}

	public Order(double ordamount, double advanceamount, String orderdescription, Customer custorders) {
		super();
		this.ordamount = ordamount;
		this.advanceamount = advanceamount;
		this.orderdescription = orderdescription;
		this.custorders = custorders;
	}

	public long getOrdnum() {
		return ordnum;
	}

	public void setOrdnum(long ordnum) {
		this.ordnum = ordnum;
	}

	public double getOrdamount() {
		return ordamount;
	}

	public void setOrdamount(double ordamount) {
		this.ordamount = ordamount;
	}

	public double getAdvanceamount() {
		return advanceamount;
	}

	public void setAdvanceamount(double advanceamount) {
		this.advanceamount = advanceamount;
	}

	public String getOrderdescription() {
		return orderdescription;
	}

	public void setOrderdescription(String orderdescription) {
		this.orderdescription = orderdescription;
	}
	
	public void addPayment(Payment payment) {
		orderspayments.add(payment);		// payment added to ManyToMany orderspayments list in Order
		payment.getPaymentsorders().add(this);	// order added to ManyToMany paymentsorders list in Payment
	}
	
	public void removePayment(Payment payment) {
		orderspayments.remove(payment);
		payment.getPaymentsorders().remove(this);
		
	}
	
	@Override
	public String toString() {
		return("\n\tOrder{"+" ordamount= "+ordamount+" advanceamount= "+advanceamount+
				" orderdescription= "+orderdescription+
				" custorders= "+custorders+
				" orderspayments= "+orderspayments +"}");
	}

	public Customer getCustorders() {
		return custorders;
	}

	public void setCustorders(Customer custorders) {
		this.custorders = custorders;
	}

	public List<Payment> getOrderspayments() {
		return orderspayments;
	}

	public void setOrderspayments(List<Payment> orderspayments) {
		this.orderspayments = orderspayments;
	}

}
