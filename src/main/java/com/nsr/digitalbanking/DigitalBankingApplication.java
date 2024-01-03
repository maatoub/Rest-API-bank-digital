package com.nsr.digitalbanking;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nsr.digitalbanking.enums.AccountStatus;
import com.nsr.digitalbanking.enums.OperationType;
import com.nsr.digitalbanking.model.CurrentAccount;
import com.nsr.digitalbanking.model.Customer;
import com.nsr.digitalbanking.model.Operation;
import com.nsr.digitalbanking.model.SavingAccount;
import com.nsr.digitalbanking.repository.BankAccountRepository;
import com.nsr.digitalbanking.repository.CustomerRepository;
import com.nsr.digitalbanking.repository.OperationRepository;

@SpringBootApplication
public class DigitalBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalBankingApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			CustomerRepository repoCustomer,
			BankAccountRepository repoAccount,
			OperationRepository repoOperation) {

		return args -> {
			Stream.of("ahmed", "nasser").forEach(cus -> {
				Customer customer = new Customer();
				customer.setName(cus);
				customer.setEmail(cus + "@gmail.com");
				repoCustomer.save(customer);
			});

			List<Customer> customers = repoCustomer.findAll();
			customers.forEach(cus -> {
				for (int index = 0; index < 5; index++) {
					CurrentAccount currentAccount = new CurrentAccount();
					currentAccount.setCustomer(cus);
					currentAccount.setBalance(Math.random() * 56923);
					currentAccount.setCreatedAt(new Date());
					currentAccount.setId(UUID.randomUUID().toString());
					currentAccount.setOverDraft(Math.random() * 4000);
					currentAccount.setStatus(AccountStatus.CREATED);
					repoAccount.save(currentAccount);
				}

			});

			customers.forEach(cus -> {
				for (int index = 0; index < 5; index++) {
					SavingAccount savingAccount = new SavingAccount();
					savingAccount.setCustomer(cus);
					savingAccount.setBalance(Math.random() * 55923);
					savingAccount.setCreatedAt(new Date());
					savingAccount.setId(UUID.randomUUID().toString());
					savingAccount.setInterestRate(2.6);
					savingAccount.setStatus(AccountStatus.CREATED);
					repoAccount.save(savingAccount);
				}
			});

			/* **************** */

			repoAccount.findAll().forEach(acc -> {
				for (int index = 0; index < 5; index++) {
					Operation op = new Operation();
					op.setAccount(acc);
					op.setAmount(Math.random() * 20000);
					op.setOpDate(new Date());
					op.setType(Math.random() > 0.5 ? OperationType.CREDIT : OperationType.DEBIT);
					repoOperation.save(op);
				}
			});
		};
	}

}
