package com.example.financialplanner;
/**
 * 
 */
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
/**
 * Tests the methods of Register.
 * @author Sandeep
 *
 */
public class SandeepJUnit {

    /**
     * Instance of Register to be tested.
     */
    private Register tester;
    
    
    /**
     * Setup method to initialize Register object and add one valid user.
     */
    @Before
    public void setup() {
        tester = new Register();
        //CHECKSTYLE.OFF: String Literal - need of String literal used
        tester.addUser("TestUser", "RandomPassword!");
        //CHECKSTYLE.ON: String Literal - need of String literal used   
    }
    
    /**
     * Test method for Register's checkInfromation(string,string) method.
     */
    @Test
    public void test() {
        assertTrue("Username: TestUser PW: RandomPassword! exists", tester.checkInformation("TestUser", "RandomPassword!"));
        assertFalse("Username: noUser PW: nothing  does not exist", tester.checkInformation("noUser", "nothing"));
        assertTrue("Admin exists", tester.checkInformation("admin_", "admin1"));
        assertFalse("First Parameter Null", tester.checkInformation("TestUser", null));
        assertFalse("Second Parameter Null", tester.checkInformation(null, "RandomPassword!"));
        assertFalse("Both Parameters Null", tester.checkInformation(null, null));
        
    }

}
