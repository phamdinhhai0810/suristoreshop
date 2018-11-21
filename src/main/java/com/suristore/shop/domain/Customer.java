package com.suristore.shop.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.suristore.shop.utils.CustomerType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "customer_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private CustomerType customerType;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	@JsonBackReference
	private Set<Order> orders = new HashSet<>();

	@Override
	public String toString() {
		return "{\"Customer\":{"
				+ "\"id\":\"" + id + "\""
				+ ", \"name\":\"" + name + "\""
				+ ", \"phone\":\"" + phone + "\""
				+ ", \"address\":\"" + address + "\""
				+ ", \"customerType\":\"" + customerType + "\""
				+ "}}";
	}
}
