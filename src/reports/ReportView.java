/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;
import com.mysql.jdbc.Connection;
import java.awt.Container;
import java.awt.Toolkit;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Nuwan Prabhath
 */
public class ReportView extends JFrame {

    public ReportView(String fileName) {
        this(fileName, null);
    }

    public ReportView(String fileName, HashMap para) {
        super("FreeMRS Report Viewer");
        Connection con = getConnection();

        try {
            JasperPrint print = JasperFillManager.fillReport(fileName, para, con);
            JRViewer viewer = new JRViewer(print);
            Container c = getContentPane();
            c.add(viewer);
        } catch (JRException jRException) {
        }
        setBounds(10, 10, 900, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setState(3);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }
    
    

    public Connection getConnection() {
        try {
            
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/freemrs", "root", "");
            return (Connection) con;
        } catch (SQLException sQLException) {
            return null;

        }
    }
}
