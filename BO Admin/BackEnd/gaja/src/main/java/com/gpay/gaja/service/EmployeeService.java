package com.gpay.gaja.service;

import java.util.List;

import com.gpay.gaja.common.dto.EmployeeDTO;

public interface EmployeeService {

    EmployeeDTO findById(Long id);

    EmployeeDTO save(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getAll();

    List<EmployeeDTO> getCompanyId(Long companyId);

}
