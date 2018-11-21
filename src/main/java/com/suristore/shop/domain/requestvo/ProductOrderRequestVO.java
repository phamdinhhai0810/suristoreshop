package com.suristore.shop.domain.requestvo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderRequestVO {

	private int id_product;
	private int quantity;
	private int soldPrice; // gía đã bán

}
