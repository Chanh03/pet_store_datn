package com.example.petshop.repo;

import com.example.petshop.entity.Order;
import com.example.petshop.entity.OrderProductDetail;
import com.example.petshop.entity.Product;
import com.example.petshop.entity.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductDetailRepo extends JpaRepository<OrderProductDetail, Integer> {
    List<OrderProductDetail> findByOrderID(Order order);

    @Query("SELECT o FROM OrderProductDetail o WHERE o.orderID = :order and o.orderID.userName = :user")
    List<OrderProductDetail> findByOrderIDAndUser(Order order, User user);


    @Query("SELECT o FROM OrderProductDetail o WHERE o.orderID = :orderID and o.productID = :productID")
    OrderProductDetail findByOrderIDAndProductID(Order orderID, Product productID);
}
