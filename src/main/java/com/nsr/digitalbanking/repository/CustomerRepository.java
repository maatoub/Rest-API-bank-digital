package com.nsr.digitalbanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nsr.digitalbanking.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
