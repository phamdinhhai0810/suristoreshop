package com.suristore.shop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.suristore.shop.domain.Order;
import com.suristore.shop.domain.requestvo.OrderRequestVO;
import com.suristore.shop.service.CustomerService;
import com.suristore.shop.service.OrderService;
import com.suristore.shop.service.ProductService;
import com.suristore.shop.utils.CustomerType;

@Controller
@RequestMapping(value = "/admin/order")

public class AdminOrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CustomerService customerService;

	@GetMapping("")
	public String index(Model model) {

		Iterable<Order> orders = orderService.findAll();
		model.addAttribute("orders", orders);
		return "admin/order_list";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("order", orderService.findOne(id));
		return "admin/order_detail";
	}

	@GetMapping("/{id}/delete")
	public String delete(@PathVariable("id") int id, RedirectAttributes redirect) {
		orderService.delete(id);

		redirect.addFlashAttribute("success",
				new StringBuilder().append("Xóa đơn hàng ").append(id).append(" thành công").toString());
		return "redirect:/admin/order";
	}

	@GetMapping("/add")
	public String add(Model model) {

		List<CustomerType> listCustomerType = new ArrayList<>();
		listCustomerType.add(CustomerType.KHACH_MUA);
		listCustomerType.add(CustomerType.KHACH_BAN_VA_MUA);

		model.addAttribute("order", new OrderRequestVO());
		model.addAttribute("products", productService.findAll());
		model.addAttribute("customers", customerService.findByCustomerType(listCustomerType));

		return "admin/order_form";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @RequestBody OrderRequestVO orderRequestVO, BindingResult result,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "admin/order_form";
		}
		orderService.save(orderRequestVO);

		redirect.addFlashAttribute("success", "Đặt hàng thành công!");
		return "redirect:/admin/order";
	}

}
