package com.epam.order.management.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.order.management.model.OrderItem;

@FeignClient(name="orderItemseach" )//Service Id of EmployeeSerach service
public interface OrderItemServiceClient {

	 @RequestMapping("/orders/items/{orderId}")
	   public OrderItem findById(@PathVariable(value="orderId") Long id);
}
