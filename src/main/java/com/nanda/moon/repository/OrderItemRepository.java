package com.nanda.moon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nanda.moon.entity.OrderItem;
import com.nanda.moon.entity.OrderTransaction;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	public List<OrderItem> findByOrderTransaction(OrderTransaction orderTransaction);

}
