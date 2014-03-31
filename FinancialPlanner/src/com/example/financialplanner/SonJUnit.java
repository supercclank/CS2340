package com.example.financialplanner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
/**
 * Son's JUnits.
 * @author Son Tran
 *
 */
public class SonJUnit {
    /**
     * private account used in this JUnit.
     */
    private Account anAccount;
    /**
     * Ran before the tests to setup anAccount.
     */
    @Before
    public void setup() {
        //CHECKSTYLE.OFF: String - suppressing repeated Strings (needed for unit testing)
        anAccount = new Account("John Smith", "jsmith96", 256.0, 3.4);
        //CHECKSTYLE.ON: String
    }
    /**
     * Method used to testEquals of Account class.
     */
    @Test(timeout = 200)
    public void testEquals() {
        Account nullAccount = null;
        assertFalse(anAccount.equals(nullAccount));

        Admin anAdmin = new Admin("stran7", "Hello");
        assertFalse(anAccount.equals(anAdmin));

        Account randomAccount = new Account("John Smith", "jaysmith", 256.0, 3.4);
        assertTrue(anAccount.equals(randomAccount));

        randomAccount = new Account("Jay Smith", "jsmith96", 256.0, 3.4);
        assertTrue(anAccount.equals(randomAccount));

        randomAccount = new Account("John Smith", "jsmith96", 350.0, 0.5);
        assertTrue(anAccount.equals(randomAccount));
    }
}
