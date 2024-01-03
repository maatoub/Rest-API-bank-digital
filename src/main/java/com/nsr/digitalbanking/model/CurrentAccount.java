package com.nsr.digitalbanking.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue(value = "CA")
@Entity
public class CurrentAccount extends BankAccount {
    private double overDraft;
}
