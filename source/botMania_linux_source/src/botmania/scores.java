/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * scores.java
 *
 * Created on May 15, 2009, 1:46:06 PM
 */

package botmania;
import java.util.*;
import java.awt.*;
/**
 *
 * @author cool
 */
public class scores extends javax.swing.JFrame
{
    private Vector<Vector<String>> data; //used for data from database
    private Vector<String> header; //used to store data header
    /** Creates new form scores */
    public scores() 
    {
        try{
            dataEngine dbengine = new dataEngine();

        data = dbengine.getScore();
        header = new Vector<String>();
        header.add("Team Name");
        header.add("Score");
        initComponents();
        this.setVisible(true);
        }catch(Exception e)
        {
            System.out.println(e);
            this.setVisible(false);
        }
        
         
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setIconImage(Toolkit.getDefaultToolkit().getImage("ico.jpg"));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 20)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("botMania hall of fame");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jTable1.setBackground(new java.awt.Color(129, 172, 206));
        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jTable1.setForeground(new java.awt.Color(253, 248, 248));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            data,header)
    );
    jTable1.setMaximumSize(new java.awt.Dimension(300, 300));
    jTable1.setName("High Scores"); // NOI18N
    jTable1.setSelectionBackground(new java.awt.Color(217, 122, 146));
    jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    jScrollPane1.setViewportView(jTable1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
        .addGroup(layout.createSequentialGroup()
            .addGap(156, 156, 156)
            .addComponent(jLabel1)
            .addContainerGap(176, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1)
            .addGap(18, 18, 18)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
