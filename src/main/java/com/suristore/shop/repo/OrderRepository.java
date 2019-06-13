package com.suristore.shop.repo;

import com.suristore.shop.domain.Order;
import com.suristore.shop.domain.custom.StatisticProfitInterface;
import com.suristore.shop.domain.custom.StatisticTotalPriceInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Iterable<Order> findByCustomerId(int idCustomer);

    @Query(nativeQuery = true, value = " SELECT DATE_FORMAT(created_at ,'%Y-%m') as datadate , SUM(total_price) as totalPrice "
            + " FROM orders "
            + " WHERE YEAR(created_at) = YEAR(current_date()) "
            + " GROUP BY YEAR(created_at), MONTH(created_at) ")
    List<StatisticTotalPriceInterface> statisticMonthTotalPrice();

    @Query(nativeQuery = true, value = " SELECT DATE_FORMAT(created_at ,'%Y-%m-%d') as datadate, SUM(total_price) as totalPrice "
            + " FROM orders "
            + " WHERE MONTH(created_at) = MONTH(CURRENT_DATE()) "
            + " AND YEAR(created_at) = YEAR(CURRENT_DATE()) "
            + " GROUP BY  DAY(created_at) ")
    List<StatisticTotalPriceInterface> statisticDayTotalPrice();

    @Query(value = "SELECT sum(c.lai) as lai, c.monthYear " +
            "FROM ( " +
            " SELECT DATE_FORMAT(a.created_at ,'%Y-%m-%d') as monthYear, (a.quantity * a.sold_price - a.quantity * b.price) as lai " +
            " FROM item_order a, goods_receipt_note b " +
            " WHERE a.product_id = b.product_id " +
            "  AND MONTH(a.created_at) = MONTH(current_date()) " +
            "  AND YEAR(a.created_at) = YEAR(current_date())" +
            " GROUP BY  DAY(a.created_at), a.product_id " +
            ") c " +
            "GROUP BY c.monthYear " +
            "ORDER BY  c.monthYear DESC ", nativeQuery = true)
    List<StatisticProfitInterface> getStatisticDayProfit();

    @Query(value = "SELECT sum(c.lai) as lai, c.monthYear " +
            "FROM ( " +
            " SELECT DATE_FORMAT(a.created_at ,'%Y-%m') as monthYear, (a.quantity * a.sold_price - a.quantity * b.price) as lai " +
            " FROM item_order a, goods_receipt_note b " +
            " WHERE a.product_id = b.product_id " +
            "  AND YEAR(a.created_at) = YEAR(current_date())" +
            " GROUP BY  DAY(a.created_at), a.product_id " +
            ") c " +
            "GROUP BY c.monthYear " +
            "ORDER BY  c.monthYear DESC "
            , nativeQuery = true)
    List<StatisticProfitInterface> getStatisticMonthProfit();

}
