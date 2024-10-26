package com.example.petshop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Nationalized;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User implements UserDetails {
    @Id
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
    @Column(name = "ActiveToken", nullable = false, length = 200)
    private String activeToken;

    @NotNull
    @Column(name = "DateCreated", nullable = false)
    private Instant dateCreated;

    @OneToMany(mappedBy = "userName")
    private Set<BookingService> bookingServices = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userName")
    private Set<Order> orders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userName")
    private Set<Review> reviews = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userName")
    private Set<Voucher> vouchers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userName", fetch = FetchType.EAGER)
    private Set<Authority> authorities = new LinkedHashSet<>();

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }
}