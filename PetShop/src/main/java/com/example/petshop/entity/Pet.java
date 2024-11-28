package com.example.petshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Pets")
public class Pet {
    @Id
    @Size(max = 50)
    @Column(name = "PetID", nullable = false, length = 50)
    private String petID;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "Breed", nullable = false)
    private String breed;

    @NotNull
    @Column(name = "Age", nullable = false)
    private Integer age;

    @Column(name = "CreateDate", nullable = false)
    private LocalDateTime createDate;

    @NotNull
    @Column(name = "Gender", nullable = false)
    private Boolean gender = false;

    @NotNull
    @Column(name = "Price", nullable = false)
    private Integer price;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "PetDescription", nullable = false)
    private String petDescription;

    @Size(max = 255)
    @NotNull
    @Column(name = "Photo", nullable = false)
    private String photo;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "Famous", nullable = false)
    private String famous;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "Hair", nullable = false)
    private String hair;

    @NotNull
    @Column(name = "Enable", nullable = false)
    private Boolean enable = false;

    @NotNull
    @Column(name = "Available", nullable = false)
    private Boolean available = false;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "PetCategoryID", nullable = false)
    private PetCategory petCategoryID;
}