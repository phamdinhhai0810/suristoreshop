package com.suristore.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suristore.shop.domain.OrderStatisticMonthTotalPriceClass;
import com.suristore.shop.service.OrderService;

@Controller
@RequestMapping(value = "/admin/statistic")
public class AdminStatisticController {

	@Autowired
	private OrderService orderService;

	@GetMapping(value = "")
	public String index(Model model) {

		List<OrderStatisticMonthTotalPriceClass> listTotalPrice = orderService.getOrderStatisticMonthTotalPrice();
		model.addAttribute("totalprice", listTotalPrice);

		return "admin/statistic";
	}

}
