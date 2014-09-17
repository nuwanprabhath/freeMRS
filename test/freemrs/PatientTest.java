/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package freemrs;

import java.util.Date;
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
public class PatientTest {
    Patient instance;
    Date date;
    public PatientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {//String name, Date birthday, String sex, String address, String maritalStatus, String job, String phone
        date = new Date();
        instance = new Patient("nuwan",date,"male","8-D","single","student","1234");
        instance.setPatientId(1);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPatientId method, of class Patient.
     */
    @Test
    public void testGetPatientId() {
        System.out.println("getPatientId");
        Integer expResult = 1;
        Integer result = instance.getPatientId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPatientId method, of class Patient.
     */
    @Test
    public void testSetPatientId() {
        System.out.println("setPatientId");
        Integer patientId = 1;
        Patient instance = new Patient();
        instance.setPatientId(patientId);
    }

    /**
     * Test of getName method, of class Patient.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "nuwan";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Patient.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "nuwan";
        Patient instance = new Patient();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getBirthday method, of class Patient.
     */
    @Test
    public void testGetBirthday() {
        System.out.println("getBirthday");
        Date expResult = date;
        Date result = instance.getBirthday();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setBirthday method, of class Patient.
     */
    @Test
    public void testSetBirthday() {
        System.out.println("setBirthday");
        Date birthday = null;
        Patient instance = new Patient();
        instance.setBirthday(birthday);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getSex method, of class Patient.
     */
    @Test
    public void testGetSex() {
        System.out.println("getSex");
        String expResult = "male";
        String result = instance.getSex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setSex method, of class Patient.
     */
    @Test
    public void testSetSex() {
        System.out.println("setSex");
        String sex = "";
        Patient instance = new Patient();
        instance.setSex(sex);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getAddress method, of class Patient.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        String expResult = "8-D";
        String result = instance.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAddress method, of class Patient.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        String address = "8-D";
        Patient instance = new Patient();
        instance.setAddress(address);
    }

    /**
     * Test of getMaritalStatus method, of class Patient.
     */
    @Test
    public void testGetMaritalStatus() {
        System.out.println("getMaritalStatus");
        String expResult = "single";
        String result = instance.getMaritalStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMaritalStatus method, of class Patient.
     */
    @Test
    public void testSetMaritalStatus() {
        System.out.println("setMaritalStatus");
        String maritalStatus = "single";
        Patient instance = new Patient();
        instance.setMaritalStatus(maritalStatus);
    }

    /**
     * Test of getJob method, of class Patient.
     */
    @Test
    public void testGetJob() {
        System.out.println("getJob");
        String expResult = "student";
        String result = instance.getJob();
        assertEquals(expResult, result);
    }

    /**
     * Test of setJob method, of class Patient.
     */
    @Test
    public void testSetJob() {
        System.out.println("setJob");
        String job = "student";
        Patient instance = new Patient();
        instance.setJob(job);
    }

    /**
     * Test of getPhone method, of class Patient.
     */
    @Test
    public void testGetPhone() {
        System.out.println("getPhone");
        String expResult = "1234";
        String result = instance.getPhone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setPhone method, of class Patient.
     */
    @Test
    public void testSetPhone() {
        System.out.println("setPhone");
        String phone = "1234";
        Patient instance = new Patient();
        instance.setPhone(phone);
    }

    /**
     * Test of toString method, of class Patient.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "nuwan";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
