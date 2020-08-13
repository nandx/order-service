package com.nanda.moon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nanda.moon.dto.OrderParamDTO;
import com.nanda.moon.service.OrderTransactionService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderTransactionService orderTransactionService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createOrder(@RequestBody OrderParamDTO orderParamDTO) {
		return orderTransactionService.create(orderParamDTO);
	}

	@RequestMapping(value = "/get/{ordernumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getOrder(@PathVariable("ordernumber") String ordernumber) {
		return orderTransactionService.getOrder(ordernumber);
	}

}
