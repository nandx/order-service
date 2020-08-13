package com.nanda.moon.repository;

import com.nanda.moon.entity.OrderTransaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTransactionRepository extends JpaRepository<OrderTransaction, Long> {

	public OrderTransaction findByOrdernumber(String ordernumber);

}
