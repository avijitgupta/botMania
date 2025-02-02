/*
 * finalScore.java
 *
 * Created on February 25, 2009, 6:56 PM
 */

package botmania;
import java.sql.*;
import java.awt.*;
/**
 *
 * @author  avijit
 */
public class finalScore extends javax.swing.JFrame {

    /** Creates new form finalScore */
    public finalScore() {
        initComponents();
    }
    public void insertscore(int a,int b, startGame sob)throws Exception
     {
       Statement stmt;
       dataEngine eng = new dataEngine();
       Connection conn = eng.dbConnection();
        try
        {
            stmt = conn.createStatement();
            stmt.executeUpdate("insert into scores " +
                 "values('"+sob.playerObj[0].name.trim().toString()+"',"+a+")");

            stmt.executeUpdate("insert into scores " +
                 "values('"+sob.playerObj[3].name.trim().toString()+"',"+b+")");

            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
     }

     public finalScore(int a,int b, startGame sob) {
        initComponents();
        jLabel1.setText("The Team "+sob.playerObj[0].name.trim()+" Final Score: "+a);
        jLabel2.setText("The Team "+sob.playerObj[3].name.trim()+" Final Score: "+b);
        
        if(a>b)jLabel4.setText("TEAM "+sob.playerObj[0].name.trim()+" WINS");
        else if(b>a)
        jLabel4.setText("TEAM "+sob.playerObj[3].name.trim()+" WINS");
        else 
            jLabel4.setText("THE GAME IS A TIE!! ");
        try{
            insertscore(a,b,sob);
        }catch(Exception e)
        {
            e.printStackTrace();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("ico.jpg"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel1.setText("jLabel1");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel2.setText("jLabel2");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel3.setText("Final Results");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel3)))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(27, 27, 27)
                .addComponent(jLabel4)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new finalScore().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables

}
