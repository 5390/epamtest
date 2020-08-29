package com.epam.order.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.order.item.model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long>{
}
