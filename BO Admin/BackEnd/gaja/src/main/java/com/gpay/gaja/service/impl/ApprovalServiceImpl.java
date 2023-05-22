package com.gpay.gaja.service.impl;

import com.gpay.gaja.common.dto.ApprovalDTO;
import com.gpay.gaja.common.mapper.ApprovalMapper;
import com.gpay.gaja.model.domain.Approval;
import com.gpay.gaja.model.domain.Company;
import com.gpay.gaja.model.repository.ApprovalRepository;
import com.gpay.gaja.model.repository.CompanyRepository;
import com.gpay.gaja.service.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApprovalServiceImpl implements ApprovalService {

    @Autowired
    private ApprovalRepository repository;

    @Autowired
    private ApprovalMapper mapper;

    @Override
    public ApprovalDTO save(ApprovalDTO approvalDTO) {
        return mapper.convertToDto(repository.save(mapper.convertToEntity(approvalDTO)));
    }

}
