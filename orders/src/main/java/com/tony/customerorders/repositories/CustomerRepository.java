package com.tony.customerorders.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tony.customerorders.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
