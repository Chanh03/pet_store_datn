package com.example.petshop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SliderBar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SliderId", nullable = false)
    private Integer id;

    @Size(max = 30)
    @NotNull
    @Column(name = "SliderImage", nullable = false, length = 30)
    private String sliderImage;

}