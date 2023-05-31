package com.gpay.gaja.model.repository;

import com.gpay.gaja.model.domain.Employee;
import com.gpay.gaja.model.domain.Document;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {

    Optional<Document> findById(Long id);

    Optional<Document> findByName(String docName);

    Optional<Document> findByEmployeeIdAndName(Employee id, String docName);

}
