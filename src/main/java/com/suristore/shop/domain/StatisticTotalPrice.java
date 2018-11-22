package com.suristore.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticTotalPrice {
	private String monthYear;
	private int tongThu;
	private int tongChi;

	@Override
	public String toString() {
		return "{"
				+ "\"monthYear\":\"" + monthYear + "\""
				+ ", \"tongThu\":" + tongThu + ""
				+ ", \"tongChi\":" + tongChi + ""
				+ "}";
	}
}
