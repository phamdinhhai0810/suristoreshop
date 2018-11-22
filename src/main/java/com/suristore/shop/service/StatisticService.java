package com.suristore.shop.service;

import java.util.List;

import com.suristore.shop.domain.StatisticProfit;
import com.suristore.shop.domain.StatisticTotalPrice;

public interface StatisticService {

	List<StatisticProfit> getStatisticMonthProfit();

	List<StatisticProfit> getStatisticDayProfit();

	List<StatisticTotalPrice> getStatisticMonthTotalPrice();

	List<StatisticTotalPrice> getStatisticDayTotalPrice();
}
