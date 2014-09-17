/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freemrs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jdesktop.swingx.JXDatePicker;
import reports.ReportView;
import java.awt.Desktop;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 *
 * @author Nuwan Prabhath
 */
public class MoreInterface extends javax.swing.JFrame {

    /**
     * Creates new form MoreInterface
     */
    JXDatePicker picker1, picker2, picker3, picker4;     //To get the calander
    private Session session;
    private Patient currentPatient;
    private Userinfo currentUser;                               //To test who logged in    
    SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    mainWindow main = null;                                     //To update after delete patient
    
    public MoreInterface(Session session, Patient patient, Userinfo user,mainWindow main) {
        initComponents();
        this.session = session;
        currentPatient = patient;
        this.currentUser = user;
        this.main = main;
        if (user.getType().equals("nurse")) {       //Removing reports and other things which are not allowed to nurse

            jTabbedPane1.removeTabAt(0);
            jTabbedPane1.removeTabAt(0);
            jTabbedPane1.removeTabAt(1);
            jTabbedPane1.removeTabAt(1);
        }

        picker1 = new JXDatePicker();            //Setting calender
        picker1.setDate(Calendar.getInstance().getTime());
        picker1.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
        jPanel1.add(picker1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, -1, -1));

        picker2 = new JXDatePicker();            //Setting calender
        picker2.setDate(Calendar.getInstance().getTime());
        picker2.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
        jPanel1.add(picker2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, -1, -1));

        picker3 = new JXDatePicker();            //Setting calender
        picker3.setDate(Calendar.getInstance().getTime());
        picker3.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
        jPanel1.add(picker3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, -1, -1));

        picker4 = new JXDatePicker();            //Setting calender
        picker4.setDate(Calendar.getInstance().getTime());
        picker4.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
        jPanel1.add(picker4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, -1, -1));

        desableElements();

    }

    //Updating combo box
    private void updateCombo() {
        session.beginTransaction();
        Query qr = session.createQuery("from Userinfo where username !=:code");
        qr.setParameter("code", currentUser.getUsername());

        List<Userinfo> result = qr.list();
        session.getTransaction().commit();

        jComboBox1.removeAllItems();
        for (Userinfo u : result) {
            jComboBox1.addItem(u);
        }

    }

    private void desableElements() {
        jPasswordField2.setEnabled(false);  //Desabeling password reset and security question until authentication complete
        jPasswordField3.setEnabled(false);
        jButton7.setEnabled(false);
        jTextField2.setEnabled(false);
        jTextField3.setEnabled(false);
        jButton8.setEnabled(false);
        jPasswordField4.setEnabled(false);
        jTextField4.setEnabled(false);
        jTextField5.setEnabled(false);
        jTextField6.setEnabled(false);
        jButton11.setEnabled(false);
        jButton9.setEnabled(false);
        jComboBox1.setEnabled(false);
        jButton13.setEnabled(false);
    }

    private Insurance getInsuarenceInfo() {
        session.beginTransaction();
        Query qr = session.createQuery("from Insurance where patientId =:code");
        qr.setParameter("code", currentPatient.getPatientId());
        List<Insurance> result = qr.list();
        session.getTransaction().commit();
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }

    }

    private GeneralMedicalInfo getMedicalInfo() {   //get medical info
        session.beginTransaction();
        Query qr = session.createQuery("from GeneralMedicalInfo where patientId =:code");
        qr.setParameter("code", currentPatient.getPatientId());
        List<GeneralMedicalInfo> result = qr.list();
        session.getTransaction().commit();

        if (!result.isEmpty()) {
            return result.get(0);
        } else {
            return null;
        }
    }

    private List<Prescription> getPrescription() {      //give prescription information

        session.beginTransaction();
        Query qr = session.createQuery("from Prescription where patientId =:code order by dateTime DESC");
        qr.setParameter("code", currentPatient.getPatientId());
        List<Prescription> result = qr.list();
        session.getTransaction().commit();

        if (!result.isEmpty()) {
            return result;
        } else {
            return null;
        }

    }

    private List<Vitals> getVitals() {    //get vitals
        session.beginTransaction();
        Query qr = session.createQuery("from Vitals where patientId =:code order by dateTime DESC");
        qr.setParameter("code", currentPatient.getPatientId());
        List<Vitals> result = qr.list();
        session.getTransaction().commit();

        if (!result.isEmpty()) {
            return result;
        } else {
            return null;
        }
    }

    private void serializeFile(String path) {       //Use to serialize the file of patient

        if (main.currentPatient != null) {
            PatientInfoExport e = new PatientInfoExport(currentPatient, getInsuarenceInfo(), getMedicalInfo(), getPrescription(), getVitals());

            try {
                FileOutputStream fileOut = new FileOutputStream(path + ".ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(e);
                out.close();
                fileOut.close();
                JOptionPane.showMessageDialog(null, "Sucessfully saved patient " + currentPatient.getName(), "Export", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException i) {
                JOptionPane.showMessageDialog(null, i.getMessage(), "Export", JOptionPane.ERROR_MESSAGE);
                i.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please search patient", "Patient Info Export", JOptionPane.ERROR_MESSAGE);
        }

    }

    private int getNewPatientID(Patient p) {      //Used to save new patient file with this id

        session.beginTransaction();
        Query qr = session.createQuery("from Patient where name =:code1 and address =:code2 and phone =:code3");
        qr.setParameter("code1", p.getName());
        qr.setParameter("code2", p.getAddress());
        qr.setParameter("code3", p.getPhone());

        List<Patient> result = qr.list();
        session.getTransaction().commit();
        if (result.size() > 1) {
            JOptionPane.showMessageDialog(null, "You already have this patient", "Export", JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            return result.get(0).getPatientId();
        }

    }

    private boolean checkAvailability(Patient p) { //To check importing already have patient
        session.beginTransaction();
        Query qr = session.createQuery("from Patient where name =:code1 and address =:code2 and phone =:code3");
        qr.setParameter("code1", p.getName());
        qr.setParameter("code2", p.getAddress());
        qr.setParameter("code3", p.getPhone());
        List<Patient> result = qr.list();

        if (result.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    private void saveFileInfo(String path) {        //To save file into database

        PatientInfoExport e1 = null;
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e1 = (PatientInfoExport) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println("Error!!" + i.getMessage());
            JOptionPane.showMessageDialog(null, i.getMessage(), "Export", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found!!" + c.getMessage());
            JOptionPane.showMessageDialog(null, c.getMessage(), "Export", JOptionPane.ERROR_MESSAGE);
        }

        Patient p = e1.getPatient();

        if (checkAvailability(p)) {

            session.beginTransaction();
            session.save(p);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Successfully imported data", "Import", JOptionPane.INFORMATION_MESSAGE);
            int id = this.getNewPatientID(p);        //Getting new patient id

            Insurance i = e1.getInsuarenceInfo();
            if (i != null) {
                i.setPatientId(id);
                session.beginTransaction();
                session.save(i);
                session.getTransaction().commit();
            }

            GeneralMedicalInfo m = e1.getGeneralMedicalInfo();
            if (m != null) {
                m.setPatientId(id);
                session.beginTransaction();
                session.save(m);
                session.getTransaction().commit();
            }

            List<Prescription> pres = e1.getPrescription();
            if (pres != null) {
                session.beginTransaction();
                for (Prescription pr : pres) {
                    pr.setPatientId(id);
                    session.save(pr);
                }
                session.getTransaction().commit();
            }

            List<Vitals> vitals = e1.getVitals();
            if (vitals != null) {
                session.beginTransaction();
                for (Vitals vi : vitals) {
                    vi.setPatientId(id);
                    session.save(vi);
                }
                session.getTransaction().commit();
            }

        } else {
            JOptionPane.showMessageDialog(null, "You already have this patient", "Export", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        fileChooser1 = new javax.swing.JFileChooser();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton6 = new javax.swing.JButton();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPasswordField3 = new javax.swing.JPasswordField();
        jButton7 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jPasswordField6 = new javax.swing.JPasswordField();
        jButton12 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPasswordField4 = new javax.swing.JPasswordField();
        jLabel30 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel33 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton9 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jPasswordField5 = new javax.swing.JPasswordField();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();

        fileChooser.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);

        setTitle("More");
        setIconImage(new ImageIcon(getClass().getResource("/images/icon_transparent.png")).getImage());
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Financial report");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 420, 10));

        jLabel2.setText("Start date:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel3.setText("End date:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        jButton1.setBackground(new java.awt.Color(153, 204, 255));
        jButton1.setText("Generate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, -1, -1));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 450, 10));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Patient report");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        jLabel5.setText("Start date:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        jLabel6.setText("End date:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 172, 390, 0));

        jButton2.setBackground(new java.awt.Color(153, 204, 255));
        jButton2.setText("Generate");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 280, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel11.setText("Reports");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report small.jpg"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 60, 70));

        jTabbedPane1.addTab("Reports", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Export-&-Import small.jpg"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setText("Export & Import data");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Export");

        jLabel12.setText("Select location to save locally:");

        jButton3.setBackground(new java.awt.Color(153, 204, 255));
        jButton3.setText("Select location");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(153, 204, 255));
        jButton4.setText("Mail file");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel13.setText("Email exported file:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Import");

        jLabel15.setText("Open file location to import:");

        jButton5.setBackground(new java.awt.Color(153, 204, 255));
        jButton5.setText("Open file");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3))
                                .addGap(59, 59, 59))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator4)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel9))
                                    .addComponent(jLabel10))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel9)))
                .addGap(26, 26, 26)
                .addComponent(jLabel10)
                .addGap(3, 3, 3)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(20, 20, 20)
                        .addComponent(jButton4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jButton5)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Export and Import", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Setting-icon small.png"))); // NOI18N
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 10, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel17.setText("Settings");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 31, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Reset password and security question");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 76, -1, -1));
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 104, 420, 10));

        jLabel19.setText("  Enter current password:");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, 10));

        jLabel20.setText("Enter new password:");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 190, -1, -1));

        jLabel21.setText("Conferm new password:");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 226, -1, -1));
        jPanel3.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 127, -1));

        jButton6.setBackground(new java.awt.Color(153, 255, 153));
        jButton6.setText("Authorize");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 120, -1, -1));

        jPasswordField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField2ActionPerformed(evt);
            }
        });
        jPanel3.add(jPasswordField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 219, 127, -1));
        jPanel3.add(jPasswordField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 186, 127, -1));

        jButton7.setBackground(new java.awt.Color(153, 255, 153));
        jButton7.setText("Change");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 185, 79, -1));

        jLabel22.setText("Enter your user name:");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 124, -1, -1));
        jPanel3.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 121, 127, -1));

        jLabel23.setText("Enter security question:");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 267, -1, -1));

        jLabel24.setText("Enter answer:");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 300, -1, -1));
        jPanel3.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 264, 127, -1));
        jPanel3.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 297, 127, -1));

        jButton8.setBackground(new java.awt.Color(153, 255, 153));
        jButton8.setText("Update");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 263, 79, -1));

        jTabbedPane1.addTab("Settings", jPanel3);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete smal.jpg"))); // NOI18N

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel37.setText("Remove patient information");

        jLabel38.setText("Completely remove current patient from database");

        jLabel39.setText("Your user name:");

        jLabel40.setText("Password:");

        jButton12.setBackground(new java.awt.Color(153, 255, 153));
        jButton12.setText("Authorize");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setText("Remove patient");

        jLabel42.setText("Remove currently selected patient:");

        jLabel43.setText("Name:");

        jButton13.setBackground(new java.awt.Color(255, 0, 0));
        jButton13.setText("Delete Patient");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel43)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel42))
                        .addGap(110, 110, 110)
                        .addComponent(jButton13))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPasswordField6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton12))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel37)
                                    .addComponent(jLabel38)))
                            .addComponent(jLabel41)
                            .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(jPasswordField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12))
                .addGap(37, 37, 37)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton13))
                .addContainerGap(129, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Remove", jPanel5);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/User-small.jpg"))); // NOI18N

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel26.setText("Add or remove users");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("Add user");

        jLabel28.setText("User name:");

        jLabel29.setText("Password:");

        jLabel30.setText("Security question:");

        jLabel31.setText("Answer:");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setText("Remove user");

        jLabel33.setText("Select user:");

        jButton9.setBackground(new java.awt.Color(255, 153, 153));
        jButton9.setText("Remove");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel34.setText("Your user name:");

        jLabel35.setText("Password:");

        jButton10.setBackground(new java.awt.Color(153, 255, 153));
        jButton10.setText("Authorize");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(153, 255, 153));
        jButton11.setText("Add");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel25)
                .addGap(28, 28, 28)
                .addComponent(jLabel26)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator7)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel34)
                                        .addGap(26, 26, 26)
                                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel35)
                                        .addGap(18, 18, 18)
                                        .addComponent(jPasswordField5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                        .addComponent(jButton10))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel31))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField5)
                                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel29)
                                            .addComponent(jLabel28))
                                        .addGap(50, 50, 50)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                            .addComponent(jPasswordField4))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(23, 23, 23))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel32)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGap(23, 23, 23))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel33)
                        .addGap(47, 47, 47)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9)
                        .addGap(28, 28, 28))
                    .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel26)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(jPasswordField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10))
                .addGap(5, 5, 5)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel29))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPasswordField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Users", jPanel4);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 400));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            String fileName = "src\\reports\\patientReport.jasper";

            java.util.Date invoiceDate = formatDate.parse(picker3.getEditor().getText());
            java.sql.Date dateStart = new java.sql.Date(invoiceDate.getTime());

            java.util.Date invoiceDate1 = formatDate.parse(picker4.getEditor().getText());
            java.sql.Date dateEnd = new java.sql.Date(invoiceDate1.getTime());

            if (dateEnd.after(dateStart)) {               //Checking youser has input a valid time period

                HashMap para = new HashMap();
                para.put("start", "'" + picker3.getEditor().getText() + "'");
                para.put("end", "'" + picker4.getEditor().getText() + "'");
                ReportView rw = new ReportView(fileName, para);
                rw.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, "End date should after start date", "More", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Please use valid date", "More", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String fileName = "src\\reports\\financialReport.jasper";
        try {
            java.util.Date invoiceDate = formatDate.parse(picker1.getEditor().getText());
            java.sql.Date dateStart = new java.sql.Date(invoiceDate.getTime());

            java.util.Date invoiceDate1 = formatDate.parse(picker2.getEditor().getText());
            java.sql.Date dateEnd = new java.sql.Date(invoiceDate1.getTime());

            if (dateEnd.after(dateStart)) {               //Checking youser has input a valid time period

                HashMap para = new HashMap();
                para.put("start", "'" + picker1.getEditor().getText() + "'");
                para.put("end", "'" + picker2.getEditor().getText() + "'");
                ReportView rw = new ReportView(fileName, para);
                rw.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "End date should after start date", "More", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Please use valid date", "More", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int returnVal = fileChooser.showSaveDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();  //Get file path to save data

            this.serializeFile(file.getAbsolutePath()); //Serializing file

        } else {
            System.out.println("File access cancelled by user.");
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            Desktop.getDesktop().mail();
        } catch (IOException ex) {
            Logger.getLogger(MoreInterface.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        int returnVal = fileChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();  //Get file path to save data
            this.saveFileInfo(file.getAbsolutePath());

        } else {
            System.out.println("File access cancelled by user.");
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        String username = jTextField1.getText();
        String password = new String(jPasswordField1.getPassword());

        session.beginTransaction();
        Query qr = session.createQuery("from Userinfo where username=:code");
        qr.setParameter("code", username);

        List<Userinfo> lst = qr.list();
        session.getTransaction().commit();

        Userinfo user = null;
        if (!lst.isEmpty()) {
            user = lst.get(0);
        }

        boolean access = false; //TO check acess is granted or not
        if (user == null) {
            jTextField1.setText("");
            jPasswordField1.setText("");
            JOptionPane.showMessageDialog(this, "User name or password incorrect", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                access = UserPasswordMatch.compareData(user, password); //Compare the password and user name with given data
                if (!username.equals(currentUser.getUsername())) {        //check user trying to reset his password
                    access = false;
                    JOptionPane.showMessageDialog(this, "User name or password incorrect", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }

        if (access) {
            jPasswordField2.setEnabled(true);
            jPasswordField3.setEnabled(true);
            jButton7.setEnabled(true);
            jTextField2.setEnabled(true);
            jTextField3.setEnabled(true);
            jButton8.setEnabled(true);

        } else {
            if (user != null) {
                JOptionPane.showMessageDialog(this, "User name or password incorrect", "Login error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jPasswordField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String pw1 = new String(jPasswordField2.getPassword());
        String pw2 = new String(jPasswordField3.getPassword());

        if (pw1.equals(pw2)) {
            session.beginTransaction();
            Query qr = session.createQuery("from Userinfo where username=:code");
            qr.setParameter("code", jTextField1.getText());
            Userinfo user = (Userinfo) qr.list().get(0);
            try {
                user.setPasswordhash(UserPasswordMatch.getHash(pw2));
                session.update(user);
                session.getTransaction().commit();
                JOptionPane.showMessageDialog(this, "Password changed successfully", "Password change", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                Logger.getLogger(MoreInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        session.beginTransaction();
        Query qr = session.createQuery("from Userinfo where username=:code");
        qr.setParameter("code", jTextField1.getText());
        Userinfo user = (Userinfo) qr.list().get(0);

        String question = jTextField2.getText();
        String answer = jTextField3.getText();

        if (question.isEmpty() == false || answer.isEmpty() == false) {
            user.setQuestion(question);
            try {
                user.setAnswer(UserPasswordMatch.getHash(answer));
                session.update(user);
                session.getTransaction().commit();
                JOptionPane.showMessageDialog(this, "Security question changed successfully", "Security question change", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                Logger.getLogger(MoreInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            JOptionPane.showMessageDialog(this, "Enter data correctly", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        String username = jTextField7.getText();
        String password = new String(jPasswordField5.getPassword());

        session.beginTransaction();
        Query qr = session.createQuery("from Userinfo where username=:code");
        qr.setParameter("code", username);

        List<Userinfo> lst = qr.list();
        session.getTransaction().commit();

        Userinfo user = null;
        if (!lst.isEmpty()) {
            user = lst.get(0);
        }

        boolean access = false; //TO check acess is granted or not
        if (user == null) {
            jTextField7.setText("");    //Resetting password field and username
            jPasswordField5.setText("");
            JOptionPane.showMessageDialog(this, "User name or password incorrect", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                access = UserPasswordMatch.compareData(user, password); //Compare the password and user name with given data
                if (!username.equals(currentUser.getUsername())) {        //check user trying to reset his password
                    access = false;
                    JOptionPane.showMessageDialog(this, "User name or password incorrect", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }

        if (access) {
            jPasswordField4.setEnabled(true);
            jTextField4.setEnabled(true);
            jTextField5.setEnabled(true);
            jTextField6.setEnabled(true);
            jButton11.setEnabled(true);
            jButton9.setEnabled(true);
            jComboBox1.setEnabled(true);

            jTextField7.setText("");
            jPasswordField5.setText("");
            updateCombo();                  //Updating the combo box to show all users

        } else {
            if (user != null) {
                JOptionPane.showMessageDialog(this, "User name or password incorrect", "Login error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        String username = jTextField4.getText();
        String password = new String(jPasswordField4.getPassword());
        String question = jTextField5.getText();
        String answer = jTextField6.getText();

        if (username.isEmpty() || password.isEmpty() || question.isEmpty() || answer.isEmpty()) { //Check there are any empty fields
            JOptionPane.showMessageDialog(this, "Complete all fields", "Empty fields", JOptionPane.ERROR_MESSAGE);
        } else {

            session.beginTransaction();
            Query qr = session.createQuery("from Userinfo where username=:code");
            qr.setParameter("code", username);

            List<Userinfo> lst = qr.list();
            session.getTransaction().commit();

            if (lst.isEmpty()) {

                Userinfo current = new Userinfo();
                current.setUsername(username);
                current.setQuestion(question);
                current.setType("nurse");
                try {
                    current.setPasswordhash(UserPasswordMatch.getHash(password));
                    current.setAnswer(UserPasswordMatch.getHash(answer));
                } catch (Exception ex) {
                    System.out.println("Error password conversion");
                }

                session.beginTransaction();
                session.save(current);          //Saving object
                session.getTransaction().commit();
                JOptionPane.showMessageDialog(this, "User added sucessfully", "Add user", JOptionPane.INFORMATION_MESSAGE);

                jTextField4.setText("");
                jPasswordField4.setText("");
                jTextField5.setText("");
                jTextField6.setText("");
                updateCombo(); //Updating using new information
            } else {
                JOptionPane.showMessageDialog(this, "User name already exist", "User name error", JOptionPane.ERROR_MESSAGE);
            }

        }


    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        session.beginTransaction();
        session.delete(jComboBox1.getSelectedItem());
        session.getTransaction().commit();
        JOptionPane.showMessageDialog(this, "User removed sucessfully", "User Remove", JOptionPane.INFORMATION_MESSAGE);
        updateCombo();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

        String username = jTextField8.getText();
        String password = new String(jPasswordField6.getPassword());

        session.beginTransaction();
        Query qr = session.createQuery("from Userinfo where username=:code");
        qr.setParameter("code", username);

        List<Userinfo> lst = qr.list();
        session.getTransaction().commit();

        Userinfo user = null;
        if (!lst.isEmpty()) {
            user = lst.get(0);
        }

        boolean access = false; //TO check acess is granted or not
        if (user == null) {
            jTextField8.setText("");
            jPasswordField6.setText("");
            JOptionPane.showMessageDialog(this, "User name or password incorrect", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                access = UserPasswordMatch.compareData(user, password); //Compare the password and user name with given data
                if (!username.equals(currentUser.getUsername())) {        //check user trying to reset his password
                    access = false;
                    JOptionPane.showMessageDialog(this, "User name or password incorrect", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }

        if (access) {
            if(main.currentPatient!=null){
            this.jButton13.setEnabled(access);
            this.jLabel44.setText(main.currentPatient.getName());
            }else{
                JOptionPane.showMessageDialog(this, "Please search patient first", "Patint not found", JOptionPane.INFORMATION_MESSAGE);
            
            }

        } else {
            if (user != null) {
                JOptionPane.showMessageDialog(this, "User name or password incorrect", "Login error", JOptionPane.ERROR_MESSAGE);
            }
        }


    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        int result = -1;
        result = JOptionPane.showConfirmDialog(this, "You are going to completely remove this patient.\nYou cannot undoone unless you have exported data.", "Schedule", JOptionPane.OK_CANCEL_OPTION);
        if (result == 0) {
            session.beginTransaction();
            session.delete(this.currentPatient);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(this, "Patient removed sucessfully", "Patient Remove", JOptionPane.INFORMATION_MESSAGE);
            main.currentPatient = null;
            this.currentPatient=null;
            main.viewVoid();
        }

    }//GEN-LAST:event_jButton13ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MoreInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MoreInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MoreInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MoreInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Session n = null;
                Patient p = null;
                new MoreInterface(n, p, null,null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JFileChooser fileChooser1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JPasswordField jPasswordField4;
    private javax.swing.JPasswordField jPasswordField5;
    private javax.swing.JPasswordField jPasswordField6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
