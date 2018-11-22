package com.suristore.shop.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suristore.shop.domain.StatisticProfit;
import com.suristore.shop.domain.StatisticTotalPrice;
import com.suristore.shop.domain.custom.StatisticTotalPriceInterface;
import com.suristore.shop.repo.GoodsReceiptNoteRepository;
import com.suristore.shop.repo.OrderRepository;
import com.suristore.shop.service.StatisticService;

@Service
public class StatisticServiceImpl implements StatisticService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private GoodsReceiptNoteRepository goodsReceiptNoteRepository;

	private SimpleDateFormat dayDate = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat monthDate = new SimpleDateFormat("yyyy-MM");

	@Override
	public List<StatisticTotalPrice> getStatisticMonthTotalPrice() {

		Map<String, StatisticTotalPrice> mapResult = getList12LatestMonth();

		List<StatisticTotalPriceInterface> listOrder = orderRepository.statisticMonthTotalPrice();

		List<StatisticTotalPriceInterface> listGoodsReceipt = goodsReceiptNoteRepository.statisticMonthTotalPrice();

		if (listOrder != null && listOrder.size() > 0) {
			for (StatisticTotalPriceInterface order : listOrder) {
				String key = monthDate.format(order.getDataDate());
				if (mapResult.containsKey(key)) {
					StatisticTotalPrice value = mapResult.get(key);
					value.setTongThu(order.getTotalPrice());
				}
			}
		}

		if (listGoodsReceipt != null && listGoodsReceipt.size() > 0) {
			for (StatisticTotalPriceInterface goodsReceipt : listGoodsReceipt) {
				String key = monthDate.format(goodsReceipt.getDataDate());
				if (mapResult.containsKey(key)) {
					StatisticTotalPrice value = mapResult.get(key);
					value.setTongChi(goodsReceipt.getTotalPrice());
				}
			}
		}

		return new ArrayList<>(mapResult.values());
	}

	@Override
	public List<StatisticTotalPrice> getStatisticDayTotalPrice() {

		Map<String, StatisticTotalPrice> mapResult = getList30LatestDay();

		List<StatisticTotalPriceInterface> listOrder = orderRepository.statisticDayTotalPrice();

		List<StatisticTotalPriceInterface> listGoodsReceipt = goodsReceiptNoteRepository.statisticDayTotalPrice();

		if (listOrder != null && listOrder.size() > 0) {
			for (StatisticTotalPriceInterface order : listOrder) {
				String key = dayDate.format(order.getDataDate());
				if (mapResult.containsKey(key)) {
					StatisticTotalPrice value = mapResult.get(key);
					value.setTongThu(order.getTotalPrice());
				}
			}
		}

		if (listGoodsReceipt != null && listGoodsReceipt.size() > 0) {
			for (StatisticTotalPriceInterface goodsReceipt : listGoodsReceipt) {
				String key = dayDate.format(goodsReceipt.getDataDate());
				if (mapResult.containsKey(key)) {
					StatisticTotalPrice value = mapResult.get(key);
					value.setTongChi(goodsReceipt.getTotalPrice());
				}
			}
		}

		return new ArrayList<>(mapResult.values());
	}

	private Map<String, StatisticTotalPrice> getList30LatestDay() {

		Map<String, StatisticTotalPrice> list = new TreeMap<>();

		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		for (int i = 0; i < 30; i++) {
			String day_name = localDate.minusDays(i).format(formatter);
			list.put(day_name, new StatisticTotalPrice(day_name, 0, 0));
		}

		return list;
	}

	private Map<String, StatisticTotalPrice> getList12LatestMonth() {

		Map<String, StatisticTotalPrice> list = new TreeMap<>();

		Calendar cal = Calendar.getInstance();

		for (int i = 1; i <= 12; i++) {
			String month_name = monthDate.format(cal.getTime());
			list.put(month_name, new StatisticTotalPrice(month_name, 0, 0));
			cal.add(Calendar.MONTH, -1);
		}

		return list;
	}

	@Override
	public List<StatisticProfit> getStatisticMonthProfit() {
		List<StatisticProfit> result = new ArrayList<>();
		List<StatisticTotalPrice> totalPrices = getStatisticMonthTotalPrice();
		if (totalPrices != null && totalPrices.size() > 0) {
			for (StatisticTotalPrice price : totalPrices) {
				result.add(new StatisticProfit(price.getMonthYear(), price.getTongThu() - price.getTongChi()));
			}
		}

		return result;
	}

	@Override
	public List<StatisticProfit> getStatisticDayProfit() {
		List<StatisticProfit> result = new ArrayList<>();
		List<StatisticTotalPrice> totalPrices = getStatisticDayTotalPrice();
		if (totalPrices != null && totalPrices.size() > 0) {
			for (StatisticTotalPrice price : totalPrices) {
				result.add(new StatisticProfit(price.getMonthYear(), price.getTongThu() - price.getTongChi()));
			}
		}

		return result;
	}
}
