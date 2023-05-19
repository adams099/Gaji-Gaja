package com.gpay.gaja.model.repository;

import com.gpay.gaja.model.domain.Company;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

    Optional<Company> findById(Long id);

    Long countByStatus(int status);
}
