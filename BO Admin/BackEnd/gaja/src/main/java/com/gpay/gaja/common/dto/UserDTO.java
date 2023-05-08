package com.gpay.gaja.common.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String username;
    private String name;
    private String email;
    private String pass;
    private String roleuser;
    private String role;
}
