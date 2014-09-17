/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package freemrs;

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
public class UserinfoTest {
    Userinfo instance;
    public UserinfoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {//String username, String type, String passwordhash, String question, String answer
        instance = new Userinfo("nuwan","doctor","123","Best teacher","me");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getUsername method, of class Userinfo.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        String expResult = "nuwan";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setUsername method, of class Userinfo.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "nuwan";
        Userinfo instance = new Userinfo();
        instance.setUsername(username);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getType method, of class Userinfo.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        String expResult = "doctor";
        String result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setType method, of class Userinfo.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String type = "doctor";
        Userinfo instance = new Userinfo();
        instance.setType(type);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getPasswordhash method, of class Userinfo.
     */
    @Test
    public void testGetPasswordhash() {
        System.out.println("getPasswordhash");
        String expResult = "123";
        String result = instance.getPasswordhash();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPasswordhash method, of class Userinfo.
     */
    @Test
    public void testSetPasswordhash() {
        System.out.println("setPasswordhash");
        String passwordhash = "1234";
        Userinfo instance = new Userinfo();
        instance.setPasswordhash(passwordhash);
    }

    /**
     * Test of getQuestion method, of class Userinfo.
     */
    @Test
    public void testGetQuestion() {
        System.out.println("getQuestion");
        String expResult = "Best teacher";
        String result = instance.getQuestion();
        assertEquals(expResult, result);
    }

    /**
     * Test of setQuestion method, of class Userinfo.
     */
    @Test
    public void testSetQuestion() {
        System.out.println("setQuestion");
        String question = "best teacher";
        Userinfo instance = new Userinfo();
        instance.setQuestion(question);
    }

    /**
     * Test of getAnswer method, of class Userinfo.
     */
    @Test
    public void testGetAnswer() {
        System.out.println("getAnswer");
        String expResult = "me";
        String result = instance.getAnswer();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAnswer method, of class Userinfo.
     */
    @Test
    public void testSetAnswer() {
        System.out.println("setAnswer");
        String answer = "me";
        Userinfo instance = new Userinfo();
        instance.setAnswer(answer);
    }

    /**
     * Test of toString method, of class Userinfo.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Userinfo instance = new Userinfo();
        String expResult = null;
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
