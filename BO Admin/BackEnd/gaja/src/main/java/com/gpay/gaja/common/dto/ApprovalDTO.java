package com.gpay.gaja.common.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gpay.gaja.model.domain.Company;

import lombok.Data;

@Data
public class ApprovalDTO {

    private Long id;
    private int status;
    private String comName;
    private String reqType;
    private String reqBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;
    private String apprBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private Long companyId;
    @JsonIgnore
    private Company companyz;
}
