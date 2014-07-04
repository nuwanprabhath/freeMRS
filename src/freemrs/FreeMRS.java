/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package freemrs;

import java.security.MessageDigest;
import java.util.Arrays;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Nuwan Prabhath
 */
public class FreeMRS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Userinfo usr = new Userinfo("nuwan", "doctor", getHash("nuwan"), "What is first friend last name?", getHash("nuwan"));
        session.save(usr);
        session.getTransaction().commit();
        session.close();


    }
    public static String getHash(String password) throws Exception {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] passBytes = password.getBytes();
        byte[] passHash = sha256.digest(passBytes);
        String hash = Arrays.toString(passHash);
        return hash;
    }
}
