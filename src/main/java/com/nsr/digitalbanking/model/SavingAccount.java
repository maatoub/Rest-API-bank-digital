package com.nsr.digitalbanking.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue(value = "SA")
@Entity
public class SavingAccount extends BankAccount{
    private double interestRate;
}
