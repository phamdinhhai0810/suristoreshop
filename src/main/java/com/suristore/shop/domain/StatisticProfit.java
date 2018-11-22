package com.suristore.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticProfit {
	private String monthYear;
	private int lai;

	@Override
	public String toString() {
		return "{"
				+ "\"monthYear\":\"" + monthYear + "\""
				+ ", \"lai\":" + lai + ""
				+ "}";
	}
}
