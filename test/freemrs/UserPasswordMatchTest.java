/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package freemrs;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nuwan Prabhath
 */
public class UserPasswordMatchTest {
    
    Userinfo user; //To test user related data
    
    public UserPasswordMatchTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        try {
            user = new Userinfo("nuwan","doctor",UserPasswordMatch.getHash("nuwan"),"Your best friend name?","amal");
        } catch (Exception ex) {
            Logger.getLogger(UserPasswordMatchTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getHash method, of class UserPasswordMatch.
     */
    @Test
    public void testGetHash() throws Exception {
        System.out.println("getHash");
        String password = "nuwan";
        String expResult = "[-124, -47, -36, -73, -60, 49, 1, -19, 113, -82, -40, -118, 21, 57, -108, 45, -92, -27, 123, 69, 81, 83, 118, 76, 48, 64, -98, -13, -114, 47, 93, 57]";
        String result = UserPasswordMatch.getHash(password);
        assertEquals(expResult, result);
    }

    /**
     * Test of compareData method, of class UserPasswordMatch.
     */
    @Test
    public void testCompareData() throws Exception {
        System.out.println("compareData");
        String password = "nuwan";
        boolean expResult = true;
        boolean result = UserPasswordMatch.compareData(user, password);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of changePassword method, of class UserPasswordMatch.
     */
    @Test
    public void testChangePassword() throws Exception {
        System.out.println("changePassword");
        String curPassword = "nuwan";
        String newPassword = "nu";
        boolean expResult = true;
        boolean result = UserPasswordMatch.changePassword(user, curPassword, newPassword);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of compareQuestion method, of class UserPasswordMatch.
     */
    @Test
    public void testCompareQuestion() throws Exception {
        System.out.println("compareQuestion");
        String answer = "amal";
        boolean expResult = false;
        boolean result = UserPasswordMatch.compareQuestion(user, answer);
        assertEquals(expResult, result);
        
    }
    
}
