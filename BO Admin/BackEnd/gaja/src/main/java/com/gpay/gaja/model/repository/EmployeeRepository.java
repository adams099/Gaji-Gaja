package com.gpay.gaja.model.repository;

import com.gpay.gaja.model.domain.Employee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Optional<Employee> findById(Long id);

    List<Employee> findByCompanyId(Long companyId);
}
