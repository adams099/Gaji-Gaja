package com.gpay.gaja.model.repository;

import com.gpay.gaja.model.domain.Company;
import com.gpay.gaja.model.domain.Attachment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttachmentRepository extends CrudRepository<Attachment, Long> {

    Optional<Attachment> findById(Long id);

    Optional<Attachment> findByName(String fileName);

    Optional<Attachment> findByCompanyIdAndName(Company id, String fileName);
}
