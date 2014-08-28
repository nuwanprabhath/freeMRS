/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package freemrs;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;

/**
 *
 * @author Nuwan Prabhath
 */
public class PatientInfoExportTest {
    
    private Patient patient;
    private Insurance insuarenceInfo;
    private GeneralMedicalInfo generalMedicalInfo;
    private List<Prescription> pres = new ArrayList();
    private List<Vitals> vitals = new ArrayList();;
    
    private PatientInfoExport instance ;
    
    public PatientInfoExportTest() {

    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
     //Patient(String name, Date birthday, String sex, String address, String maritalStatus, String job, String phone)
     patient = new Patient("nuwan",new Date(),"male","8-D,Boraliyawatha,Bemmulla.","married","student","0715181576");
     insuarenceInfo = new Insurance("celinco", "life", new Date(), "123",1) ;  
     generalMedicalInfo =  new GeneralMedicalInfo(1,"blood pressure","blood pressure","none","none"); 
     //Prescription(int patientId, Date dateTime, String notes)
     pres.add(new Prescription(1,new Date(), "note"));
     vitals.add(new Vitals(new Date(), 60, 1.4f, 70, 60, 70, 35, "oral", 99, 22.5f, "none", 1));
     instance = new PatientInfoExport( patient,  insuarenceInfo,  generalMedicalInfo,  pres,  vitals);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPatient method, of class PatientInfoExport.
     */
    @Test
    public void testGetPatient() {
        System.out.println("getPatient");
       
        Patient expResult = this.patient;
        Patient result = instance.getPatient();
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of getInsuarenceInfo method, of class PatientInfoExport.
     */
    @Test
    public void testGetInsuarenceInfo() {
        System.out.println("getInsuarenceInfo");
        Insurance expResult = this.insuarenceInfo;
        Insurance result = instance.getInsuarenceInfo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGeneralMedicalInfo method, of class PatientInfoExport.
     */
    @Test
    public void testGetGeneralMedicalInfo() {
        System.out.println("getGeneralMedicalInfo");
        GeneralMedicalInfo expResult = this.generalMedicalInfo;
        GeneralMedicalInfo result = instance.getGeneralMedicalInfo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrescription method, of class PatientInfoExport.
     */
    @Test
    public void testGetPrescription() {
        System.out.println("getPrescription");
        List<Prescription> expResult = this.pres;
        List<Prescription> result = instance.getPrescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVitals method, of class PatientInfoExport.
     */
    @Test
    public void testGetVitals() {
        System.out.println("getVitals");
        List<Vitals> expResult = this.vitals;
        List<Vitals> result = instance.getVitals();
        assertEquals(expResult, result);
    }
    
}
