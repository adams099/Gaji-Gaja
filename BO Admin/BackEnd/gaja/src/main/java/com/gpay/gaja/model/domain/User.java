package com.gpay.gaja.model.domain;

import lombok.Data;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "name")
    private String name;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password", length = 1000)
    private String pass;
    @Column(name = "role_id")
    private int roleId;
    @Column(name = "status_id")
    private int statId;
    @Column(name = "created_time")
    private LocalDateTime created;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "approved_by")
    private String approved;
    @Column(name = "phone_number")
    private String phone;

}
