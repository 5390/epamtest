package com.epam.order.management.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.epam.order.management.model.OrderItem;
import com.epam.order.management.model.Orders;
import com.epam.order.management.repository.OrderRepository;
import com.epam.order.management.restclient.OrderItemServiceClient;

@RestController
public class OrderController {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	OrderItemServiceClient orderItemClient;

	@RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
	public ResponseEntity<Orders> GetOrderDetails(@PathVariable("orderId") long id) {
		Optional<Orders> order =orderRepo.findById(id);
		OrderItem	orderItem = orderItemClient.findById(id);
		System.out.println(orderItem.toString());
		if (order.isPresent()) {
			return new ResponseEntity<Orders>(order.get(),HttpStatus.OK);
		}
		return new ResponseEntity<Orders>(new Orders(),HttpStatus.OK);
	}

	@RequestMapping(value = "/orders", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Orders> saveOrder(@RequestBody Orders inputOrder) {

		Orders outputOrder = orderRepo.save(inputOrder);
		return new ResponseEntity<Orders>(outputOrder,HttpStatus.OK);
	}
}
