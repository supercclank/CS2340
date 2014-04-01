package com.example.financialplanner;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * Tests the methods of Register
 * @author Sandeep
 *
 */
public class SandeepJUnit {

    /*
     * Instance of Register to be tested
     */
    private Register tester;
    
    @Before
    /**
     * Setup method to initialize Register object and add one valid user
     */
    public void setup() {
        tester = new Register();
        tester.addUser("TestUser","RandomPassword!");
        
        
    }
    @Test
    /**
     * Test method for Register's checkInfromation(string,string) method.
     */
    public void test() {
        assertTrue("Username: TestUser PW: RandomPassword! exists",tester.checkInformation("TestUser","RandomPassword!"));
        assertFalse("Username: noUser PW: nothing  does not exist",tester.checkInformation("noUser","nothing"));
        assertTrue("Admin exists",tester.checkInformation("admin_", "admin1"));
        assertFalse("First Parameter Null",tester.checkInformation("TestUser",null));
        assertFalse("Second Parameter Null",tester.checkInformation(null,"RandomPassword!"));
        assertFalse("Both Parameters Null",tester.checkInformation(null,null));
        
    }

}
