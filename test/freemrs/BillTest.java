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
public class BillTest {
    
    Bill instance;
    Date date;
    public BillTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before //(int patientId, String type, float cost, Date date)
    public void setUp() {
        date = new Date();
        instance = new Bill(1,"drugs",100F,date);
        instance.setBillId(1);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getBillId method, of class Bill.
     */
    @Test
    public void testGetBillId() {
        System.out.println("getBillId");
        Integer expResult = 1;
        Integer result = instance.getBillId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setBillId method, of class Bill.
     */
    @Test
    public void testSetBillId() {
        System.out.println("setBillId");
        Integer billId = 1;
        instance = new Bill();
        instance.setBillId(billId);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getPatientId method, of class Bill.
     */
    @Test
    public void testGetPatientId() {
        System.out.println("getPatientId");
        int expResult = 1;
        int result = instance.getPatientId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setPatientId method, of class Bill.
     */
    @Test
    public void testSetPatientId() {
        System.out.println("setPatientId");
        int patientId = 1;
        instance = new Bill();
        instance.setPatientId(patientId);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getType method, of class Bill.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        String expResult = "drugs";
        String result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setType method, of class Bill.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String type = "";
        Bill instance = new Bill();
        instance.setType(type);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getCost method, of class Bill.
     */
    @Test
    public void testGetCost() {
        System.out.println("getCost");
        float expResult = 100F;
        float result = instance.getCost();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setCost method, of class Bill.
     */
    @Test
    public void testSetCost() {
        System.out.println("setCost");
        float cost = 0.0F;
        Bill instance = new Bill();
        instance.setCost(cost);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getDate method, of class Bill.
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
     * Test of setDate method, of class Bill.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        Date date = null;
        Bill instance = new Bill();
        instance.setDate(date);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
