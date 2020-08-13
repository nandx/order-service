package com.nanda.moon.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nanda.moon.common.CommonUtils;
import com.nanda.moon.dto.OrderParamDTO;
import com.nanda.moon.dto.OrderParamDTO.ItemDTO;
import com.nanda.moon.dto.ResponseDTO;
import com.nanda.moon.entity.OrderItem;
import com.nanda.moon.entity.OrderTransaction;
import com.nanda.moon.repository.OrderItemRepository;
import com.nanda.moon.repository.OrderTransactionRepository;

@Service
public class OrderTransactionService {

	@Autowired
	private OrderTransactionRepository orderTransactionRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;

	public ResponseEntity<String> create(OrderParamDTO orderParamDTO) {
		List<ItemDTO> listItem = orderParamDTO.getItems();
		List<OrderItem> listOrderItem = new ArrayList<OrderItem>();

		BigDecimal totalamount = BigDecimal.ZERO;
		OrderTransaction transaction = new OrderTransaction();
		for (ItemDTO itemDTO : listItem) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderTransaction(transaction);
			BeanUtils.copyProperties(itemDTO, orderItem);
			totalamount = itemDTO.getPrice().multiply(BigDecimal.valueOf(itemDTO.getTotalitem())).setScale(2,
					RoundingMode.HALF_UP);
			listOrderItem.add(orderItem);
		}
		transaction.setTotalamount(totalamount);

		orderTransactionRepository.save(transaction);
		orderItemRepository.saveAll(listOrderItem);

		return getOrder(orderParamDTO.getOrdernumber());
	}

	public ResponseEntity<String> getOrder(String ordernumber) {
		if (!CommonUtils.isNotEmpty(ordernumber))
			return new ResponseEntity<String>(
					new ResponseDTO<OrderParamDTO>("404", "Order number is empty.").toString(), HttpStatus.OK);

		OrderTransaction orderTansaction = orderTransactionRepository.findByOrdernumber(ordernumber);
		if (orderTansaction == null)
			return new ResponseEntity<String>(
					new ResponseDTO<OrderParamDTO>("404", "Order number is not found.").toString(), HttpStatus.OK);

		List<OrderItem> listItem = orderItemRepository.findByOrderTransaction(orderTansaction);
		OrderParamDTO orderParamDTO = new OrderParamDTO();
		orderParamDTO.setItems(new ArrayList<OrderParamDTO.ItemDTO>());
		BeanUtils.copyProperties(orderTansaction, orderParamDTO);
		for (OrderItem orderItem : listItem) {
			OrderParamDTO.ItemDTO itemDTO = new OrderParamDTO.ItemDTO();
			BeanUtils.copyProperties(orderItem, itemDTO);
			orderParamDTO.getItems().add(itemDTO);
		}

		ResponseDTO<OrderParamDTO> result = new ResponseDTO<OrderParamDTO>();
		result.addData(orderParamDTO);
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
	}

}
