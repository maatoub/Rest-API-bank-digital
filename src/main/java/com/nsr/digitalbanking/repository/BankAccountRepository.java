package com.nsr.digitalbanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nsr.digitalbanking.model.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

}
