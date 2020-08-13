package com.nanda.moon.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class OrderParamDTO {

	private String ordernumber;
	private String orderstatus;
	private BigDecimal totalamount;
	private List<ItemDTO> items;

	@Data
	public static class ItemDTO {

		private String productcode;
		private String productname;
		private BigDecimal price;
		private Integer totalitem;

	}

}
