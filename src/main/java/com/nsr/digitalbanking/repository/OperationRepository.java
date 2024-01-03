package com.nsr.digitalbanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nsr.digitalbanking.model.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {

}