package com.suristore.shop.service;

import java.util.List;

import com.suristore.shop.domain.Customer;
import com.suristore.shop.utils.CustomerType;

public interface CustomerService {
	Iterable<Customer> findAll();

	Iterable<Customer> findByCustomerType(List<CustomerType> listCustomerType);

	Customer findOne(int id);

	long countAll();

	void save(Customer customer);

	void delete(int id);
}
