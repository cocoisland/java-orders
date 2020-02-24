package com.tony.customerorders.services;

import com.tony.customerorders.models.Payment;

public interface PaymentService {

	Payment findPaymentById(long id);
	
	Payment save(Payment payment);
}
