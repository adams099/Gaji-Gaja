package com.gpay.gaja.common.dto;

import lombok.Data;

@Data
public class SendEmail {

    private Long id;
    private String subject;
    private String email;
    private String body;
}
