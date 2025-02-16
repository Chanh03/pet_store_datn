package com.example.petshop.service;

import com.example.petshop.entity.Order;
import com.example.petshop.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface OrderService {
    List<Order> getAll();

    Order save(Order order);

    void deleteById(Integer id);

    List<Order> getHistory(String username);

    Order getByOrderId(Integer id);

    Order getByOrderIdAndUser(int id, User user);

    Order getById(Integer id);

    List<Order> findOrdersByDate(Date from, Date to);

    List<Order> findOrdersToday(Date localDate);

    List<Order> findOrdersByYear(int date);

    List<Order> findOrdersByMonth(int month1, int year1);

    List<Object[]> findOrdersChartByDate(Date from, Date to);

    List<Object[]> getAllChart();
}
