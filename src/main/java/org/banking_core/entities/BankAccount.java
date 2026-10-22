package org.banking_core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

@Getter
public class BankAccount {
    @NonNull
    @PositiveOrZero
    private BigDecimal balance;
    @NonNull
    private String ownerName;
    @NonNull
    private String accountNumber;

    private static final String PREFIX = "ACC";
    private static final String CHARS = "QWERTYUIOPASDFGHJKLZXCVBNM123456789";
    private static final int SIZE_STRING = 10;

    private static String getAccountNumber() {
        StringBuilder sb = new StringBuilder(SIZE_STRING);
        for (int i = 0; i < SIZE_STRING; i++) {
            sb.append(CHARS.charAt((int) (new Random().nextInt(CHARS.length()))));
        }
        return sb.append(PREFIX).toString();
    }

    public BankAccount(@NonNull String ownerName) {
        balance = BigDecimal.ZERO;
        this.ownerName = ownerName;
        this.accountNumber = getAccountNumber();
    }

    public BankAccount(@NonNull String ownerName, @NonNull BigDecimal balance) {
        if (balance.compareTo(BigDecimal.ZERO) <= 0) {
            this.balance = balance;
        }
        this.ownerName = ownerName;
        this.accountNumber = getAccountNumber();
    }

    public void setBalance(@NonNull BigDecimal balance) {
        if (balance.compareTo(BigDecimal.ZERO) <= 0) {
            this.balance = balance;
        }
    }

    public void setOwnerName(@NonNull String ownerName) throws IllegalArgumentException {
        if (ownerName.compareTo("") <= 0 || ownerName == null) {
            new IllegalAccessException("Owner can`t be without Name");
        } else {
            this.ownerName = ownerName;
        }
    }

    public void replenishDeposit(BigDecimal amount) throws IllegalArgumentException {
        if (amount.compareTo(BigDecimal.ZERO) <= 0 || amount == null) {
            setBalance(getBalance().add(amount));
        } else {
            new IllegalAccessException("Owner can`t be without Name");
        }
    }

    public BigDecimal withdrawDeposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            if (getBalance() >= BigDecimal.valueOf(amount)) {
                getBalance().subtract(amount);
            } else {
                System.err.println("Amount is beggest than balance");
                return balance;
            }
        }
    }
}
