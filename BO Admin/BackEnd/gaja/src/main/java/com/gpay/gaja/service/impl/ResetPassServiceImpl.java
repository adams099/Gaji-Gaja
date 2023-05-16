package com.gpay.gaja.service.impl;

import com.gpay.gaja.common.dto.ResetPassDTO;
import com.gpay.gaja.common.mapper.ResetPassMapper;
import com.gpay.gaja.model.domain.ResetPass;
import com.gpay.gaja.model.repository.ResetPassRepository;
import com.gpay.gaja.service.ResetPassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResetPassServiceImpl implements ResetPassService {

    @Autowired
    private ResetPassRepository repository;
    @Autowired
    private ResetPassMapper mapper;

    @Override
    public ResetPassDTO save(ResetPassDTO ResetPassDTO) {
        return mapper.convertToDto(repository.save(mapper.convertToEntity(ResetPassDTO)));
    }

    @Override
    public List<ResetPassDTO> findByEmail(ResetPassDTO email) {
        List<ResetPass> emailOptional = repository.findByEmail(email.getEmail());
        return mapper.convertToDto(emailOptional);
    }

}
