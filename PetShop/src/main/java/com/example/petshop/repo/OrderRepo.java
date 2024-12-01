package com.example.petshop.repo;

import com.example.petshop.entity.Order;
import com.example.petshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.userName.userName = :username")
    List<Order> findByUserName(String username);

    @Query("SELECT o FROM Order o WHERE o.id = :id and o.userName = :user")
    Order findByIdAndUser(int id, User user);

    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :from AND :to")
    List<Order> findByOrderDate(Date from, Date to);

    @Query("SELECT o FROM Order o WHERE o.orderDate = :localDate")
    List<Order> findByOrderToday(Date localDate);

    @Query("SELECT o FROM Order o WHERE MONTH(o.orderDate) = :month1 AND YEAR(o.orderDate) = :year1")
    List<Order> findByOrderMonth(int month1, int year1);

    @Query("SELECT o FROM Order o WHERE YEAR(o.orderDate) = :year1")
    List<Order> findByOrderYear(int year1);

}
