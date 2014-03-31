package com.example.financialplanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


import java.util.Date;
import org.junit.Test;
/**
 * Cory's JUnit Test.
 * @author Cory
 *
 */
public class CoryJUnit {
    /**
     * Method that tests preformTransaction, that in turn tests
     * private method run transaction (preformTransaction has no conditionals
     * but run has many, tests public method to ensure private method runs as intended).
     */
    @Test
    public void testDeposit() {
        Account testAccount = new Account("HERP", "DERP", 0, Math.PI);
        assertTrue(testAccount.getBalance() == 0);
        assertTrue(testAccount.performTransaction(new Transaction(Transaction.TransactionType.DEPOSIT, 0, new Date(), new Date(), "desposit 1")));
        assertEquals(0, testAccount.getBalance(), 0);
        assertFalse(testAccount.getBalance() == 1);
        assertTrue(testAccount.performTransaction(new Transaction(Transaction.TransactionType.DEPOSIT, 100, new Date(), new Date(), "desposit 2")));
        assertEquals(100, testAccount.getBalance(), 0);
        assertFalse(testAccount.getBalance() == 0);
        assertTrue(testAccount.performTransaction(new Transaction(Transaction.TransactionType.DEPOSIT, 20, new Date(), new Date(), "desposit 3")));
        assertEquals(120, testAccount.getBalance(), 0);
        assertFalse(testAccount.getBalance() == 0);
        assertEquals(3, testAccount.getDeposits().size());
        assertEquals(3, testAccount.getTransactions().size());
        assertEquals(0, testAccount.getWithdrawals().size());
    }
    /**
     * Method that tests performing withdrawals and deposits.
     */
    @Test
    public void testMixed() {
        Account testAccount = new Account("HAS", "CHEEZEBURGER", 0, Math.PI);
        assertTrue(testAccount.getBalance() == 0);
        assertTrue(testAccount.performTransaction(new Transaction(Transaction.TransactionType.DEPOSIT, 0, new Date(), new Date(), "mix - desposit 1")));
        assertEquals(0, testAccount.getBalance(), 0);
        assertFalse(testAccount.getBalance() == 1);
        assertTrue(testAccount.performTransaction(new Transaction(Transaction.TransactionType.DEPOSIT, 100, new Date(), new Date(), "mix - desposit 2")));
        assertEquals(100, testAccount.getBalance(), 0);
        assertFalse(testAccount.getBalance() == 0);
        assertTrue(testAccount.performTransaction(new Transaction(Transaction.TransactionType.DEPOSIT, 20, new Date(), new Date(), "mix - desposit 3")));
        assertEquals(120, testAccount.getBalance(), 0);
        assertFalse(testAccount.getBalance() == 0);
        assertEquals(3, testAccount.getDeposits().size());
        assertEquals(3, testAccount.getTransactions().size());
        assertEquals(0, testAccount.getWithdrawals().size());
        assertTrue(testAccount.getBalance() == 120);
        assertTrue(testAccount.performTransaction(new Transaction(Transaction.TransactionType.WITHDRAW, 0, new Date(), new Date(), "mix - withdraw 1")));
        assertEquals(120, testAccount.getBalance(), 0);
        assertFalse(testAccount.getBalance() == 1);
        assertTrue(testAccount.performTransaction(new Transaction(Transaction.TransactionType.WITHDRAW, 100, new Date(), new Date(), "mix - withdraw 2")));
        assertEquals(20, testAccount.getBalance(), 0);
        assertFalse(testAccount.getBalance() == 0);
        assertTrue(testAccount.performTransaction(new Transaction(Transaction.TransactionType.WITHDRAW, 20, new Date(), new Date(), "mix - withdraw 3")));
        assertEquals(0, testAccount.getBalance(), 0);
        assertFalse(testAccount.getBalance() == 20);
        assertEquals(3, testAccount.getDeposits().size());
        assertEquals(6, testAccount.getTransactions().size());
        assertEquals(3, testAccount.getWithdrawals().size());
    }
    /**
     * Method that tests preforming withdrawals.
     */
    @Test
    public void testWithdraw() {
        Account testAccount = new Account("HURR", "HORSE", 120, Math.PI);
        assertTrue(testAccount.getBalance() == 120);
        assertTrue(testAccount.performTransaction(new Transaction(Transaction.TransactionType.WITHDRAW, 0, new Date(), new Date(), "withdraw 1")));
        assertEquals(120, testAccount.getBalance(), 0);
        assertFalse(testAccount.getBalance() == 1);
        assertTrue(testAccount.performTransaction(new Transaction(Transaction.TransactionType.WITHDRAW, 100, new Date(), new Date(), "withdraw 2")));
        assertEquals(20, testAccount.getBalance(), 0);
        assertFalse(testAccount.getBalance() == 0);
        assertTrue(testAccount.performTransaction(new Transaction(Transaction.TransactionType.WITHDRAW, 20, new Date(), new Date(), "withdraw 3")));
        assertEquals(0, testAccount.getBalance(), 0);
        assertFalse(testAccount.getBalance() == 20);
        assertEquals(0, testAccount.getDeposits().size());
        assertEquals(3, testAccount.getTransactions().size());
        assertEquals(3, testAccount.getWithdrawals().size());
    }

}
