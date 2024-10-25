package com.example.petshop.entity.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GETProductDTO {
    private Integer id;

    private String productName;

    private Integer price;

    private String photo;

    private Boolean available = false;

    private Integer quantity;

    private String productDescription;
}
