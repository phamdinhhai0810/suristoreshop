package com.suristore.shop.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.suristore.shop.domain.Order;
import com.suristore.shop.domain.custom.OrderStatisticMonthTotalPrice;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	Iterable<Order> findByCustomerId(int idCustomer);

	@Query(nativeQuery = true, value = " SELECT YEAR(created_at) as year , MONTH(created_at) as month , SUM(total_price) as totalPrice "
										 + " FROM orders "
										 + " GROUP BY YEAR(created_at), MONTH(created_at) "
										 + " ORDER BY year DESC, year DESC "
										 + " LIMIT 12 ")
	List<OrderStatisticMonthTotalPrice> OrderStatisticMonthTotalPrice();
}
