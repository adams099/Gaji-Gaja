package com.gpay.gaja.service.impl;

import com.gpay.gaja.common.dto.CompanyDTO;
import com.gpay.gaja.common.mapper.CompanyMapper;
import com.gpay.gaja.model.domain.Company;
import com.gpay.gaja.model.repository.CompanyRepository;
import com.gpay.gaja.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository repository;
    @Autowired
    private CompanyMapper mapper;

    @Override
    public CompanyDTO findById(Long id) {
        Optional<Company> companyOptional = repository.findById(id);
        return mapper.convertToDto(companyOptional.get());
    }

    @Override
    public CompanyDTO save(CompanyDTO companyDTO) {
        return mapper.convertToDto(repository.save(mapper.convertToEntity(companyDTO)));
    }

    @Override
    public List<CompanyDTO> getAll() {
        return mapper.convertToDto(repository.findAll());
    }

    @Override
    public Long getStatusCount(int status) {
        return repository.countByStatus(status);
    }

}
