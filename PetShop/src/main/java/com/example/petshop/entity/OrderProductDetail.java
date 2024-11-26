package com.example.petshop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "OrderProductDetails")
public class OrderProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderProductDetailID", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "OrderID", nullable = false)
    private Order orderID;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ProductID", nullable = false)
    private Product productID;

    @NotNull
    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "ReviewStatus", nullable = false)
    private Boolean reviewStatus;

    @NotNull
    @Column(name = "Price", nullable = false)
    private Integer price;
}