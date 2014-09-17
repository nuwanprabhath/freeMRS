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
public class GeneralMedicalInfoTest {
    GeneralMedicalInfo instance;
    public GeneralMedicalInfoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before   //int patientId, String mainMedicalProblem, String medicalProblems, String allergies, String immunizations
    public void setUp() {
        instance = new  GeneralMedicalInfo(1,"blood","pressure","none","insiuline");
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPatientId method, of class GeneralMedicalInfo.
     */
    @Test
    public void testGetPatientId() {
        System.out.println("getPatientId");
        int expResult = 1;
        int result = instance.getPatientId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPatientId method, of class GeneralMedicalInfo.
     */
    @Test
    public void testSetPatientId() {
        System.out.println("setPatientId");
        int patientId = 0;
        GeneralMedicalInfo instance = new GeneralMedicalInfo();
        instance.setPatientId(patientId);
    }

    /**
     * Test of getMainMedicalProblem method, of class GeneralMedicalInfo.
     */
    @Test
    public void testGetMainMedicalProblem() {
        System.out.println("getMainMedicalProblem");
        String expResult = "blood";
        String result = instance.getMainMedicalProblem();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMainMedicalProblem method, of class GeneralMedicalInfo.
     */
    @Test
    public void testSetMainMedicalProblem() {
        System.out.println("setMainMedicalProblem");
        String mainMedicalProblem = "pressure";
        GeneralMedicalInfo instance = new GeneralMedicalInfo();
        instance.setMainMedicalProblem(mainMedicalProblem);
    }

    /**
     * Test of getMedicalProblems method, of class GeneralMedicalInfo.
     */
    @Test
    public void testGetMedicalProblems() {
        System.out.println("getMedicalProblems");
        String expResult = "pressure";
        String result = instance.getMedicalProblems();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMedicalProblems method, of class GeneralMedicalInfo.
     */
    @Test
    public void testSetMedicalProblems() {
        System.out.println("setMedicalProblems");
        String medicalProblems = "pressure";
        instance.setMedicalProblems(medicalProblems);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getAllergies method, of class GeneralMedicalInfo.
     */
    @Test
    public void testGetAllergies() {
        System.out.println("getAllergies");
        String expResult = "none";
        String result = instance.getAllergies();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAllergies method, of class GeneralMedicalInfo.
     */
    @Test
    public void testSetAllergies() {
        System.out.println("setAllergies");
        String allergies = "";
        GeneralMedicalInfo instance = new GeneralMedicalInfo();
        instance.setAllergies(allergies);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getImmunizations method, of class GeneralMedicalInfo.
     */
    @Test
    public void testGetImmunizations() {
        System.out.println("getImmunizations");
        String expResult = "insiuline";
        String result = instance.getImmunizations();
        assertEquals(expResult, result);
    }

    /**
     * Test of setImmunizations method, of class GeneralMedicalInfo.
     */
    @Test
    public void testSetImmunizations() {
        System.out.println("setImmunizations");
        String immunizations = "";
        GeneralMedicalInfo instance = new GeneralMedicalInfo();
        instance.setImmunizations(immunizations);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
