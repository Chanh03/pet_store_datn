package com.example.petshop.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSaleDTO {
    private Integer productID;
    private String productName;
    private Integer quantity;
}
