package com.example.petshop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ServiceID", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "ServiceName", nullable = false)
    private String serviceName;

    @NotNull
    @Column(name = "Price", nullable = false)
    private Integer price;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "Description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "serviceID")
    private Set<BookingService> bookingServices = new LinkedHashSet<>();

}