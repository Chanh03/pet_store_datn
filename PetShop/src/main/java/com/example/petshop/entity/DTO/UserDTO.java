package com.example.petshop.entity.DTO;

import java.time.Instant;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import com.example.petshop.entity.Authority;
import com.example.petshop.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String userName;
    private boolean enable;
    private String activeToken;
    private Instant dateCreated;
    private String AuthID; // Trường này cần thiết

    public UserDTO(User user) {
        this.userName = user.getUsername();
        this.enable = user.getEnable();
        this.activeToken = user.getActiveToken();
        this.dateCreated = user.getDateCreated();
        if (!user.getAuthorities().isEmpty()) {
            // Lấy ID của Authority (nếu có)
            this.AuthID = String.valueOf(user.getAuthorities().iterator().next().getId());
        }
    }


}