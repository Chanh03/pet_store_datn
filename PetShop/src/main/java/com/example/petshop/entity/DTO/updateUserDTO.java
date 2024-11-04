package com.example.petshop.entity.DTO;

import java.time.Instant;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class updateUserDTO {
    private String userName;
    private String activeToken;
    private String email;
    private String fullName;
    private String dateCreate;
    private String phoneNumber;
    private String userAddress;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    
    public String getFindUserOauth2(String username) {
    	if(username != null && username.matches("[^a-zA-Z]*")) {
    		return username;
    	}
		return null;
	}

	public String findUserOauth2;
}