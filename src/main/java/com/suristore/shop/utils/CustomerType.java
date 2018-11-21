package com.suristore.shop.utils;

public enum CustomerType {
	KHACH_MUA("Khách Mua"), KHACH_BAN("Khách Bán"), KHACH_BAN_VA_MUA("Khách Mua - Bán");

	private String value;

	CustomerType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
