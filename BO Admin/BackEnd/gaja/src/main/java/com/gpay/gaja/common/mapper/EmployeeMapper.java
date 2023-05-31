package com.gpay.gaja.common.mapper;

import com.gpay.gaja.common.dto.EmployeeDTO;
import com.gpay.gaja.model.domain.Employee;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeMapper implements EntityToDTOMapper<Employee, EmployeeDTO> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public Employee createEntity(EmployeeDTO employeeDTO) {
        return mapper.map(employeeDTO, Employee.class);
    }

    @Override
    public Employee updateEntity(EmployeeDTO dto) {
        return null;
    }

    @Override
    public EmployeeDTO convertToDto(Employee employee) {
        return mapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> convertToDto(Iterable<Employee> employees) {
        List<EmployeeDTO> dtos = new ArrayList<EmployeeDTO>();
        for (Employee entity : employees) {
            dtos.add(convertToDto(entity));
        }
        return dtos;
    }

    @Override
    public Employee convertToEntity(EmployeeDTO employeeDTO) {
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return mapper.map(employeeDTO, Employee.class);
    }

    @Override
    public List<Employee> convertToEntity(Iterable<EmployeeDTO> employeeDTOS) {
        return null;
    }

}
