package com.nsr.digitalbanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.nsr.digitalbanking.model.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    List<BankAccount> findByCustomerId(Long accountId);
}