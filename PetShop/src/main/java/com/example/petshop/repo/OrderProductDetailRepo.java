package com.example.petshop.repo;

import com.example.petshop.entity.Order;
import com.example.petshop.entity.OrderProductDetail;
import com.example.petshop.entity.Product;
import com.example.petshop.entity.User;
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

    @Query("SELECT o.productID.id,o.productID.productName, SUM(o.quantity) ,o.productID.price " +
            "FROM OrderProductDetail o " +
            "WHERE o.orderID IN :order AND o.orderID.orderStatusID.id = 4 " +
            "GROUP BY o.productID, o.productID.id")
    List<Object[]> findTop5Product(List<Order> order);
}
