/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freemrs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXMonthView;

/**
 *
 * @author Nuwan Prabhath
 */
public class ScheduleInterface extends javax.swing.JPanel {

    /**
     * Creates new form ScheduleInterface
     */
    Session session;
    Patient patient;
    JXDatePicker picker;                //Calander object
    SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");   //To test the date format
    private int tablePosition = -1;     //To track the table position

    public ScheduleInterface(Session session) {
        initComponents();

        this.session = session;

        picker = new JXDatePicker();            //Setting calender
        picker.setDate(Calendar.getInstance().getTime());
        picker.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
        add(picker, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, -1));

        updateComobo();             //Updating combo box to get locations currently available

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jCheckBox2 = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setText("Scheduling");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 27, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("name");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 33, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Age:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, 37, -1, -1));

        jLabel4.setText("value");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 39, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Medical issue:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(376, 39, -1, -1));

        jLabel6.setText("value");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 39, -1, -1));

        jSeparator2.setMinimumSize(new java.awt.Dimension(1060, 0));
        jSeparator2.setPreferredSize(new java.awt.Dimension(1060, 2));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 995, 10));

        jLabel7.setText("Select location:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 90, -1));

        jLabel8.setText("or Enter:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, -1, -1));
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, 90, -1));

        jLabel9.setText("Select start time:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, -1, -1));

        jLabel10.setText("Select date:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "00", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
        add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, -1, -1));

        jLabel11.setText("Select end time:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, -1, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "00", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
        add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 130, -1, -1));

        jCheckBox1.setText("Include time in seach");
        add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, -1, -1));

        jButton1.setBackground(new java.awt.Color(153, 255, 153));
        jButton1.setText("Search schedule");
        jButton1.setToolTipText("Search saved schedules");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 100, -1, -1));

        jButton2.setBackground(new java.awt.Color(153, 255, 153));
        jButton2.setText("Save schedule");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 130, 120, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Total number of patients scheduled:");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, 20, 10));

        jTable1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Name", "Location", "Date", "Start time", "End tilme"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(3).setMinWidth(40);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(40);
            jTable1.getColumnModel().getColumn(4).setMinWidth(40);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(40);
        }

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 680, 160));

        jCheckBox2.setText("Include location in search");
        add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 100, -1, -1));

        jButton3.setBackground(new java.awt.Color(153, 255, 153));
        jButton3.setText("Add entry");
        jButton3.setToolTipText("Add schedule to table");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 210, 100, -1));

        jButton4.setBackground(new java.awt.Color(255, 204, 204));
        jButton4.setText("Remove entry");
        jButton4.setToolTipText("Remove entry froom table");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 240, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        //Set location of the text area equal to selected location from combo box
        String sch = (String) jComboBox1.getSelectedItem();
        if (sch != null) {
            this.jTextField1.setText(sch);
        }

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Add schedule to database
        Schedule sch = new Schedule();
        try {
            java.sql.Date sqlDate = null;
            java.util.Date invoiceDate = formatDate.parse(picker.getEditor().getText()); //get date from clander object
            sqlDate = new java.sql.Date(invoiceDate.getTime());

            sch.setDate(sqlDate);
            sch.setLocation(jTextField1.getText());
            int start = Integer.parseInt(jComboBox2.getSelectedItem().toString());      //get start and end time slots
            int end = Integer.parseInt(jComboBox3.getSelectedItem().toString());
            sch.setStartTime(start);                //Set start and end time to schedule object
            sch.setEndTime(end);
            sch.setPatientId(patient.getPatientId());

            java.util.Date date = new java.util.Date(); //Check the scheduling date is after today
            if (date.after(sqlDate)) {
                JOptionPane.showMessageDialog(this, "Please use valid date", "Schedule", JOptionPane.INFORMATION_MESSAGE);

            } else if (start < end || (start == 12 && end == 13)) { //checking for clash

                session.beginTransaction();                                     //code1= start code2 =end
                Query qr = session.createQuery("from Schedule where ((startTime<= :code1 and :code1 <=endTime) or (startTime<= :code2 and :code2 <=endTime)) and (location!=:code3) and (date= :code4)");
                qr.setParameter("code1", start);
                qr.setParameter("code2", end);
                qr.setParameter("code3", jTextField1.getText());
                qr.setParameter("code4", sqlDate);
                List<Schedule> result = qr.list();
                session.getTransaction().commit();

                if (result.isEmpty()) {
                    session.beginTransaction();
                    session.save(sch);                                  //Saving object 
                    session.getTransaction().commit();
                    JOptionPane.showMessageDialog(null, "Schedule saved successfully", "Schedules", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    //Error message to overlap time clash in two location same time problem
                    JOptionPane.showMessageDialog(null, "Time clash", "Schedules", JOptionPane.WARNING_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Please use valid time slot", "Schedule", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            JOptionPane.showMessageDialog(this, "Enter date in correct format", "Schedule", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //Add schedule to table
        try {
            java.sql.Date sqlDate = null;
            java.util.Date invoiceDate = formatDate.parse(picker.getEditor().getText()); //geting date from 
            sqlDate = new java.sql.Date(invoiceDate.getTime());

            int start = Integer.parseInt(jComboBox2.getSelectedItem().toString());  //get start and end time
            int end = Integer.parseInt(jComboBox3.getSelectedItem().toString());

            java.util.Date date = new java.util.Date();
            if (date.after(sqlDate)) {                      //Checking date is after
                JOptionPane.showMessageDialog(this, "Please use valid date", "Schedule", JOptionPane.INFORMATION_MESSAGE);

            } else if (start < end || (start == 12 && end == 13)) {

                if (tablePosition == jTable1.getRowCount() - 1) {   //If rows are finished. Create new one
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                    model.addRow(new Object[]{null, null, null});
                }

                jTable1.setValueAt(patient.getName(), ++tablePosition, 0);
                jTable1.setValueAt(jTextField1.getText(), tablePosition, 1);
                jTable1.setValueAt(picker.getEditor().getText(), tablePosition, 2);
                jTable1.setValueAt(start + ".00", tablePosition, 3);
                jTable1.setValueAt(end + ".00", tablePosition, 4);

            } else {
                JOptionPane.showMessageDialog(this, "Please use valid time slot", "Schedule", JOptionPane.INFORMATION_MESSAGE);
            }


        } catch (ParseException ex) {
            Logger.getLogger(ScheduleInterface.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //Removing item from table
        int selectedRow = this.jTable1.getSelectedRow();
        try {
            Schedule selected = (Schedule) jTable1.getValueAt(selectedRow, 1);

            int result;
            result = JOptionPane.showConfirmDialog(this, "You are going to remove previous schedule", "Schedule", JOptionPane.OK_CANCEL_OPTION);
            if (result == 0) {              //Ok message

                session.beginTransaction();
                session.delete(selected);
                session.getTransaction().commit();

                this.tablePosition--;           //Reduce table position from one
                DefaultTableModel model = (DefaultTableModel) this.jTable1.getModel();
                int[] rows = jTable1.getSelectedRows();
                for (int i = 0; i < rows.length; i++) { //remove rows
                    model.removeRow(rows[i] - i);
                }
            }

        } catch (java.lang.ClassCastException e) {
            this.tablePosition--;
            DefaultTableModel model = (DefaultTableModel) this.jTable1.getModel();
            int[] rows = jTable1.getSelectedRows();
            for (int i = 0; i < rows.length; i++) {
                model.removeRow(rows[i] - i);
            }
        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Searching schedules
        try {
            boolean location = jCheckBox2.isSelected(); //Checking for location and time search
            boolean time = jCheckBox1.isSelected();

            java.sql.Date sqlDate = null;
            java.util.Date invoiceDate = formatDate.parse(picker.getEditor().getText()); //get selected date
            sqlDate = new java.sql.Date(invoiceDate.getTime());

            session.beginTransaction();

            if (location && time) {  //Compare with check boxes and do required action
                Query qr = session.createQuery("from Schedule where date=:code1 and startTime=:code2 and endTime=:code3 and location=:code4");
                qr.setParameter("code1", sqlDate);
                qr.setParameter("code4", jTextField1.getText());
                qr.setParameter("code2", Integer.parseInt(jComboBox2.getSelectedItem().toString()));
                qr.setParameter("code3", Integer.parseInt(jComboBox3.getSelectedItem().toString()));
                List<Schedule> result = qr.list();
                session.getTransaction().commit();
                addToTable(result);
                jLabel13.setText(result.size() + "");

            } else if (location && !time) {
                Query qr = session.createQuery("from Schedule where date=:code1 and location=:code4");
                qr.setParameter("code1", sqlDate);
                qr.setParameter("code4", jTextField1.getText());
                List<Schedule> result = qr.list();
                session.getTransaction().commit();
                addToTable(result);
                jLabel13.setText(result.size() + "");

            } else if (!location && time) {
                Query qr = session.createQuery("from Schedule where date=:code1 and startTime=:code2 and endTime=:code3");
                qr.setParameter("code1", sqlDate);
                qr.setParameter("code2", Integer.parseInt(jComboBox2.getSelectedItem().toString()));
                qr.setParameter("code3", Integer.parseInt(jComboBox3.getSelectedItem().toString()));
                List<Schedule> result = qr.list();
                session.getTransaction().commit();
                addToTable(result);
                jLabel13.setText(result.size() + "");

            } else if (!location && !time) {
                Query qr = session.createQuery("from Schedule where date=:code1");
                qr.setParameter("code1", sqlDate);
                List<Schedule> result = qr.list();
                session.getTransaction().commit();
                addToTable(result);
                jLabel13.setText(result.size() + "");
            }

        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Enter date correctly", "Schedule", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(ScheduleInterface.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void addToTable(List<Schedule> list) {  // Used to add search results to jtable
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setNumRows(0);

        this.tablePosition = -1;
        for (Schedule sch : list) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.addRow(new Object[]{null, null, null});

            session.beginTransaction();
            Query qr = session.createQuery("from Patient where patientId =:code");
            qr.setParameter("code", sch.getPatientId());
            List<Patient> result = qr.list();
            session.getTransaction().commit();

            jTable1.setValueAt(result.get(0).getName(), ++tablePosition, 0);
            jTable1.setValueAt(sch, tablePosition, 1);
            jTable1.setValueAt(sch.getDate().toString(), tablePosition, 2);
            jTable1.setValueAt(sch.getStartTime() + ".00", tablePosition, 3);
            jTable1.setValueAt(sch.getEndTime() + ".00", tablePosition, 4);
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    void updateInfo(Patient currentPatient) {       //Update all sub types of interfaces
        this.patient = currentPatient;
        updateDemographic();
        updateMedicalInfo();
        updateComobo();
    }

    private void updateDemographic() {              //Update dempgraphic information in interface
        jLabel2.setText(patient.getName());
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(patient.getBirthday());
        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
        this.jLabel4.setText(age + "");
    }

    private void updateMedicalInfo() {   //Update medical info
        session.beginTransaction();
        Query qr = session.createQuery("from GeneralMedicalInfo where patientId =:code");
        qr.setParameter("code", patient.getPatientId());
        List<GeneralMedicalInfo> result = qr.list();
        session.getTransaction().commit();

        if (!result.isEmpty()) {
            GeneralMedicalInfo medical = result.get(0);
            this.jLabel6.setText(medical.getMainMedicalProblem());
        }
    }

    private void updateComobo() {   //Add locations to the combo box
        jComboBox1.removeAllItems();

        session.beginTransaction();
        Query qr = session.createQuery("from Schedule group by location");
        List<Schedule> result = qr.list();
        session.getTransaction().commit();

        if (!result.isEmpty()) {
            for (Schedule sch : result) {
                jComboBox1.addItem(sch.getLocation());
            }
        }

    }

}
