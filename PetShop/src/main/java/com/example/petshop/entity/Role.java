package com.example.petshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Roles")
public class Role {
    @Id
    @Column(name = "Id", length = 50, nullable = false)
    private String id;

    @Size(max = 50)
    @Column(name = "Name", length = 50)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private Set<Authority> authorities = new LinkedHashSet<>();

}