package com.example.petshop.entity.DTO;

import java.util.Date;

import com.example.petshop.entity.Authority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthorityDTO {
    private int id;
    private String roleName;

    public AuthorityDTO(Authority authority) {
        this.id = authority.getId();
        this.roleName = authority.getRole().getName(); // Sử dụng Role từ Authority
    }

}