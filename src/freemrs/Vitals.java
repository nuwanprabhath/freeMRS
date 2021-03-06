package freemrs;


import java.util.Date;

/**
 * Vitals to map dataase 
*/
public class Vitals  implements java.io.Serializable {


     private Integer vitalId;
     private Date dateTime;
     private float weight;
     private float height;
     private int bpSystolic;
     private int bpDiastolic;
     private int pulse;
     private int temperature;
     private String tempLocation;
     private int oxygenSaturation;
     private float bmi;
     private String notes;
     private int patientId;

    public Vitals() {
    }

    public Vitals(Date dateTime, float weight, float height, int bpSystolic, int bpDiastolic, int pulse, int temperature, String tempLocation, int oxygenSaturation, float bmi, String notes, int patientId) {
       this.dateTime = dateTime;
       this.weight = weight;
       this.height = height;
       this.bpSystolic = bpSystolic;
       this.bpDiastolic = bpDiastolic;
       this.pulse = pulse;
       this.temperature = temperature;
       this.tempLocation = tempLocation;
       this.oxygenSaturation = oxygenSaturation;
       this.bmi = bmi;
       this.notes = notes;
       this.patientId = patientId;
    }
   
    public Integer getVitalId() {
        return this.vitalId;
    }
    
    public void setVitalId(Integer vitalId) {
        this.vitalId = vitalId;
    }
    public Date getDateTime() {
        return this.dateTime;
    }
    
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    public float getWeight() {
        return this.weight;
    }
    
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public float getHeight() {
        return this.height;
    }
    
    public void setHeight(float height) {
        this.height = height;
    }
    public int getBpSystolic() {
        return this.bpSystolic;
    }
    
    public void setBpSystolic(int bpSystolic) {
        this.bpSystolic = bpSystolic;
    }
    public int getBpDiastolic() {
        return this.bpDiastolic;
    }
    
    public void setBpDiastolic(int bpDiastolic) {
        this.bpDiastolic = bpDiastolic;
    }
    public int getPulse() {
        return this.pulse;
    }
    
    public void setPulse(int pulse) {
        this.pulse = pulse;
    }
    public int getTemperature() {
        return this.temperature;
    }
    
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
    public String getTempLocation() {
        return this.tempLocation;
    }
    
    public void setTempLocation(String tempLocation) {
        this.tempLocation = tempLocation;
    }
    public int getOxygenSaturation() {
        return this.oxygenSaturation;
    }
    
    public void setOxygenSaturation(int oxygenSaturation) {
        this.oxygenSaturation = oxygenSaturation;
    }
    public float getBmi() {
        return this.bmi;
    }
    
    public void setBmi(float bmi) {
        this.bmi = bmi;
    }
    public String getNotes() {
        return this.notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public int getPatientId() {
        return this.patientId;
    }
    
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

     @Override
   public String toString(){
        return dateTime.toString();
    }


}


