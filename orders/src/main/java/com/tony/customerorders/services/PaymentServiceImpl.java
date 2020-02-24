package com.tony.customerorders.services;

import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tony.customerorders.models.Payment;
import com.tony.customerorders.repositories.PaymentRepository;

@Transactional
@Service(value="paymentService")
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepository payrepos;
	
	@Override
	public Payment findPaymentById(long id) {
		
		return payrepos.findById(id).orElseThrow(()-> new EntityNotFoundException("Payment "+ id +" Not Found."));
	}

	// save new Payment
	@Override
	public Payment save(Payment payment) {
		
		if (payment.getPaymentsorders().size() > 0) {
			long orderId = payment.getPaymentsorders().get(0).getOrdnum();
			throw new EntityExistsException("This is Payment has one or more existing Orders."+ orderId);
		}
		
		Payment newPayment = new Payment();
		newPayment.setPaymentsorders(new ArrayList<>());
		newPayment.setType(payment.getType());
		return null;
	}

	
}
