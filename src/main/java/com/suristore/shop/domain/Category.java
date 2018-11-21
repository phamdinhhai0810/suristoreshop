package com.suristore.shop.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private int id;

	@Length(max = 255)
	@NotEmpty
	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JsonBackReference // prevent load products when load category (REST)
	private Set<Product> products = new HashSet<Product>();

	@Override
	public String toString() {
		return "{" + "\"id\":\"" + id + "\"" + ", \"name\":\"" + name + "\"" + "}";
	}

}