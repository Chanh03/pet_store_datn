package com.example.petshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Nationalized;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User implements UserDetails {
    @Id
    @JsonProperty("userName")
    @Size(max = 50)
    @Column(name = "UserName", nullable = false, length = 50)
    private String userName;

    @Size(max = 100)
    @NotNull
    @Nationalized
    @Column(name = "UserPassword", nullable = false, length = 100)
    private String userPassword;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "FullName", nullable = false, length = 50)
    private String fullName;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @Size(max = 20)
    @NotNull
    @Nationalized
    @Column(name = "PhoneNumber", nullable = false, length = 20)
    private String phoneNumber;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "UserAddress", nullable = false)
    private String userAddress;

    @NotNull
    @Column(name = "Enable", nullable = false)
    private Boolean enable = false;

    @Size(max = 200)
    @NotNull
    @JsonIgnore
    @Column(name = "ActiveToken", nullable = false, length = 200)
    private String activeToken;

    @NotNull
    @Column(name = "IsDelete", nullable = false)
    private Boolean isDelete = false;
    @NotNull
    @Column(name = "DateCreated", nullable = false)
    private LocalDateTime dateCreated;

    @JsonIgnore
    @OneToMany(mappedBy = "userName")
    private Set<Order> orders = new LinkedHashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "userName")
    private Set<Review> reviews = new LinkedHashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "userName", fetch = FetchType.EAGER)
    private Set<Authority> authorities = new LinkedHashSet<>();

    @Size(max = 225)
    @Column(name = "TemporaryGUID", length = 225)
    private String temporaryGUID;

    @Column(name = "TempGuidExpir")
    private LocalDateTime tempGuidExpir;

    @JsonIgnore
    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

}