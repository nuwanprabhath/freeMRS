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
public class InsuranceTest {
    Insurance instance;
    Date date;
    public InsuranceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {//String provider, String planName, Date effectiveDate, String policyNumber, int patientId
        date = new Date();
        instance= new Insurance("celinco","life",date,"1",1);
        instance.setInsuarenceId(2);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInsuarenceId method, of class Insurance.
     */
    @Test
    public void testGetInsuarenceId() {
        System.out.println("getInsuarenceId");
        Integer expResult = 2;
        Integer result = instance.getInsuarenceId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setInsuarenceId method, of class Insurance.
     */
    @Test
    public void testSetInsuarenceId() {
        System.out.println("setInsuarenceId");
        Integer insuarenceId = null;
        Insurance instance = new Insurance();
        instance.setInsuarenceId(insuarenceId);
    }

    /**
     * Test of getProvider method, of class Insurance.
     */
    @Test
    public void testGetProvider() {
        System.out.println("getProvider");
        String expResult = "celinco";
        String result = instance.getProvider();
        assertEquals(expResult, result);
    }

    /**
     * Test of setProvider method, of class Insurance.
     */
    @Test
    public void testSetProvider() {
        System.out.println("setProvider");
        String provider = "celinco";
        Insurance instance = new Insurance();
        instance.setProvider(provider);
    }

    /**
     * Test of getPlanName method, of class Insurance.
     */
    @Test
    public void testGetPlanName() {
        System.out.println("getPlanName");
        String expResult = "life";
        String result = instance.getPlanName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPlanName method, of class Insurance.
     */
    @Test
    public void testSetPlanName() {
        System.out.println("setPlanName");
        String planName = "life";
        Insurance instance = new Insurance();
        instance.setPlanName(planName);
    }

    /**
     * Test of getEffectiveDate method, of class Insurance.
     */
    @Test
    public void testGetEffectiveDate() {
        System.out.println("getEffectiveDate");
        Date expResult = date;
        Date result = instance.getEffectiveDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEffectiveDate method, of class Insurance.
     */
    @Test
    public void testSetEffectiveDate() {
        System.out.println("setEffectiveDate");
        Date effectiveDate = date;
        Insurance instance = new Insurance();
        instance.setEffectiveDate(effectiveDate);
    }

    /**
     * Test of getPolicyNumber method, of class Insurance.
     */
    @Test
    public void testGetPolicyNumber() {
        System.out.println("getPolicyNumber");
        String expResult = "1";
        String result = instance.getPolicyNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPolicyNumber method, of class Insurance.
     */
    @Test
    public void testSetPolicyNumber() {
        System.out.println("setPolicyNumber");
        String policyNumber = "1";
        Insurance instance = new Insurance();
        instance.setPolicyNumber(policyNumber);
    }

    /**
     * Test of getPatientId method, of class Insurance.
     */
    @Test
    public void testGetPatientId() {
        System.out.println("getPatientId");
        int expResult = 1;
        int result = instance.getPatientId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPatientId method, of class Insurance.
     */
    @Test
    public void testSetPatientId() {
        System.out.println("setPatientId");
        int patientId = 2;
        Insurance instance = new Insurance();
        instance.setPatientId(patientId);
    }
    
}
