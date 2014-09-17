package freemrs;


import java.util.Date;

/**
 * Schedule to map database 
*/
public class Schedule  implements java.io.Serializable {


     private Integer scheduleId;
     private int patientId;
     private Date date;
     private int startTime;
     private int endTime;
     private String location;

    public Schedule() {
    }

    public Schedule(int patientId, Date date, int startTime, int endTime, String location) {
       this.patientId = patientId;
       this.date = date;
       this.startTime = startTime;
       this.endTime = endTime;
       this.location = location;
    }
   
    public Integer getScheduleId() {
        return this.scheduleId;
    }
    
    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }
    public int getPatientId() {
        return this.patientId;
    }
    
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    public int getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }
    public int getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }

    public String toString(){
        return this.location;
    }
    


}


