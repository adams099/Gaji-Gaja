package com.gpay.gaja.common.mapper;

import com.gpay.gaja.common.dto.ResetPassDTO;
import com.gpay.gaja.model.domain.ResetPass;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResetPassMapper implements EntityToDTOMapper<ResetPass, ResetPassDTO> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public ResetPass createEntity(ResetPassDTO resetPassDTO) {
        return mapper.map(resetPassDTO, ResetPass.class);
    }

    @Override
    public ResetPass updateEntity(ResetPassDTO resetPassDTO) {
        return null;
    }

    @Override
    public ResetPassDTO convertToDto(ResetPass resetPass) {
        return mapper.map(resetPass, ResetPassDTO.class);
    }

    @Override
    public List<ResetPassDTO> convertToDto(Iterable<ResetPass> resetPasss) {
        List<ResetPassDTO> dtos = new ArrayList<ResetPassDTO>();
        for (ResetPass entity : resetPasss) {
            dtos.add(convertToDto(entity));
        }
        return dtos;
    }

    @Override
    public ResetPass convertToEntity(ResetPassDTO resetPassDTO) {
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return mapper.map(resetPassDTO, ResetPass.class);
    }

    @Override
    public List<ResetPass> convertToEntity(Iterable<ResetPassDTO> resetPassDTOS) {
        return null;
    }
}
