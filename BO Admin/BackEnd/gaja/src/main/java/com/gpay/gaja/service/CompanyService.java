package com.gpay.gaja.service;

import java.util.List;

import com.gpay.gaja.common.dto.CompanyDTO;

public interface CompanyService {

    CompanyDTO findById(Long id);
    
    CompanyDTO save(CompanyDTO companyDTO);
    
    List<CompanyDTO> getAll();
    
    Long getStatusCount(int status);
}
