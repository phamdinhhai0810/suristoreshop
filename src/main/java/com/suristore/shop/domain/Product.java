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

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "image", nullable = false)
	private String image;

	@Column(name = "price", nullable = false)
	private int price; // giá tham khảo

	@Column(name = "total", nullable = false)
	private int total;

	@Column(name = "totalSold", nullable = false)
	private int totalSold;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	@JsonBackReference
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id", referencedColumnName = "id")
	@JsonBackReference
	private Type type;

	@OneToMany(mappedBy = "pk.product", cascade = CascadeType.ALL)
	@JsonBackReference
	private Set<ItemOrder> items = new HashSet<>();


	@Override
	public String toString() {
		return "{" + "\"id\":\"" + id + "\"" + ", \"name\":\"" + name + "\"" + ", \"description\":\"" + description
				+ "\"" + ", \"image\":\"" + image + "\"" + ", \"price\":\"" + price + "\"" + ", \"category\":"
				+ category + ", \"type\":" + type + "}";
	}
}