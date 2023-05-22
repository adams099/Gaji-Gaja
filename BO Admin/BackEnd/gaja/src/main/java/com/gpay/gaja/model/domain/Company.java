package com.gpay.gaja.model.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "company")
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String comName;
    @Column(name = "siup")
    private String siup;
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
    @JsonIgnore
    @Column(name = "status_id")
    private Integer status;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_time")
    private LocalDateTime createdTime;
    @Column(name = "approved_by")
    private String apprBy;
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @JsonIgnore
    @OneToMany(mappedBy = "companyId", fetch = FetchType.LAZY)
    private List<Approval> approvals;
}
