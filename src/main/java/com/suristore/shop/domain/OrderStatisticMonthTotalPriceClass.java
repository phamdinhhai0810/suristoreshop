package com.suristore.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatisticMonthTotalPriceClass {
	private String year;
	private String month;
	private String totalPrice;

	@Override
	public String toString() {
		return "{\"OrderStatisticMonthTotalPriceClass\":{" + "\"year\":\"" + year + "\"" + ", \"month\":\"" + month + "\""
				+ ", \"totalPrice\":\"" + totalPrice + "\"" + "}}";
	}
}
