package com.gpay.gaja.service;

import java.util.List;

import com.gpay.gaja.common.dto.RoleDTO;

public interface RoleService {

    RoleDTO findById(Long id);

    RoleDTO save(RoleDTO roleDTO);

    List<RoleDTO> getAll();
}
