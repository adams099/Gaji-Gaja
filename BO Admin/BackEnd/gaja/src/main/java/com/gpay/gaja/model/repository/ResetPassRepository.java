package com.gpay.gaja.model.repository;

import com.gpay.gaja.model.domain.ResetPass;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResetPassRepository extends CrudRepository<ResetPass, Long> {

    Optional<ResetPass> findById(Long id);

    List<ResetPass> findByEmail(String email);

}
