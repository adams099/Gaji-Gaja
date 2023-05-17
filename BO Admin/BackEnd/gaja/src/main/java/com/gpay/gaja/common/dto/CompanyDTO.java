package com.gpay.gaja.common.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CompanyDTO {

    private Long id;
    private String comName;
    private String comTaxNum;
    private String siup;
    private String address;
    private String mailAddress;
    private String postal;
    private String adminName;
    private String adminEmail;
    private int status;
    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;
    private String apprBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
