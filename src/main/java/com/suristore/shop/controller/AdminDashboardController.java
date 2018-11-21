package com.suristore.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.suristore.shop.domain.Order;
import com.suristore.shop.service.CategoryService;
import com.suristore.shop.service.CustomerService;
import com.suristore.shop.service.OrderService;
import com.suristore.shop.service.ProductService;
import com.suristore.shop.service.TypeService;

@Controller
public class AdminDashboardController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private TypeService typeService;

	@GetMapping({ "/admin", "/admin/dashboard" })
	public String index(Model model) {
		// Stats
		long totalUsers = customerService.countAll();
		long totalCategories = categoryService.countAll();
		long totalProducts = productService.countAll();
		long totalOrders = orderService.countAll();
		long totalTypes = typeService.countAll();

		// Fetch top 5 newest orders;
		Iterable<Order> newestOrders = orderService.find5OrderLatest();

		model.addAttribute("totalUsers", totalUsers);
		model.addAttribute("totalCategories", totalCategories);
		model.addAttribute("totalProducts", totalProducts);
		model.addAttribute("totalOrders", totalOrders);
		model.addAttribute("totalTypes", totalTypes);
		model.addAttribute("newestOrders", newestOrders);

		return "admin/dashboard";
	}
	
	@GetMapping("/admin/dashboard/order/{id}/delete")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirect) {
		orderService.delete(id);

		redirect.addFlashAttribute("success",
				new StringBuilder().append("Xóa đơn hàng ").append(id).append(" thành công").toString());
		return "redirect:/admin/dashboard";
	}

}
