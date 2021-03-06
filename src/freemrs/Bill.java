package freemrs;


import java.util.Date;

/**
 * Bill to map database table
 */
public class Bill  implements java.io.Serializable {


     private Integer billId;
     private int patientId;
     private String type;
     private float cost;
     private Date date;

    public Bill() {
    }

    public Bill(int patientId, String type, float cost, Date date) {
       this.patientId = patientId;
       this.type = type;
       this.cost = cost;
       this.date = date;
    }
   
    public Integer getBillId() {
        return this.billId;
    }
    
    public void setBillId(Integer billId) {
        this.billId = billId;
    }
    public int getPatientId() {
        return this.patientId;
    }
    
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public float getCost() {
        return this.cost;
    }
    
    public void setCost(float cost) {
        this.cost = cost;
    }
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }




}


