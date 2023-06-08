package com.gpay.gaja.service.impl;

import com.gpay.gaja.common.dto.EmployeeDTO;
import com.gpay.gaja.common.mapper.EmployeeMapper;
import com.gpay.gaja.model.domain.Employee;
import com.gpay.gaja.model.repository.EmployeeRepository;
import com.gpay.gaja.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private EmployeeMapper mapper;

    @Override
    public EmployeeDTO findById(Long id) {
        Optional<Employee> employeOptional = repository.findById(id);
        return mapper.convertToDto(employeOptional.get());
    }

    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        return mapper.convertToDto(repository.save(mapper.convertToEntity(employeeDTO)));
    }

    @Override
    public List<EmployeeDTO> getAll() {
        Iterable<Employee> test = repository.findAll();
        return mapper.convertToDto(test);
    }

    @Override
    public List<EmployeeDTO> getCompanyId(Long companyId) {
        List<Employee> employees = repository.findByCompanyId(companyId);
        return mapper.convertToDto(employees);
    }

}
