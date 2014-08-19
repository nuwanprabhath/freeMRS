/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package freemrs;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 *
 * @author Nuwan Prabhath
 */
public class UserPasswordMatch {
    
    public static String getHash(String password) throws Exception {
        MessageDigest sha256=MessageDigest.getInstance("SHA-256");
        byte[] passBytes=password.getBytes();
        byte[] passHash=sha256.digest(passBytes);
        String hash=Arrays.toString(passHash);
        return hash;
    }
    
     public static boolean compareData(Userinfo user,String password) throws Exception// returns true if username and password is a match
    {
        if(user.getPasswordhash().equals(getHash(password)))
            return true;
        else
            return false;
    }
    
     public static boolean changePassword(Userinfo user,String curPassword,String newPassword) throws Exception
    {
        if(compareData(user,curPassword))
        {
            user.setPasswordhash(getHash(newPassword));
            return true;
        }
        else
            return false;
    }
     
     public static boolean compareQuestion(Userinfo user,String answer) throws Exception// returns true if username and password is a match
    {
        if(user.getAnswer().equals(getHash(answer)))
            return true;
        else
            return false;
    }
}
