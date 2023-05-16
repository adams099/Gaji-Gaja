package com.gpay.gaja.common.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResetPassDTO {

    private Long id;
    private String otp;
    private String email;
    private LocalDateTime date;
}
