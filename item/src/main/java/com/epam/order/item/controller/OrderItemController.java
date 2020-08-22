package com.epam.order.item.controller;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.epam.order.item.model.OrderItem;
import com.epam.order.item.repository.OrderItemRepository;

@RestController
public class OrderItemController {

	@Autowired
	private OrderItemRepository orderRepo;
	
	@Autowired
	   RestTemplate restTemplate;
	
	@RequestMapping(value = "/orders/items/{orderId}", method = RequestMethod.GET)
	public ResponseEntity<OrderItem> GetOrderDetails(@PathVariable("orderId") long id) {
		Optional<OrderItem> order =orderRepo.findById(id);
//		HttpHeaders headers = new HttpHeaders();
//	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//	      HttpEntity <String> entity = new HttpEntity<String>(headers);
//	      
//	       restTemplate.exchange("http://localhost:1990/orders/"+id, HttpMethod.GET, entity, String.class).getBody();
		if (order.isPresent()) {
			return new ResponseEntity<OrderItem>(order.get(),HttpStatus.OK);
		}
		return new ResponseEntity<OrderItem>(new OrderItem(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/orders/items", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderItem> saveOrder(@RequestBody OrderItem inputOrder) {

		OrderItem outputOrder = orderRepo.save(inputOrder);
		return new ResponseEntity<OrderItem>(outputOrder,HttpStatus.OK);
	}
}
