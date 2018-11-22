package com.suristore.shop.service;

import org.springframework.data.domain.Page;

import com.suristore.shop.domain.Order;
import com.suristore.shop.domain.requestvo.OrderRequestVO;

public interface OrderService {

	Iterable<Order> findAll();

	Page<Order> findLatest(int page, int size);

	Order findOne(int id);

	long countAll();

	void save(OrderRequestVO orderRequestVO);

	void delete(int orderId);

	Iterable<Order> find5OrderLatest();

	Iterable<Order> findByCustomerId(int idCustomer);

}
