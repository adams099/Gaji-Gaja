package com.gpay.gaja.service;

import java.util.List;

import com.gpay.gaja.common.dto.StatusDTO;

public interface StatusService {

    StatusDTO findById(Long id);

    StatusDTO save(StatusDTO statusDTO);

    List<StatusDTO> getAll();
}
