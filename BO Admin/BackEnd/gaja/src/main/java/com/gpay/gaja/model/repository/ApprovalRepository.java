package com.gpay.gaja.model.repository;

import com.gpay.gaja.model.domain.Approval;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApprovalRepository extends CrudRepository<Approval, Long> {

    Optional<Approval> findById(Long id);

    Optional<Approval> findByStatus(int status);
}
