package com.gpay.gaja.service.impl;

import com.gpay.gaja.common.dto.StatusDTO;
import com.gpay.gaja.common.mapper.StatusMapper;
import com.gpay.gaja.model.domain.Status;
import com.gpay.gaja.model.repository.StatusRepository;
import com.gpay.gaja.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository repository;
    @Autowired
    private StatusMapper mapper;

    @Override
    public StatusDTO findById(Long id) {
        Optional<Status> statusOptional = repository.findById(id);
        return mapper.convertToDto(statusOptional.get());
    }

    @Override
    public StatusDTO save(StatusDTO statusDTO) {
        return mapper.convertToDto(repository.save(mapper.convertToEntity(statusDTO)));
    }

    @Override
    public List<StatusDTO> getAll() {
        return mapper.convertToDto(repository.findAll());
    }

}
