package com.suristore.shop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.suristore.shop.domain.Customer;
import com.suristore.shop.service.CustomerService;
import com.suristore.shop.service.OrderService;

@Controller
@RequestMapping(value = "/admin/customer")
public class AdminCustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private OrderService orderService;

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("customers", customerService.findAll());
		return "admin/customer_list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("customer", new Customer());
		return "admin/customer_form";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		model.addAttribute("customer", customerService.findOne(id));
		return "admin/customer_form";
	}

	@PostMapping("/save")
	public String save(@Valid Customer customer, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "admin/customer_form";
		}

		customerService.save(customer);

		redirect.addFlashAttribute("success", "Lưu khách hàng thành công!");
		return "redirect:/admin/customer";
	}

	@GetMapping("/{id}/orders")
	public String showOrders(@PathVariable("id") int id, Model model) {

		model.addAttribute("customer", customerService.findOne(id));
		model.addAttribute("orders", orderService.findByCustomerId(id));
		return "admin/customer_orders";
	}

	@GetMapping("/{id}/delete")
	public String delete(@PathVariable("id") int id, RedirectAttributes redirect) {
		customerService.delete(id);

		redirect.addFlashAttribute("success", "Xóa khách hàng thành công!");
		return "redirect:/admin/customer";
	}

}
