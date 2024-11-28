package com.example.petshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "OrderDate", nullable = false)
    private Date orderDate;

    @Size(max = 255)
    @Nationalized
    @Column(name = "ShippingAddress")
    private String shippingAddress;

    @NotNull
    @Column(name = "TotalAmount", nullable = false)
    private Long totalAmount;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "UserName", nullable = false)
    private User userName;

    @NotNull
    @Column(name = "Enable", nullable = false)
    private Boolean enable = false;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "OrderStatusID", nullable = false)
    private OrderStatus orderStatusID;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "PaymentStatusID", nullable = false)
    private PaymentStatus paymentStatusID;

    @JsonIgnore
    @OneToMany(mappedBy = "orderID")
    private Set<OrderProductDetail> orderProductDetails = new LinkedHashSet<>();

}