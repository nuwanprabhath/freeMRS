package freemrs;
// Generated Jul 27, 2014 6:07:15 PM by Hibernate Tools 3.6.0


import java.util.Date;

/**
 * Prescription generated by hbm2java
 */
public class Prescription  implements java.io.Serializable {


     private Integer prescriptionId;
     private int patientId;
     private Date dateTime;
     private String notes;

    public Prescription() {
    }

    public Prescription(int patientId, Date dateTime, String notes) {
       this.patientId = patientId;
       this.dateTime = dateTime;
       this.notes = notes;
    }
   
    public Integer getPrescriptionId() {
        return this.prescriptionId;
    }
    
    public void setPrescriptionId(Integer prescriptionId) {
        this.prescriptionId = prescriptionId;
    }
    public int getPatientId() {
        return this.patientId;
    }
    
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    public Date getDateTime() {
        return this.dateTime;
    }
    
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    public String getNotes() {
        return this.notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }

     @Override
     public String toString(){
    return dateTime.toString();
}


}


