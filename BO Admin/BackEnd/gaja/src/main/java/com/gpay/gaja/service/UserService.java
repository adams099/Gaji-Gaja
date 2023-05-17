package com.gpay.gaja.service;

import java.util.List;

import com.gpay.gaja.common.dto.UserDTO;

public interface UserService {

    UserDTO findById(Long id);

    UserDTO findByEmail(String id);

    UserDTO save(UserDTO userDTO);

    List<UserDTO> getAll();

    String login(UserDTO userDTO);

}
