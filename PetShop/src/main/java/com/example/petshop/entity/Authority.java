package com.example.petshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@Entity
@Table(name = "Authorities")
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UserName")
    private User userName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RoleId")
    private Role role;


    @Override
    public String getAuthority() {
        return role.getName();
    }
}