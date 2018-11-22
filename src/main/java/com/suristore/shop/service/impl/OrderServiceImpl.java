package com.suristore.shop.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suristore.shop.domain.Customer;
import com.suristore.shop.domain.ItemOrder;
import com.suristore.shop.domain.Order;
import com.suristore.shop.domain.Product;
import com.suristore.shop.domain.requestvo.OrderRequestVO;
import com.suristore.shop.domain.requestvo.ProductOrderRequestVO;
import com.suristore.shop.repo.CustomerRepository;
import com.suristore.shop.repo.OrderRepository;
import com.suristore.shop.repo.ProductRepository;
import com.suristore.shop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	// @Autowired
	// private UserRepository userRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Order> findAll() {
		return orderRepository.findAll(new Sort(Sort.Direction.DESC, "createdAt"));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Order> findLatest(int page, int size) {
		// PageRequest pageRequest = new PageRequest(page, size,
		// Sort.Direction.DESC, "createdAt");
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Order findOne(int id) {
		return orderRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public long countAll() {
		return orderRepository.count();
	}

	@Override
	@Transactional
	public void save(OrderRequestVO orderRequestVO) {

		Customer customer = customerRepository.findOne(orderRequestVO.getIdCustomer());

		List<ProductOrderRequestVO> productOrderRequestVOList = orderRequestVO.getListProduct();

		// Set order
		Order order = new Order();
		order.setCustomer(customer);
		order.setNote(orderRequestVO.getNote());

		Set<ItemOrder> itemOrderList = new HashSet<>();

		int totalPriceOfOrder = 0;
		if (productOrderRequestVOList != null && productOrderRequestVOList.size() > 0) {
			for (ProductOrderRequestVO productOrderRequestVO : productOrderRequestVOList) {

				totalPriceOfOrder += productOrderRequestVO.getSoldPrice() * productOrderRequestVO.getQuantity();

				Product product = productRepository.findOne(productOrderRequestVO.getId_product());

				// tăng số lượng sản phẩm đã bán
				product.setTotalSold(product.getTotalSold() + productOrderRequestVO.getQuantity());
				productRepository.save(product);

				ItemOrder itemOrder = new ItemOrder();
				itemOrder.setOrder(order);
				itemOrder.setProduct(product);
				itemOrder.setQuantity(productOrderRequestVO.getQuantity());
				itemOrder.setSoldPrice(productOrderRequestVO.getSoldPrice());

				itemOrderList.add(itemOrder);
			}
		}

		order.setItemOrder(itemOrderList);
		order.setTotalPrice(totalPriceOfOrder);

		orderRepository.save(order);
	}

	@Override
	@Transactional
	public void delete(int orderId) {
		Order order = orderRepository.findOne(orderId);
		if (order != null) {
			// trả lại số lượng sản phẩm đã bán
			// hoàn trả số lượng về kho
			Set<ItemOrder> itemOrderList = order.getItemOrder();
			if (itemOrderList != null && itemOrderList.size() > 0) {
				for (ItemOrder itemOrder : itemOrderList) {
					Product product = itemOrder.getProduct();
					product.setTotalSold(product.getTotalSold() - itemOrder.getQuantity());
					productRepository.save(product);
				}
			}

		}
		orderRepository.delete(orderId);
	}

	@Override
	@Transactional
	public Iterable<Order> find5OrderLatest() {

		PageRequest pageRequest = new PageRequest(0, 5, Sort.Direction.DESC, "createdAt");
		return orderRepository.findAll(pageRequest);
	}

	@Override
	public Iterable<Order> findByCustomerId(int idCustomer) {

		return orderRepository.findByCustomerId(idCustomer);
	}

}
