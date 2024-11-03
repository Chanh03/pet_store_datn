package com.example.petshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Voucher {
    @Id
    @Size(max = 50)
    @Column(name = "VoucherID", nullable = false, length = 50)
    private String voucherID;

    @NotNull
    @Column(name = "Discount", nullable = false)
    private Integer discount;

    @NotNull
    @Column(name = "ExpiryDate", nullable = false)
    private LocalDate expiryDate;

    @NotNull
    @Column(name = "Enable", nullable = false)
    private Boolean enable = false;

}