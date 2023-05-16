package com.gpay.gaja.model.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "approval")
@Data
public class Approval {
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
    @Column(name = "last_update")
    private String lastUpdate;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "update_by")
    private String updateBy;
}
