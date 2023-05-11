package com.gpay.gaja.model.repository;

import com.gpay.gaja.model.domain.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends CrudRepository<Status, Long> {

    Optional<Status> findById(Long id);

}
