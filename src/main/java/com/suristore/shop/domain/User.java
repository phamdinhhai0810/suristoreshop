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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private int id;

	@NotEmpty
	@Length(max = 100)
	@Column(name = "name", nullable = false)
	private String name;

	@NotEmpty
	@Email
	@Column(name = "email", nullable = false)
	private String email;

	@NotEmpty
	@Length(min = 8)
	@Column(name = "password", nullable = false)
	private String password;

	@JoinTable(name = "user_role", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	@ManyToMany(cascade = CascadeType.MERGE)
	private Set<Role> roles = new HashSet<>();

	@Override
	public String toString() {
		return "{\"User\":{"
				+ "\"id\":\"" + id + "\""
				+ ", \"name\":\"" + name + "\""
				+ ", \"email\":\"" + email + "\""
				+ ", \"roles\":" + roles
				+ "}}";
	}
}