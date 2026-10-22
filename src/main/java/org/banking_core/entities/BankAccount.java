package org.banking_core.entities;

import lombok.Getter;
import lombok.NonNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.Random;

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
    private static Random random = new Random();

    private static String generateAccountNumber() {
        StringBuilder sb = new StringBuilder(SIZE_STRING);
        for (int i = 0; i < SIZE_STRING; i++) {
            sb.append(CHARS.charAt((int) (random.nextInt(CHARS.length()))));
        }
        return PREFIX + sb;
    }

    public BankAccount(@NonNull BigDecimal balance, @NonNull String ownerName) {
        if (balance.compareTo(BigDecimal.ZERO) >= 0) {
            this.balance = balance;
        } else {
            this.balance = BigDecimal.ZERO;
        }
        this.ownerName = ownerName;
        this.accountNumber = getAccountNumber();
    }

    public void setBalance(@NonNull BigDecimal balance) {
        this.balance = balance;
    }

    public void setOwnerName(@NonNull String ownerName) throws IllegalArgumentException {
        if (ownerName.isBlank()) {
            throw new IllegalArgumentException("Owner can`t be without Name");
        } else {
            this.ownerName = ownerName;
        }
    }

    public void replenishDeposit(@NonNull BigDecimal amount) throws IllegalArgumentException {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            setBalance(getBalance().add(amount));
        } else {
            throw new IllegalArgumentException("Owner can`t be without Name");
        }
    }

    public void withdrawDeposit(@NonNull BigDecimal amount) throws IllegalArgumentException {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero");
        } else {
            if (amount.compareTo(getBalance()) <= 0) {
                setBalance(getBalance().subtract(amount));
            } else {
                throw new IllegalArgumentException("Insufficient funds");
            }
        }
    }
}