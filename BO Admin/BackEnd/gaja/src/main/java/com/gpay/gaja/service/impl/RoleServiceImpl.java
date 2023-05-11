package com.gpay.gaja.service.impl;

import com.gpay.gaja.common.dto.RoleDTO;
import com.gpay.gaja.common.mapper.RoleMapper;
import com.gpay.gaja.model.domain.Role;
import com.gpay.gaja.model.repository.RoleRepository;
import com.gpay.gaja.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;
    @Autowired
    private RoleMapper mapper;

    @Override
    public RoleDTO findById(Long id) {
        Optional<Role> roleOptional = repository.findById(id);
        return mapper.convertToDto(roleOptional.get());
    }

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        return mapper.convertToDto(repository.save(mapper.convertToEntity(roleDTO)));
    }

    @Override
    public List<RoleDTO> getAll() {
        return mapper.convertToDto(repository.findAll());
    }

}
