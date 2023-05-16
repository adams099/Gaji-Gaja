package com.gpay.gaja.common.dto;

import lombok.Data;

@Data
public class ApprovalDTO {

    private Long id;
    private String comName;
    private String adminName;
    private String adminEmail;
    private String mailAddress;
    private String address;
    private String postal;
    private String comTaxNum;
    private String lastUpdate;
    private String createdBy;
    private String updateBy;
    private int status;

}
