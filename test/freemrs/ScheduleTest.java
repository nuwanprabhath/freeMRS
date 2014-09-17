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
public class ScheduleTest {
    Schedule instance;
    Date date;
    public ScheduleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {//int patientId, Date date, int startTime, int endTime, String location
        date = new Date();
        instance = new Schedule(1,date,2,3,"colombo");
        instance.setScheduleId(4);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getScheduleId method, of class Schedule.
     */
    @Test
    public void testGetScheduleId() {
        System.out.println("getScheduleId");
        Integer expResult = 4;
        Integer result = instance.getScheduleId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setScheduleId method, of class Schedule.
     */
    @Test
    public void testSetScheduleId() {
        System.out.println("setScheduleId");
        Integer scheduleId = 4;
        Schedule instance = new Schedule();
        instance.setScheduleId(scheduleId);
    }

    /**
     * Test of getPatientId method, of class Schedule.
     */
    @Test
    public void testGetPatientId() {
        System.out.println("getPatientId");
        int expResult = 1;
        int result = instance.getPatientId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPatientId method, of class Schedule.
     */
    @Test
    public void testSetPatientId() {
        System.out.println("setPatientId");
        int patientId = 1;
        Schedule instance = new Schedule();
        instance.setPatientId(patientId);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getDate method, of class Schedule.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        Date expResult = date;
        Date result = instance.getDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setDate method, of class Schedule.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        Date date = this.date;
        Schedule instance = new Schedule();
        instance.setDate(date);
    }

    /**
     * Test of getStartTime method, of class Schedule.
     */
    @Test
    public void testGetStartTime() {
        System.out.println("getStartTime");
        int expResult = 2;
        int result = instance.getStartTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setStartTime method, of class Schedule.
     */
    @Test
    public void testSetStartTime() {
        System.out.println("setStartTime");
        int startTime = 2;
        Schedule instance = new Schedule();
        instance.setStartTime(startTime);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getEndTime method, of class Schedule.
     */
    @Test
    public void testGetEndTime() {
        System.out.println("getEndTime");
        int expResult = 3;
        int result = instance.getEndTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEndTime method, of class Schedule.
     */
    @Test
    public void testSetEndTime() {
        System.out.println("setEndTime");
        int endTime = 0;
        Schedule instance = new Schedule();
        instance.setEndTime(endTime);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getLocation method, of class Schedule.
     */
    @Test
    public void testGetLocation() {
        System.out.println("getLocation");
        String expResult = "colombo";
        String result = instance.getLocation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setLocation method, of class Schedule.
     */
    @Test
    public void testSetLocation() {
        System.out.println("setLocation");
        String location = "colombo";
        Schedule instance = new Schedule();
        instance.setLocation(location);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of toString method, of class Schedule.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Schedule instance = new Schedule();
        String expResult = null;
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
