/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package freemrs;

import java.util.*;

/**
 *
 * @author Nuwan Prabhath
 */
public class PatientInfoExport implements java.io.Serializable{  //use to serialize data of a patient
    
    private Patient patient;
    private Insurance insuarenceInfo;
    private GeneralMedicalInfo generalMedicalInfo;
    private List<Prescription> pres;
    private List<Vitals> vitals;

    public PatientInfoExport(Patient patient, Insurance insuarenceInfo, GeneralMedicalInfo generalInfo, List<Prescription> pres, List<Vitals> vitals) {
        this.patient = patient;
        this.insuarenceInfo = insuarenceInfo;
        this.generalMedicalInfo = generalInfo;
        this.pres = pres;
        this.vitals = vitals;
    }

    public Patient getPatient() {
        return patient;
    }

    public Insurance getInsuarenceInfo() {
        return insuarenceInfo;
    }

    public GeneralMedicalInfo getGeneralMedicalInfo() {
        return generalMedicalInfo;
    }

    public List<Prescription> getPrescription() {
        return pres;
    }

    public List<Vitals> getVitals() {
        return vitals;
    }
    
    
    
    
    
}
