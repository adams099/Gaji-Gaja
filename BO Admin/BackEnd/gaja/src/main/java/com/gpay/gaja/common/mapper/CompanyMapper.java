package com.gpay.gaja.common.mapper;

import com.gpay.gaja.common.dto.CompanyDTO;
import com.gpay.gaja.model.domain.Company;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyMapper implements EntityToDTOMapper<Company, CompanyDTO> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public Company createEntity(CompanyDTO companyDTO) {
        return mapper.map(companyDTO, Company.class);
    }

    @Override
    public Company updateEntity(CompanyDTO companyDTO) {
        return null;
    }

    @Override
    public CompanyDTO convertToDto(Company company) {
        return mapper.map(company, CompanyDTO.class);
    }

    @Override
    public List<CompanyDTO> convertToDto(Iterable<Company> companys) {
        List<CompanyDTO> dtos = new ArrayList<CompanyDTO>();
        for (Company entity : companys) {
            dtos.add(convertToDto(entity));
        }
        return dtos;
    }

    @Override
    public Company convertToEntity(CompanyDTO companyDTO) {
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return mapper.map(companyDTO, Company.class);
    }

    @Override
    public List<Company> convertToEntity(Iterable<CompanyDTO> companyDTOS) {
        return null;
    }
}
