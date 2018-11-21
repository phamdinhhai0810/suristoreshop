package com.suristore.shop.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suristore.shop.domain.Customer;
import com.suristore.shop.utils.CustomerType;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	// @Query(nativeQuery = true, value = "SELECT * FROM customer WHERE
	// customer_type IN customerType")
	List<Customer> findByCustomerTypeIn(List<CustomerType> customerType);

}
