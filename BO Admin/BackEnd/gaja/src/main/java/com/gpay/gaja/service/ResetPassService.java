package com.gpay.gaja.service;

import java.util.List;

import com.gpay.gaja.common.dto.ResetPassDTO;

public interface ResetPassService {

    ResetPassDTO save(ResetPassDTO resetPassDTO);

    List<ResetPassDTO> findByEmail(ResetPassDTO email);

}
