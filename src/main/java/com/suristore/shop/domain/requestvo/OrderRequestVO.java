package com.suristore.shop.domain.requestvo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestVO {
	private int idCustomer;
	private String note;
	private List<ProductOrderRequestVO> listProduct;
}
