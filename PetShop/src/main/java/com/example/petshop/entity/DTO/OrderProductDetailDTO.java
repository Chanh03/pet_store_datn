package com.example.petshop.entity.DTO;

import com.example.petshop.entity.Order;
import com.example.petshop.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductDetailDTO {
	private Integer orderID;
	private String productID;
	private Integer quantity;
	private Integer price;
}
