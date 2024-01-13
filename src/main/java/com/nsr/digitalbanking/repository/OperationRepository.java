package com.nsr.digitalbanking.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nsr.digitalbanking.model.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    List<Operation> findByAccountId(String id);

    Page<Operation> findByAccountId(String accountId, Pageable pageable);
}