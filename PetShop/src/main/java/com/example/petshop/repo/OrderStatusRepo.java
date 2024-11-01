package com.example.petshop.repo;

import com.example.petshop.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepo extends JpaRepository<OrderStatus, Integer> {
    OrderStatus findByStatusName(String statusName);

    @Query("SELECT os FROM OrderStatus os WHERE os.id = ?1")
    OrderStatus getByOrderStatusId(Integer orderStatusID);
}
