package com.tony.customerorders.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tony.customerorders.models.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
