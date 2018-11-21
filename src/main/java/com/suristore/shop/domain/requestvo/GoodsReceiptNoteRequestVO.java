package com.suristore.shop.domain.requestvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsReceiptNoteRequestVO {
	private int id_customer;
	private String note;
	private int id_product;
	private int quantity;
	private int price; // giá nhập
}
