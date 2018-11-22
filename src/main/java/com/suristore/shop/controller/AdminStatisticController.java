package com.suristore.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suristore.shop.service.StatisticService;

@Controller
@RequestMapping(value = "/admin/statistic")
public class AdminStatisticController {

	@Autowired
	private StatisticService statisticService;

	@GetMapping(value = { "", "/day" })
	public String index(Model model) {

		model.addAttribute("totalprice_day", statisticService.getStatisticDayTotalPrice());
		model.addAttribute("profit_day", statisticService.getStatisticDayProfit());
		
		return "admin/statistic_day";
	}

	@GetMapping(value = { "", "/month" })
	public String month(Model model) {

		model.addAttribute("totalprice_month", statisticService.getStatisticMonthTotalPrice());
		model.addAttribute("profit_month", statisticService.getStatisticMonthProfit());
		
		return "admin/statistic_month";
	}

}
