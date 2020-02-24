package com.tony.customerorders.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tony.customerorders.models.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long>{

}
