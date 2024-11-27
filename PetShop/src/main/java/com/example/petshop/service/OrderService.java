package com.example.petshop.service;

import com.example.petshop.entity.Order;
import com.example.petshop.entity.User;
import org.springframework.stereotype.Service;

import java.awt.print.PrinterJob;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
}
