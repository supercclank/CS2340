package com.example.financialplanner;

import static org.junit.Assert.*;

import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests for addUser() in Register.java
 * 
 * @author Edward Sum Lok Yu
 *
 */
public class EdwardJUnit {
    
    private Register register;
    
    @Before
    public void setup() {
        register = new Register();
    }

    @Test
    public void testAddUser() {
        assertTrue(register.addUser("georgeBurdell", "GT1927"));
        assertTrue(register.getUsers().containsValue(new User("georgeBurdell", "GT1927")));
    }
    
    @Test
    public void testAddDuplicate() {
        register.addUser("coryB", "jersey");
        
        assertFalse(register.addUser("coryB", "jersey"));
        assertFalse(register.addUser("coryB", "newJersey"));
        
        // only the original user, password combination should be kept
        HashMap<String, User> map = register.getUsers();
        assertTrue(map.get("coryB").checkPass("jersey"));
        assertFalse(map.get("coryB").checkPass("newJersey"));
    }

}
