package com.gpay.gaja.common.mapper;

import com.gpay.gaja.common.dto.ApprovalDTO;
import com.gpay.gaja.model.domain.Approval;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApprovalMapper implements EntityToDTOMapper<Approval, ApprovalDTO> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public Approval createEntity(ApprovalDTO approvalDTO) {
        return mapper.map(approvalDTO, Approval.class);
    }

    @Override
    public Approval updateEntity(ApprovalDTO approvalDTO) {
        return null;
    }

    @Override
    public ApprovalDTO convertToDto(Approval approval) {
        return mapper.map(approval, ApprovalDTO.class);
    }

    @Override
    public List<ApprovalDTO> convertToDto(Iterable<Approval> approvals) {
        List<ApprovalDTO> dtos = new ArrayList<ApprovalDTO>();
        for (Approval entity : approvals) {
            dtos.add(convertToDto(entity));
        }
        return dtos;
    }

    @Override
    public Approval convertToEntity(ApprovalDTO approvalDTO) {
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return mapper.map(approvalDTO, Approval.class);
    }

    @Override
    public List<Approval> convertToEntity(Iterable<ApprovalDTO> approvalDTOS) {
        return null;
    }
}
