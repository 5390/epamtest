package com.epam.order.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.epam.order.management.model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long>{

}
