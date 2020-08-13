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
import com.nanda.moon.dto.ResponseDTO;
import com.nanda.moon.service.OrderTransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderTransactionService orderTransactionService;

	@Operation(summary = "Create Order")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Create Order", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }) })
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createOrder(@RequestBody OrderParamDTO orderParamDTO) {
		return orderTransactionService.create(orderParamDTO);
	}

	@Operation(summary = "Get Order")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Get Order", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }) })
	@RequestMapping(value = "/get/{ordernumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getOrder(@PathVariable("ordernumber") String ordernumber) {
		return orderTransactionService.getOrder(ordernumber);
	}

}
