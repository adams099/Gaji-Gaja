package com.gpay.gaja.common.dto;

import lombok.Data;

@Data
public class CompanyDTO {

    private Long id;
    private String comName;
    private String comTaxNum;
    private String address;
    private String mailAddress;
    private String postal;
    private String adminName;
    private String adminEmail;
    private int status;
    private String sender;

}
