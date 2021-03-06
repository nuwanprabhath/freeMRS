package freemrs;



/**
 * GeneralMedicalInfo to map database
 */
public class GeneralMedicalInfo  implements java.io.Serializable {


     private int patientId;
     private String mainMedicalProblem;
     private String medicalProblems;
     private String allergies;
     private String immunizations;

    public GeneralMedicalInfo() {
    }

    public GeneralMedicalInfo(int patientId, String mainMedicalProblem, String medicalProblems, String allergies, String immunizations) {
       this.patientId = patientId;
       this.mainMedicalProblem = mainMedicalProblem;
       this.medicalProblems = medicalProblems;
       this.allergies = allergies;
       this.immunizations = immunizations;
    }
   
    public int getPatientId() {
        return this.patientId;
    }
    
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    public String getMainMedicalProblem() {
        return this.mainMedicalProblem;
    }
    
    public void setMainMedicalProblem(String mainMedicalProblem) {
        this.mainMedicalProblem = mainMedicalProblem;
    }
    public String getMedicalProblems() {
        return this.medicalProblems;
    }
    
    public void setMedicalProblems(String medicalProblems) {
        this.medicalProblems = medicalProblems;
    }
    public String getAllergies() {
        return this.allergies;
    }
    
    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }
    public String getImmunizations() {
        return this.immunizations;
    }
    
    public void setImmunizations(String immunizations) {
        this.immunizations = immunizations;
    }




}


