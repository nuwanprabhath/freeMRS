package freemrs;
// Generated Jul 27, 2014 6:07:15 PM by Hibernate Tools 3.6.0



/**
 * Userinfo generated by hbm2java
 */
public class Userinfo  implements java.io.Serializable {


     private String username;
     private String type;
     private String passwordhash;
     private String question;
     private String answer;

    public Userinfo() {
    }

    public Userinfo(String username, String type, String passwordhash, String question, String answer) {
       this.username = username;
       this.type = type;
       this.passwordhash = passwordhash;
       this.question = question;
       this.answer = answer;
    }
   
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public String getPasswordhash() {
        return this.passwordhash;
    }
    
    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }
    public String getQuestion() {
        return this.question;
    }
    
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getAnswer() {
        return this.answer;
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }

     @Override
    public String toString(){
        return this.username;
    }


}


