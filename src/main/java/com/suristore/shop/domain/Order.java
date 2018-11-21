package com.suristore.shop.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Table
@Entity(name = "orders")
@Getter
@Setter
public class Order extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	@JsonBackReference
	private Customer customer;

	@Column(name = "totalPrice")
	private int totalPrice;

	@Column(name = "note")
	private String note;

	@OneToMany(mappedBy = "pk.order", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ItemOrder> itemOrder = new HashSet<>();

	@Override
	public String toString() {
		return "{\"Order\":{"
				+ "\"id\":\"" + id + "\""
				+ ", \"customer\":" + customer
				+ ", \"totalPrice\":\"" + totalPrice + "\""
				+ ", \"note\":\"" + note + "\""
				+ ", \"itemOrder\":" + itemOrder
				+ "}}";
	}
}