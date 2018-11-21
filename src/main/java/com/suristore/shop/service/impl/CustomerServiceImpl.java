package com.suristore.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suristore.shop.domain.Customer;
import com.suristore.shop.repo.CustomerRepository;
import com.suristore.shop.service.CustomerService;
import com.suristore.shop.utils.CustomerType;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Iterable<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer findOne(int id) {
		return customerRepository.findOne(id);
	}

	@Override
	public long countAll() {
		return customerRepository.count();
	}

	@Override
	public void save(Customer customer) {
		customerRepository.save(customer);
	}

	@Override
	public void delete(int id) {
		customerRepository.delete(id);
	}

	@Override
	public Iterable<Customer> findByCustomerType(List<CustomerType> listCustomerType) {

		return customerRepository.findByCustomerTypeIn(listCustomerType);
	}
}
