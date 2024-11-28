package com.example.petshop.entity.DTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import com.example.petshop.entity.OrderProductDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private String userName;
    private String shippingAddress;
    private String code;
    private String message;
    private String amount;
    private String maDonHang;
    private String paymentUrl;
    private Date orderDate;
    private List<OrderProductDetailDTO> productDetails;

    public Date getOrderDate() {
        if (orderDate == null) {
            return null;
        }
        return orderDate;
    }

    public long getAmount() {
        if (amount == null) {
            return 0;
        }
        long amountInt = Long.parseLong(amount);
        return amountInt;
    }
}
