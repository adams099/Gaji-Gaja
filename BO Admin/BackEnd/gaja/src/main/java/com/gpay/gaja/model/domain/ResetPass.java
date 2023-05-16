package com.gpay.gaja.model.domain;

import lombok.Data;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "otp")
@Data
public class ResetPass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "otp")
    private String otp;
    @Column(name = "email")
    private String email;
    @Column(name = "date")
    private LocalDateTime date;
}
