package com.gpay.gaja.service;

import java.util.List;

import com.gpay.gaja.common.dto.ApprovalDTO;

public interface ApprovalService {

    ApprovalDTO save(ApprovalDTO approvalDTO);

    List<ApprovalDTO> getAll();

}
