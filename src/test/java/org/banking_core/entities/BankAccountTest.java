package org.banking_core.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class BankAccountTest {

    BankAccount bankAccount;

    private void assertMoneyEquals(String expected, BigDecimal actual) {
        Assertions.assertEquals(
                0,
                actual.compareTo(new BigDecimal(expected))
        );
    }

    @BeforeEach
    void setUp() {
        bankAccount = new BankAccount(new BigDecimal("1000"), "John Doe");
    }

    @Test
    void replenishDeposit() {
        bankAccount.replenishDeposit(new BigDecimal("200"));
        assertMoneyEquals("1200", bankAccount.getBalance());
    }

    @Test
    void withdrawDeposit() {
        bankAccount.withdrawDeposit(new BigDecimal("200"));
        assertMoneyEquals("800", bankAccount.getBalance());
    }

    @Test
    void withdrawDeposit_InsufficientFunds() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> bankAccount.withdrawDeposit(new BigDecimal("1001")));
    }

    @Test
    void withdrawDeposit_ZeroAmount() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> bankAccount.withdrawDeposit(new BigDecimal("0")));
        assertMoneyEquals("1000", bankAccount.getBalance());
    }

    @Test
    void withdrawDeposit_NegativeAmount() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> bankAccount.withdrawDeposit(new BigDecimal(-100)));
        assertMoneyEquals("1000", bankAccount.getBalance());
    }

    @Test
    void withdrawDeposit_WithdrawAll() {
        bankAccount.withdrawDeposit(new BigDecimal("1000"));
        assertMoneyEquals("0", bankAccount.getBalance());
    }
}