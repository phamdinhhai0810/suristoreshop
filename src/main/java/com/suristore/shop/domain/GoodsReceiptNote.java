package com.suristore.shop.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table
@Entity(name = "goods_receipt_note")
public class GoodsReceiptNote extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	@JsonBackReference
	private Customer customer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	@JsonBackReference
	private Product product;

	@Column(name = "price")
	private int price; // giá nhập

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "note")
	private String note;

	@Override
	public String toString() {
		return "{\"GoodsReceiptNote\":{"
				+ "\"id\":\"" + id + "\""
				+ ", \"customer\":" + customer
				+ ", \"product\":" + product
				+ ", \"price\":\"" + price + "\""
				+ ", \"quantity\":\"" + quantity + "\""
				+ ", \"note\":\"" + note + "\""
				+ "}}";
	}
}
