package com.example.financialplanner;
/**
 * 
 */
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests for addUser() in Register.java.
 * 
 * @author Edward Sum Lok Yu
 *
 */
public class EdwardJUnit {
    /**
     * private Register used for testing.
     */
    private Register register;
    /**
     * Method that sets up the register to be used for testing, ran before each test.
     */
    @Before
    public void setup() {
        register = new Register();
    }
    /**
     * Method that tests adding a single user.
     */
    @Test
    public void testAddUser() {
        //CHECKSTYLE.OFF: String literal - literal needed for tests
        assertTrue(register.addUser("georgeBurdell", "GT1927"));
        //CHECKSTYLE.ON: String literal
        assertTrue(register.getUsers().containsValue(new User("georgeBurdell", "GT1927")));
    }
    /**
     * Methid that tests adding a duplicate user.
     */
    @Test
    public void testAddDuplicate() {
        //CHECKSTYLE.OFF: String literal - literal needed for tests
        register.addUser("coryB", "jersey");
        //CHECKSTYLE.ON: String literal
        assertFalse(register.addUser("coryB", "jersey"));
        //CHECKSTYLE.OFF: String literal - literal needed for tests
        assertFalse(register.addUser("coryB", "newJersey"));
        //CHECKSTYLE.ON: String literal
        // only the original user, password combination should be kept
        HashMap<String, User> map = register.getUsers();
        assertTrue(map.get("coryB").checkPass("jersey"));
        assertFalse(map.get("coryB").checkPass("newJersey"));
    }

}
