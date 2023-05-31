package com.gpay.gaja.common.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EmployeeDTO {

    private Long id;
    private Integer leaveDays;

    private String nik;
    private String nip;
    private String npwp;
    private String mobilePhone;
    private String division;
    private String directSupervisor;
    private String status;
    private String createBy;
    private String approveBy;
    private String companyEmail;

    private Float salary;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dateBirth;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime joinDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;

    private String document;

}
