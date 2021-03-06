package com.suristore.shop.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.suristore.shop.domain.GoodsReceiptNote;
import com.suristore.shop.domain.custom.StatisticTotalPriceInterface;

public interface GoodsReceiptNoteRepository extends JpaRepository<GoodsReceiptNote, Integer> {

    @Query(nativeQuery = true, value = " SELECT DATE_FORMAT(created_at ,'%Y-%m') as datadate, SUM(price) as totalPrice "
            + " FROM goods_receipt_note "
            + " WHERE YEAR(created_at) = YEAR(current_date()) "
            + " GROUP BY YEAR(created_at), MONTH(created_at) ")
    List<StatisticTotalPriceInterface> statisticMonthTotalPrice();

    @Query(nativeQuery = true, value = " SELECT DATE_FORMAT(created_at ,'%Y-%m-%d') as datadate, SUM(price) as totalPrice "
            + " FROM goods_receipt_note "
            + " WHERE MONTH(created_at) = MONTH(CURRENT_DATE()) "
            + " AND YEAR(created_at) = YEAR(CURRENT_DATE()) "
            + " GROUP BY  DAY(created_at) ")
    List<StatisticTotalPriceInterface> statisticDayTotalPrice();

}
