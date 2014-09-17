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
public class VitalsTest {
    Vitals instance;
    Date date;
    public VitalsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {//Date dateTime, float weight, float height, int bpSystolic, int bpDiastolic, int pulse, int temperature, String tempLocation, int oxygenSaturation, float bmi, String notes, int patientId
       date = new Date();
        instance= new Vitals(date,45F,2.5F,70,80,70,32,"oral",98,21F,"none",1);
        instance.setVitalId(2);
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getVitalId method, of class Vitals.
     */
    @Test
    public void testGetVitalId() {
        System.out.println("getVitalId");
        Integer expResult = 2;
        Integer result = instance.getVitalId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVitalId method, of class Vitals.
     */
    @Test
    public void testSetVitalId() {
        System.out.println("setVitalId");
        Integer vitalId = 2;
        Vitals instance = new Vitals();
        instance.setVitalId(vitalId);
    }

    /**
     * Test of getDateTime method, of class Vitals.
     */
    @Test
    public void testGetDateTime() {
        System.out.println("getDateTime");
        Date expResult = date;
        Date result = instance.getDateTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDateTime method, of class Vitals.
     */
    @Test
    public void testSetDateTime() {
        System.out.println("setDateTime");
        Date dateTime = date;
        Vitals instance = new Vitals();
        instance.setDateTime(dateTime);
    }

    /**
     * Test of getWeight method, of class Vitals.
     */
    @Test
    public void testGetWeight() {
        System.out.println("getWeight");
        float expResult = 45F;
        float result = instance.getWeight();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setWeight method, of class Vitals.
     */
    @Test
    public void testSetWeight() {
        System.out.println("setWeight");
        float weight = 45F;
        Vitals instance = new Vitals();
        instance.setWeight(weight);
    }

    /**
     * Test of getHeight method, of class Vitals.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        float expResult = 2.5F;
        float result = instance.getHeight();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setHeight method, of class Vitals.
     */
    @Test
    public void testSetHeight() {
        System.out.println("setHeight");
        float height = 2.5F;
        Vitals instance = new Vitals();
        instance.setHeight(height);
    }

    /**
     * Test of getBpSystolic method, of class Vitals.
     */
    @Test
    public void testGetBpSystolic() {
        System.out.println("getBpSystolic");
        int expResult = 70;
        int result = instance.getBpSystolic();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBpSystolic method, of class Vitals.
     */
    @Test
    public void testSetBpSystolic() {
        System.out.println("setBpSystolic");
        int bpSystolic = 70;
        Vitals instance = new Vitals();
        instance.setBpSystolic(bpSystolic);
    }

    /**
     * Test of getBpDiastolic method, of class Vitals.
     */
    @Test
    public void testGetBpDiastolic() {
        System.out.println("getBpDiastolic");
        int expResult = 80;
        int result = instance.getBpDiastolic();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBpDiastolic method, of class Vitals.
     */
    @Test
    public void testSetBpDiastolic() {
        System.out.println("setBpDiastolic");
        int bpDiastolic = 80;
        Vitals instance = new Vitals();
        instance.setBpDiastolic(bpDiastolic);
    }

    /**
     * Test of getPulse method, of class Vitals.
     */
    @Test
    public void testGetPulse() {
        System.out.println("getPulse");
        int expResult = 70;
        int result = instance.getPulse();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPulse method, of class Vitals.
     */
    @Test
    public void testSetPulse() {
        System.out.println("setPulse");
        int pulse = 70;
        Vitals instance = new Vitals();
        instance.setPulse(pulse);
    }

    /**
     * Test of getTemperature method, of class Vitals.
     */
    @Test
    public void testGetTemperature() {
        System.out.println("getTemperature");
        int expResult = 32;
        int result = instance.getTemperature();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTemperature method, of class Vitals.
     */
    @Test
    public void testSetTemperature() {
        System.out.println("setTemperature");
        int temperature = 32;
        Vitals instance = new Vitals();
        instance.setTemperature(temperature);
    }

    /**
     * Test of getTempLocation method, of class Vitals.
     */
    @Test
    public void testGetTempLocation() {
        System.out.println("getTempLocation");
        String expResult = "oral";
        String result = instance.getTempLocation();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTempLocation method, of class Vitals.
     */
    @Test
    public void testSetTempLocation() {
        System.out.println("setTempLocation");
        String tempLocation = "oral";
        Vitals instance = new Vitals();
        instance.setTempLocation(tempLocation);
    }

    /**
     * Test of getOxygenSaturation method, of class Vitals.
     */
    @Test
    public void testGetOxygenSaturation() {
        System.out.println("getOxygenSaturation");
        int expResult = 98;
        int result = instance.getOxygenSaturation();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOxygenSaturation method, of class Vitals.
     */
    @Test
    public void testSetOxygenSaturation() {
        System.out.println("setOxygenSaturation");
        int oxygenSaturation = 98;
        Vitals instance = new Vitals();
        instance.setOxygenSaturation(oxygenSaturation);
    }

    /**
     * Test of getBmi method, of class Vitals.
     */
    @Test
    public void testGetBmi() {
        System.out.println("getBmi");
        float expResult = 21F;
        float result = instance.getBmi();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setBmi method, of class Vitals.
     */
    @Test
    public void testSetBmi() {
        System.out.println("setBmi");
        float bmi = 21F;
        Vitals instance = new Vitals();
        instance.setBmi(bmi);
    }

    /**
     * Test of getNotes method, of class Vitals.
     */
    @Test
    public void testGetNotes() {
        System.out.println("getNotes");
        String expResult = "none";
        String result = instance.getNotes();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNotes method, of class Vitals.
     */
    @Test
    public void testSetNotes() {
        System.out.println("setNotes");
        String notes = "none";
        Vitals instance = new Vitals();
        instance.setNotes(notes);
    }

    /**
     * Test of getPatientId method, of class Vitals.
     */
    @Test
    public void testGetPatientId() {
        System.out.println("getPatientId");
        int expResult = 1;
        int result = instance.getPatientId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPatientId method, of class Vitals.
     */
    @Test
    public void testSetPatientId() {
        System.out.println("setPatientId");
        int patientId =1;
        Vitals instance = new Vitals();
        instance.setPatientId(patientId);
    }

    
    
}
