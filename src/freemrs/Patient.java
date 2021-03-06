package freemrs;


import java.util.Date;

/**
 * Patient to map database
 */
public class Patient  implements java.io.Serializable {


     private Integer patientId;
     private String name;
     private Date birthday;
     private String sex;
     private String address;
     private String maritalStatus;
     private String job;
     private String phone;

    public Patient() {
    }

    public Patient(String name, Date birthday, String sex, String address, String maritalStatus, String job, String phone) {
       this.name = name;
       this.birthday = birthday;
       this.sex = sex;
       this.address = address;
       this.maritalStatus = maritalStatus;
       this.job = job;
       this.phone = phone;
    }
   
    public Integer getPatientId() {
        return this.patientId;
    }
    
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getSex() {
        return this.sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public String getMaritalStatus() {
        return this.maritalStatus;
    }
    
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    public String getJob() {
        return this.job;
    }
    
    public void setJob(String job) {
        this.job = job;
    }
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

     @Override
    public String toString(){
        return this.name;
    }


}


