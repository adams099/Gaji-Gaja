package com.gpay.gaja.model.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "leave_days")
    private Integer leaveDays;

    @Column(name = "nik")
    private String nik;
    @Column(name = "nip")
    private String nip;
    @Column(name = "npwp")
    private String npwp;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "mobile_phone")
    private String mobilePhone;
    @Column(name = "division")
    private String division;
    @Column(name = "direct_supervisor")
    private String directSupervisor;
    @Column(name = "status")
    private String status;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "approve_by")
    private String approveBy;
    @Column(name = "company_email")
    private String companyEmail;
    @Column(name = "document")
    private String document;

    @Column(name = "salary")
    private Float salary;

    @Column(name = "date_of_birth")
    private LocalDateTime dateBirth;
    @Column(name = "join_date")
    private LocalDateTime joinDate;
    @Column(name = "created_time")
    private LocalDateTime createdTime;
    @Column(name = "update_time")
    private LocalDateTime updatedTime;

    @JsonIgnore
    @OneToMany(mappedBy = "employeeId", fetch = FetchType.LAZY)
    private List<Document> documents;

}
