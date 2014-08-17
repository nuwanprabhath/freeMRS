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
    JXDatePicker picker1, picker2, picker3, picker4;
    private Session session;
    private Patient currentPatient;

    public MoreInterface(Session session, Patient patient) {
        initComponents();
        this.session = session;
        currentPatient = patient;

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

        jPasswordField2.setEnabled(false);  //Desabeling password reset until authentication complete
        jPasswordField3.setEnabled(false);
        jButton7.setEnabled(false);
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

    private void serializeFile(String path) {

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

    private void saveFileInfo(String path) {

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

            int id = this.getNewPatientID(p);        //Getting new patient id
            System.out.println("NEW ID " + id);

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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel9))
                            .addComponent(jLabel10)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator5)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15))
                                .addGap(147, 147, 147)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
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
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jButton3))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Export and Import", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Setting-icon small.png"))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel17.setText("Settings");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Reset password");

        jLabel19.setText("Enter current password:");

        jLabel20.setText("Enter new password:");

        jLabel21.setText("Conferm new password:");

        jButton6.setBackground(new java.awt.Color(153, 255, 153));
        jButton6.setText("Authorize");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jPasswordField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField2ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(153, 255, 153));
        jButton7.setText("Change");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel22.setText("Enter your user name:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel17)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel22)
                                        .addComponent(jLabel21)
                                        .addComponent(jLabel19))
                                    .addComponent(jLabel20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPasswordField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel17)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jButton6))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21)
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton7)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(37, 37, 37)
                                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel20))
                                    .addGap(35, 35, 35))))
                        .addGap(49, 49, 49))))
        );

        jTabbedPane1.addTab("Settings", jPanel3);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 370));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String fileName = "src\\reports\\patientReport.jasper";
        HashMap para = new HashMap();
        para.put("start", "'" + picker3.getEditor().getText() + "'");
        para.put("end", "'" + picker4.getEditor().getText() + "'");
        ReportView rw = new ReportView(fileName, para);
        rw.setVisible(true);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String fileName = "src\\reports\\financialReport.jasper";
        HashMap para = new HashMap();
        para.put("start", "'" + picker1.getEditor().getText() + "'");
        para.put("end", "'" + picker2.getEditor().getText() + "'");
        ReportView rw = new ReportView(fileName, para);
        rw.setVisible(true);

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
            System.out.println(file.getAbsolutePath());
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
                access = UserPasswordMatch.compareData(user, password);
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }

        if (access) {
            jPasswordField2.setEnabled(true);
            jPasswordField3.setEnabled(true);
            jButton7.setEnabled(true);

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
                new MoreInterface(n, p).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JFileChooser fileChooser1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
