package com.gpay.gaja.model.domain;

import lombok.Data;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "company")
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String comName;
    @Column(name = "company_tax_number")
    private String comTaxNum;
    @Column(name = "address")
    private String address;
    @Column(name = "mailing_address", unique = true)
    private String mailAddress;
    @Column(name = "postal_code")
    private String postal;
    @Column(name = "admin_name")
    private String adminName;
    @Column(name = "admin_email")
    private String adminEmail;
    @Column(name = "status_id")
    private int status;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_time")
    private LocalDateTime createdTime;
    @Column(name = "approved_by")
    private String apprBy;
    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
