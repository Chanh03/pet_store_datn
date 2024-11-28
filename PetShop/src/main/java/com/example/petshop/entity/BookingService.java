package com.example.petshop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class BookingService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookServiceID", nullable = false)
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Nationalized
    @Column(name = "NumberPhone", nullable = false, length = 20)
    private String numberPhone;

    @NotNull
    @Column(name = "BookDate", nullable = false)
    private LocalDate bookDate;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "BookStatus", nullable = false, length = 50)
    private String bookStatus;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "Note", nullable = false)
    private String note;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UserName", nullable = false)
    private User userName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ServiceID", nullable = false)
    private Service serviceID;

}