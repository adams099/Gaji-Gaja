package com.gpay.gaja.common.mapper;

import com.gpay.gaja.common.dto.RoleDTO;
import com.gpay.gaja.model.domain.Role;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleMapper implements EntityToDTOMapper<Role, RoleDTO> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public Role createEntity(RoleDTO roleDTO) {
        return mapper.map(roleDTO, Role.class);
    }

    @Override
    public Role updateEntity(RoleDTO roleDTO) {
        return null;
    }

    @Override
    public RoleDTO convertToDto(Role role) {
        return mapper.map(role, RoleDTO.class);
    }

    @Override
    public List<RoleDTO> convertToDto(Iterable<Role> roles) {
        List<RoleDTO> dtos = new ArrayList<RoleDTO>();
        for (Role entity : roles) {
            dtos.add(convertToDto(entity));
        }
        return dtos;
    }

    @Override
    public Role convertToEntity(RoleDTO roleDTO) {
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return mapper.map(roleDTO, Role.class);
    }

    @Override
    public List<Role> convertToEntity(Iterable<RoleDTO> roleDTOS) {
        return null;
    }
}
