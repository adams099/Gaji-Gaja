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

    @Column(name = "name")
    private String name;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password", length = 1000)
    private String pin;
    @Column(name = "pin")
    private String pass;
    @Column(name = "role_id")
    private int roleId;
    @Column(name = "status_id")
    private int statId;
    @Column(name = "company_id")
    private Long companyId;
    @Column(name = "created_time")
    private LocalDateTime created;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "phone_number", unique = true)
    private String phone;
    @Column(name = "approved_by")
    private String apprBy;
    @Column(name = "update_time")
    private LocalDateTime update;
    @Column(name = "last_login")
    private LocalDateTime last;

}
