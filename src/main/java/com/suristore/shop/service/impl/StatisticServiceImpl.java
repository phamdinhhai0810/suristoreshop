package com.suristore.shop.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.suristore.shop.domain.custom.StatisticProfitInterface;
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

        Map<String, StatisticTotalPrice> mapResult = getList12MonthOfThisYear();

        List<StatisticTotalPriceInterface> listOrder = orderRepository.statisticMonthTotalPrice();

        List<StatisticTotalPriceInterface> listGoodsReceipt = goodsReceiptNoteRepository.statisticMonthTotalPrice();

        if (listOrder != null && listOrder.size() > 0) {
            for (StatisticTotalPriceInterface order : listOrder) {
                String key = order.getDataDate();
                if (mapResult.containsKey(key)) {
                    StatisticTotalPrice value = mapResult.get(key);
                    value.setTongThu(order.getTotalPrice());
                }
            }
        }

        if (listGoodsReceipt != null && listGoodsReceipt.size() > 0) {
            for (StatisticTotalPriceInterface goodsReceipt : listGoodsReceipt) {
                String key = goodsReceipt.getDataDate();
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

        Map<String, StatisticTotalPrice> mapResult = getList30DayOfThisMonth();

        List<StatisticTotalPriceInterface> listOrder = orderRepository.statisticDayTotalPrice();

        List<StatisticTotalPriceInterface> listGoodsReceipt = goodsReceiptNoteRepository.statisticDayTotalPrice();

        if (listOrder != null && listOrder.size() > 0) {
            for (StatisticTotalPriceInterface order : listOrder) {
                String key = order.getDataDate();
                if (mapResult.containsKey(key)) {
                    StatisticTotalPrice value = mapResult.get(key);
                    value.setTongThu(order.getTotalPrice());
                }
            }
        }

        if (listGoodsReceipt != null && listGoodsReceipt.size() > 0) {
            for (StatisticTotalPriceInterface goodsReceipt : listGoodsReceipt) {
                String key = goodsReceipt.getDataDate();
                if (mapResult.containsKey(key)) {
                    StatisticTotalPrice value = mapResult.get(key);
                    value.setTongChi(goodsReceipt.getTotalPrice());
                }
            }
        }

        return new ArrayList<>(mapResult.values());
    }

    private Map<String, StatisticTotalPrice> getList30DayOfThisMonth() {

        Map<String, StatisticTotalPrice> list = new TreeMap<>();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int myMonth = cal.get(Calendar.MONTH);

        while (myMonth == cal.get(Calendar.MONTH)) {
            System.out.print(cal.getTime());
            String day_name = dayDate.format(cal.getTime());
            list.put(day_name, new StatisticTotalPrice(day_name, 0, 0));

            cal.add(Calendar.DAY_OF_MONTH, 1);
        }


        return list;
    }

    private Map<String, StatisticTotalPrice> getList12MonthOfThisYear() {

        Map<String, StatisticTotalPrice> list = new TreeMap<>();

        Calendar calendar = Calendar.getInstance();
        int myYear = calendar.get(Calendar.YEAR);

        for (int i = 1; i <= 12; i++) {
            String yearmonth;
            if (i < 10)
                yearmonth = String.format("%d-0%d",myYear, i);
            else
                yearmonth = String.format("%d-%d",myYear, i);

            list.put(yearmonth, new StatisticTotalPrice(yearmonth, 0, 0));
        }

        return list;
    }

    @Override
    public List<StatisticProfit> getStatisticMonthProfit() {
        Map<String, StatisticProfit> result = getListProfitMonthOfThisYear();

        List<StatisticProfitInterface> data = orderRepository.getStatisticMonthProfit();
        data.stream().forEach(d -> {

            if (result.containsKey(d.getMonthYear())) {
                StatisticProfit sp = result.get(d.getMonthYear());
                sp.setLai(d.getLai());
            }
        });

        return new ArrayList<>(result.values());
    }

    @Override
    public List<StatisticProfit> getStatisticDayProfit() {

        Map<String, StatisticProfit> result = getListProfitDayOfThisMonth();

        List<StatisticProfitInterface> data = orderRepository.getStatisticDayProfit();
        data.stream().forEach(d -> {

            if (result.containsKey(d.getMonthYear())) {
                StatisticProfit sp = result.get(d.getMonthYear());
                sp.setLai(d.getLai());
            }
        });

        return new ArrayList<>(result.values());
    }

    private Map<String, StatisticProfit> getListProfitDayOfThisMonth() {
        // lay cac ngay trong thang nay

        Map<String, StatisticProfit> list = new TreeMap<>();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int myMonth = cal.get(Calendar.MONTH);

        while (myMonth == cal.get(Calendar.MONTH)) {
            System.out.print(cal.getTime());
            String day_name = dayDate.format(cal.getTime());
            list.put(day_name, new StatisticProfit(day_name, 0));

            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        return list;
    }

    private Map<String, StatisticProfit> getListProfitMonthOfThisYear() {
        // lay cac thang trong nam nay

        Map<String, StatisticProfit> list = new TreeMap<>();

        Calendar calendar = Calendar.getInstance();
        int myYear = calendar.get(Calendar.YEAR);

        for (int i = 1; i <= 12; i++) {
            String yearmonth;
            if (i < 10)
                yearmonth = String.format("%d-0%d",myYear, i);
            else
                yearmonth = String.format("%d-%d",myYear, i);

            list.put(yearmonth, new StatisticProfit(yearmonth, 0));
        }

        return list;
    }
}
