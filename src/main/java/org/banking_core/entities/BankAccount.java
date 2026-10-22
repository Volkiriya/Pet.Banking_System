package org.banking_core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
@Setter
@Getter
@AllArgsConstructor
public class BankAccount {
    @NonNull
    @PositiveOrZero
    private BigDecimal balance;
    @NonNull
    private String ownerName;
    @NonNull
    private BigDecimal accountNumber;

    public void replenishDeposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            setBalance(getBalance().add(amount));
        }
    }

    public BigDecimal withdrawDeposit(BigDecimal amount) {
        if  (amount.compareTo(BigDecimal.ZERO) > 0) {
            if (setBalance(getBalance().subtract(amount)) >= amount.compareTo(BigDecimal.ZERO) > 0 ) {
               return setBalance(getBalance().subtract(amount));
            } else {
                System.err.println("Amount is beggest than balance");
                return balance;
            }
        }
    }
}
