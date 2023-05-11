package com.gpay.gaja.common.mapper;

import com.gpay.gaja.common.dto.StatusDTO;
import com.gpay.gaja.model.domain.Status;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StatusMapper implements EntityToDTOMapper<Status, StatusDTO> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public Status createEntity(StatusDTO statusDTO) {
        return mapper.map(statusDTO, Status.class);
    }

    @Override
    public Status updateEntity(StatusDTO statusDTO) {
        return null;
    }

    @Override
    public StatusDTO convertToDto(Status status) {
        return mapper.map(status, StatusDTO.class);
    }

    @Override
    public List<StatusDTO> convertToDto(Iterable<Status> statuss) {
        List<StatusDTO> dtos = new ArrayList<StatusDTO>();
        for (Status entity : statuss) {
            dtos.add(convertToDto(entity));
        }
        return dtos;
    }

    @Override
    public Status convertToEntity(StatusDTO statusDTO) {
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return mapper.map(statusDTO, Status.class);
    }

    @Override
    public List<Status> convertToEntity(Iterable<StatusDTO> statusDTOS) {
        return null;
    }
}
