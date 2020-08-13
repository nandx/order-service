package com.nanda.moon.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "order_transaction")
public class OrderTransaction {

	public OrderTransaction() {
		Double x = (Math.random() * ((1000 - 1) + 1)) + 1;
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd-HHmmss");
		this.ordernumber = "TRX-" + sdf.format(new Date()) + "-" + x.intValue();
		this.orderstatus = StatusOrder.PENDING.name();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 20, unique = true, nullable = false)
	private Long id;

	@Column(name = "ordernumber", length = 50, unique = true, nullable = false)
	private String ordernumber;

	@Column(name = "orderstatus", length = 50, nullable = false)
	private String orderstatus;

	@Column(name = "totalamount", precision = 15, scale = 4)
	private BigDecimal totalamount;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createddate")
	private Date createddate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateddate")
	private Date updateddate;

	public static enum StatusOrder {
		PENDING;
	}

}
