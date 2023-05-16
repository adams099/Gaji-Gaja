package com.gpay.gaja.common.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String username;
    private String name;
    private String email;
    private String pass;
    private int roleId;
    private int statId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    private String createdBy;
    private String approved;
    private String phone;
    private LocalDateTime update;

}
