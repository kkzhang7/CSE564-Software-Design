/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myasu.crudcli.gradebook.xzhan320.netbeans;

import com.sun.jersey.api.client.ClientResponse;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author xzhan
 */
public class Grading_REST_UI extends javax.swing.JFrame {
    
    private static final Logger LOG = LoggerFactory.getLogger(Grading_REST_UI.class);
    
    private Grading_CRUD_cl grading_CRUD_rest_client;
    
    private URI resourceURI;

    /**
     * Creates new form Appointment_REST_UI
     */
    public Grading_REST_UI() {
        LOG.info("Creating a Grading_REST_UI object");
        initComponents();
        
        grading_CRUD_rest_client = new Grading_CRUD_cl();
    }

    private String convertFormToXMLString(){
        Gradebook gradebook = new Gradebook();
        if (!jtfId.getText().equals("")){
            gradebook.setId(Integer.parseInt(jtfId.getText()));
        }
        try{
            gradebook.setStudentId(jtfStudentId.getText());
            gradebook.setGradingItem(myComboBox.getSelectedItem().toString());
            gradebook.setGrade(Double.parseDouble(jtfGrade.getText()));
            gradebook.setGradingComment(myTextArea.getText());
        } catch(NumberFormatException e) {
            System.out.println(e);
        }
        String xmlString = Converter.convertFromObjectToXml(gradebook, gradebook.getClass());
        
        return xmlString;
    }
    
    private void populateForm(ClientResponse clientResponse){
        LOG.info("Populating the UI with the Gradebook info");
        
        String entity = clientResponse.getEntity(String.class);
        
        LOG.debug("The Client Response entity is {}", entity);
        
        try{
            if ((clientResponse.getStatus() == Response.Status.OK.getStatusCode()) ||
                (clientResponse.getStatus() == Response.Status.CREATED.getStatusCode())){
                Gradebook _gradebook = (Gradebook)Converter.convertFromXmlToObject(entity, Gradebook.class);
                LOG.debug("The Client Response gradebook object is {}", _gradebook);
                
                // Populate Gradebook info
                jtfId.setText(Integer.toString(_gradebook.getId()));
                jtfStudentId.setText(_gradebook.getStudentId());
                myComboBox.setSelectedItem(String.valueOf(_gradebook.getGradingItem()));
                jtfGrade.setText(Double.toString(_gradebook.getGrade()));
                myTextArea.setText(_gradebook.getGradingComment());
            }
            else {
                jtfStudentId.setText("");
                myComboBox.setSelectedIndex(0);
                jtfGrade.setText("");
                myTextArea.setText("");
            }
            
            // Populate HTTP Header Information
            jtfHttpCode.setText(Integer.toString(clientResponse.getStatus()));
            jtfMediaType.setText(clientResponse.getType().toString());
            
            // The Location filed is only populated when a Resource is created
            if (clientResponse.getStatus() == Response.Status.CREATED.getStatusCode()){
                jtfLocation.setText(clientResponse.getLocation().toString());
            }
            else {
                jtfLocation.setText("");
            }
            
        } catch (JAXBException e){
            e.printStackTrace();
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

        myButtonGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jrbCreate = new javax.swing.JRadioButton();
        jrbRead = new javax.swing.JRadioButton();
        jrbUpdate = new javax.swing.JRadioButton();
        jrbDelete = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        myComboBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jtfId = new javax.swing.JTextField();
        jtfStudentId = new javax.swing.JTextField();
        jtfGrade = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jtfHttpCode = new javax.swing.JTextField();
        jtfMediaType = new javax.swing.JTextField();
        jtfLocation = new javax.swing.JTextField();
        jbtSubmit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        myTextArea = new javax.swing.JTextArea();
        jbtClear = new javax.swing.JButton();
        jlbFeedback = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gradebook system");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setText("Action Panel");

        myButtonGroup.add(jrbCreate);
        jrbCreate.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jrbCreate.setText("Create");

        myButtonGroup.add(jrbRead);
        jrbRead.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jrbRead.setText("Read");

        myButtonGroup.add(jrbUpdate);
        jrbUpdate.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jrbUpdate.setText("Update");

        myButtonGroup.add(jrbDelete);
        jrbDelete.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jrbDelete.setText("Delete");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Student ID");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("Grading Item");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel4.setText("Record ID");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setText("Grade");

        myComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Assignment", "Mid-term", "Quiz", "Class-lab" }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setText("Comment");

        jtfStudentId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel7.setText("HTTP Header Info");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel8.setText("HTTP Status Code");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel9.setText("Media Type");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel10.setText("Location");

        jtfHttpCode.setEditable(false);

        jtfMediaType.setEditable(false);

        jtfLocation.setEditable(false);

        jbtSubmit.setText("Submit");
        jbtSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSubmitActionPerformed(evt);
            }
        });

        myTextArea.setColumns(20);
        myTextArea.setLineWrap(true);
        myTextArea.setRows(5);
        myTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(myTextArea);

        jbtClear.setText("Clear");
        jbtClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtClearActionPerformed(evt);
            }
        });

        jlbFeedback.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jlbFeedback.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jlbFeedback, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtfLocation))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jrbUpdate)
                                        .addGap(63, 63, 63)
                                        .addComponent(jLabel2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jrbCreate)
                                            .addComponent(jrbRead)
                                            .addComponent(jrbDelete))
                                        .addGap(67, 67, 67)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jbtClear, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(55, 55, 55)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jbtSubmit))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addComponent(jLabel9)
                                                .addGap(18, 18, 18)
                                                .addComponent(jtfMediaType, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jtfGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jtfStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(myComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jtfId, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(63, 63, 63)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jtfHttpCode, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jrbCreate)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jrbRead)
                            .addComponent(jLabel4)
                            .addComponent(jtfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jrbUpdate)
                            .addComponent(jLabel2)
                            .addComponent(jtfStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jrbDelete)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jtfGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(myComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbtSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlbFeedback, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jtfHttpCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jtfMediaType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jtfLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSubmitActionPerformed
        // TODO add your handling code here:
        LOG.info("Invoking REST Client based on selection");
        
        String gradebookID = jtfId.getText();
        
        if (jrbCreate.isSelected()){
            LOG.debug("Invoking {} action", jrbCreate.getText());//Create
            
            ClientResponse clientResponse = grading_CRUD_rest_client.createGradebook(this.convertFormToXMLString());
            
            resourceURI = clientResponse.getLocation();
            LOG.debug("Retrieved location {}", resourceURI);
            
            String httpStatusInfo = String.valueOf(clientResponse.getStatusInfo());
            
            this.populateForm(clientResponse);
            this.jlbFeedback.setText("Last Operation: CREATE - Record ID " + this.jtfId.getText() + "  HTTP Status: " + httpStatusInfo);
        } else if (jrbRead.isSelected()) {
            LOG.debug("Invoking {} action", jrbRead.getText());//Read
                    
            ClientResponse clientResponse = grading_CRUD_rest_client.retrieveGradebook(ClientResponse.class, gradebookID);
            
            String httpStatusInfo = String.valueOf(clientResponse.getStatusInfo());
            
            this.populateForm(clientResponse);
            this.jlbFeedback.setText("Last Operation: READ - Record ID " + this.jtfId.getText() + "  HTTP Status: " + httpStatusInfo);
        } else if (jrbUpdate.isSelected()) {
            LOG.debug("Invoking {} action", jrbUpdate.getText());//Update
            
            ClientResponse clientResponse = grading_CRUD_rest_client.updateGradebook(this.convertFormToXMLString(), gradebookID);
            
            String httpStatusInfo = String.valueOf(clientResponse.getStatusInfo());
            
            this.populateForm(clientResponse);
            this.jlbFeedback.setText("Last Operation: UPDATE - Record ID " + this.jtfId.getText() + "  HTTP Status: " + httpStatusInfo);
        } else if (jrbDelete.isSelected()) {
            LOG.debug("Invoking {} action", jrbDelete.getText());//Delete
            
            ClientResponse clientResponse = grading_CRUD_rest_client.deleteGradebook(gradebookID);
            
            LOG.info("Feedback: " + clientResponse);
            
            String httpStatusInfo = String.valueOf(clientResponse.getStatusInfo());
            String httpStatus = String.valueOf(clientResponse.getStatus());
            String mediaType = String.valueOf(clientResponse.getType());
            //LOG.debug("The output: {}", httpStatusInfo);
            //LOG.debug("The output: {}", httpStatus);
            //LOG.debug("The output: {}", mediaType);
            this.jlbFeedback.setText("Last Operation: DELETE - Record ID " + this.jtfId.getText() + "  HTTP Status: " + httpStatusInfo);
            this.jtfHttpCode.setText(httpStatus);
            this.jtfMediaType.setText(mediaType);
            this.jtfLocation.setText("");
            this.jtfGrade.setText("");
            this.jtfStudentId.setText("");
            this.myTextArea.setText("");
            this.myComboBox.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jbtSubmitActionPerformed

    private void jbtClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtClearActionPerformed
        // TODO add your handling code here:
        // Reset all entries input
        myButtonGroup.clearSelection();
        myComboBox.setSelectedIndex(0);
        jtfId.setText("");
        jtfStudentId.setText("");
        jtfGrade.setText("");
        myTextArea.setText("");
        jtfHttpCode.setText("");
        jtfMediaType.setText("");
        jtfLocation.setText("");
        jtfId.grabFocus();
    }//GEN-LAST:event_jbtClearActionPerformed

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
            java.util.logging.Logger.getLogger(Grading_REST_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Grading_REST_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Grading_REST_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Grading_REST_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Grading_REST_UI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtClear;
    private javax.swing.JButton jbtSubmit;
    private javax.swing.JLabel jlbFeedback;
    private javax.swing.JRadioButton jrbCreate;
    private javax.swing.JRadioButton jrbDelete;
    private javax.swing.JRadioButton jrbRead;
    private javax.swing.JRadioButton jrbUpdate;
    private javax.swing.JTextField jtfGrade;
    private javax.swing.JTextField jtfHttpCode;
    private javax.swing.JTextField jtfId;
    private javax.swing.JTextField jtfLocation;
    private javax.swing.JTextField jtfMediaType;
    private javax.swing.JTextField jtfStudentId;
    private javax.swing.ButtonGroup myButtonGroup;
    private javax.swing.JComboBox<String> myComboBox;
    private javax.swing.JTextArea myTextArea;
    // End of variables declaration//GEN-END:variables
}
