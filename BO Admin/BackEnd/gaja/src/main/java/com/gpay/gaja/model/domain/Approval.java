package com.gpay.gaja.model.domain;

import lombok.Data;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "approval")
@Data
public class Approval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private int status;
    @Column(name = "company_name")
    private String comName;
    @Column(name = "request_type")
    private String reqType;
    @Column(name = "requested_by")
    private String reqBy;
    @Column(name = "created_time")
    private LocalDateTime createdTime;
    @Column(name = "approved_by")
    private String apprBy;
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "comapny_id", referencedColumnName = "id", nullable = false)
    private Company companyId;
}
