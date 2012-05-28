/*
 * gDisplay.java
 *
 * Created on February 15, 2009, 4:02 PM
 */

package botmania;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics.*;
import java.lang.*;
import java.util.*;
import java.applet.*;
import java.io.*;
import java.lang.Object.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author  avijit
 */
public class gDisplay extends javax.swing.JFrame implements Runnable{

    /** Creates new form gDisplay */
    public gDisplay() {
        initComponents();
    }
    
    startGame sob;
    Image flag1,flag2,buffer3;
    public gDisplay(startGame obj) {
        initComponents();
        sob=obj;
        Toolkit t = Toolkit.getDefaultToolkit();
         flag1 = t.createImage ("flag1.png");
	flag2 = t.createImage ("flag2.png");
     }
     playerInfo bot[]=new playerInfo[6];
     int cc=0;
     Random rand;
    public void addBot(playerInfo o)
    {
    bot[cc++]=o;
    }
     Image buffer,buffer2;
    public void run()
    {
         //System.out.print("NOOO");
        buffer=createImage(45,45);
        buffer2=createImage(700,700);
        buffer3=createImage(700,700);
       while(true)
       {
         repaint();   
         try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(gDisplay.class.getName()).log(Level.SEVERE, null, ex);
            }
       }

    }
    int count=0;
    int oldx,oldy;
    @Override
    public void paint(Graphics g)
    {
        ////following uses Image double buffering
       
    int ix=350,iy=650,fx=350,fy=50;
    if(sob.initialPaint==0 )
        {
        Graphics scr=g;
        Graphics t=buffer2.getGraphics();
        count++;
        t.fillRect(0, 0, 700, 700); 
            for(int i=0;i<700;i++)
               {
                    for(int j=0;j<700;j++)
                    {
                    if(sob.arr[i][j]=='E')
                    {
                        t.setColor(new Color(0,0,0));
                        
                    }

                    if(sob.arr[i][j]=='W')
                    {
                        if((i)%2==0)
                     t.setColor(new Color(251,173,62));
                      else
                     t.setColor(new Color(127, 78, 0 ));
                    
                    }

                 /*   if(sob.arr[i][j]=='A')
                    {
                        ix=i;
                        iy=j;
                    }
                    if(sob.arr[i][j]=='Z')
                    {
                        fx=i;
                        fy=j;
                    }
                    */
                    t.drawRect(i, j, 1, 1);
                }
            }
  //  while(t.drawImage(flag1, ix, iy, this)==false){}
   // while(t.drawImage(flag2, fx, fy, this)==false){}
     sob.initialPaint=1;
     scr.drawImage(buffer2, 0,0,null);
     Graphics m;
     m=buffer3.getGraphics();
     m.drawImage(buffer2,0,0,null);
     }
     
    else
    {
       
        Graphics scr=g;
        //buffer2=buffer3;
        Graphics t=buffer2.getGraphics();
        scr.drawImage(buffer2, 0, 0, null);
        t.drawImage(buffer3,0,0,null);
        if(bot[0]!=null)
        {
            for(int i=0;i<cc;i++)
            {
                if(bot[i]!=null)
                {//System.out.print("bot[i]="+bot[i]+"\n");
                    //System.out.print("ans="+i+"\n\n ");   
                    try {
                           
                           // scr.drawImage(buffer, x-10, y-10, null);
                           t.setColor(Color.white);
                            if(sob.playerObj[i].health>90)
                            t.setColor(Color.green);
			
                            else if(sob.playerObj[i].health>80)
                            t.setColor(Color.blue);

                            else if(sob.playerObj[i].health>70)
                            t.setColor(Color.yellow);

                            else if(sob.playerObj[i].health>60)
                            t.setColor(Color.cyan);

                            else if(sob.playerObj[i].health>50)
                            t.setColor(Color.white);
                           
                           // System.out.print(" "+sob.playerObj[i].health+" "+sob.playerObj[i].teamid+" "+sob.playerObj[i].playerid+" "+sob.playerObj[i].packet+" "+i+"\n");
                            /////////outer oval
                            
                            int x = (int) bot[i].myx;
                            int y = (int) bot[i].myy;
                           
                            t.fillOval(x, y, 25, 25);
                            /////////////////
                            	
                            t.setColor(Color.red);

                        if(sob.playerObj[i].health<50 && sob.playerObj[i].health>=40)
			t.setColor(Color.green);
			
			else if(sob.playerObj[i].health<40 && sob.playerObj[i].health>=30)
			t.setColor(Color.blue);
			
			else if(sob.playerObj[i].health<30 && sob.playerObj[i].health>=20)
			t.setColor(Color.orange);
			
			else if(sob.playerObj[i].health<20 && sob.playerObj[i].health>=10)
			t.setColor(Color.cyan);
			
			else if(sob.playerObj[i].health<10 && sob.playerObj[i].health>0)
			t.setColor(Color.black);
	
                            
                            
                            
                           // scr.setColor(Color.green);
                            
                            
                            ////////inner Oval
                            t.fillOval(x+5, y+5, 15, 15);
                            
                          
                            
                             
                            /////Nozzel
                          
                          
                            double ang=(Math.PI*bot[i].movedir*1.0/180.0);
                            double angu=((Math.PI*bot[i].movedir+90)%360*1.0/180.0);
                            double angb=((Math.PI*bot[i].movedir-90)%360*1.0/180.0);
                            int length = 15;
                            int lx,ly;
                            lx=x+13;
                            ly=y+13;
                            
                            
                            t.setColor(Color.red);
                            t.drawLine((int)(lx-Math.cos(angu)),(int)(ly+Math.sin(angu)) , (int)(lx-Math.cos(angu))+(int)((length+5)*Math.cos(ang)), (int)(ly+Math.sin(angu))+(int)((length+5)*Math.sin(ang)));
                            t.drawLine(lx,ly , lx+(int)(length*Math.cos(ang)), ly+(int)(length*Math.sin(ang)));
                            t.drawLine((int)(lx+Math.cos(angb)),(int)(ly+Math.sin(angb)) , (int)(lx+Math.cos(angb))+(int)((length+5)*Math.cos(ang)), (int)(ly+Math.sin(angb))+(int)((length+5)*Math.sin(ang)));
                            
                            
                              //////////flags
                            while(t.drawImage(flag1, (int)sob.teamObj.x, (int)sob.teamObj.y, this)==false){}
                            while(t.drawImage(flag2, (int)sob.enemyObj.x, (int)sob.enemyObj.y, this)==false){}
                        
                            int inx=lx;
                            int iny=ly;
                            oldx=inx;
                            oldy=iny;
                            int ct=0;
                            int dmin=sob.playerObj[i].range;
                rand=new Random();   
                                          
                Color c1=new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
		if(bot[i].hasfired==1 && bot[i].tofire==1)
                {
                   scr.setColor(c1);
		   scr.drawLine((int)bot[i].fired[0], (int)bot[i].fired[1], (int)bot[i].fired[2], (int)bot[i].fired[3]);
                    /*scr.setColor(Color.BLACK);
                    scr.drawLine((int)bot[i].fired[0], (int)bot[i].fired[1], (int)bot[i].fired[2], (int)bot[i].fired[3]);*/bot[i].hasfired=0;    
                }
                
                if(bot[i].hasfired==1 && bot[i].tofire==2)
                {
                   scr.setColor(Color.yellow);
		   scr.fillOval((int)bot[i].fired[2], (int)bot[i].fired[3],8,8);
                   scr.setColor(Color.orange);
                   scr.fillOval((int)bot[i].fired[2]+1, (int)bot[i].fired[3]+1,5,5);
                   
                    /*scr.setColor(Color.BLACK);
                    scr.drawLine((int)bot[i].fired[0], (int)bot[i].fired[1], (int)bot[i].fired[2], (int)bot[i].fired[3]);*/bot[i].hasfired=0;    
                }
                
                        /*if(bot[i].hasscanned==1)
                        {
                        scr.setColor(Color.WHITE);
                        scr.drawLine((int)bot[i].scan[0], (int)bot[i].scan[1], (int)bot[i].scan[2], (int)bot[i].scan[3]);
                        bot[i].hasscanned=0;
                        }*/
                           
                          
                        /*if(bot[i].img==1)
                        {
                        buffer=createImage(45,45);
                        Graphics s=buffer.getGraphics();
                        s.fillRect(0, 0, 45, 45);
                        bot[i].img=0;
                        }*/
                           // t.setColor(new Color(255, 255, 255));
                           
                            x = (int) bot[i].myx;
                            y = (int) bot[i].myy;
                            for (int ii = x-10 ; ii <= x + 35; ii++) 
                            {
                                for (int j = y-10 ; j <= y + 35; j++) 
                                {
                                   
                                    if(ii>=0 && ii<700 && j>=0 && j<700)
                                    {
                                    
                                        if (sob.arr[ii][j] == 'E') 
                                        {
                                            //t.setColor(new Color(0, 0, 0));
                                        }

                                        if (sob.arr[ii][j] == 'W') 
                                        {
                                            /*  if((ii)%2==0)
                                              t.setColor(new Color(251,173,62));
                                              else
                                              t.setColor(new Color(127, 78, 0 ));*/
                                              if(ii>x-1 && ii<x+26 && j>y-1 && j<y+26)
                                              {
                                                  sob.playerObj[i].blocked=1;
                                                  
                                                 // buffer=createImage(45,45);
                                              }
                                                 //buffer=createImage(45,45);
                                        }

                                       
                                        
                                       // t.fillRect(ii-x+10, j-y+10, 1, 1);
                                        
                                    }
                                         x = (int) bot[i].myx;
                                        y = (int) bot[i].myy;
                                        if(Math.abs(ii-x)>50 || Math.abs(j-y)>50)
                                        {
                                            ii=x-10;
                                            j=y-10;
                                        }
                                }
                            }
                             Thread.sleep(10);
                         // scr.drawImage(buffer, x-10, y-10, null);
                          
                            
                            
                        } catch (Exception ex) {
                            Logger.getLogger(gDisplay.class.getName()).log(Level.SEVERE, null, ex);
                        }
                
                }
                }
            }

    
         }
    
   
    if(sob.apiCount>100000)
    {
    java.awt.event.WindowEvent evt =null;
    formWindowClosing(evt);
    this.setVisible(false);
    int sc1=sob.playerObj[0].score+sob.playerObj[1].score+sob.playerObj[2].score;
    int sc2=sob.playerObj[3].score+sob.playerObj[4].score+sob.playerObj[5].score;
    finalScore ob=new finalScore(sc1,sc2,sob);
    ob.setLocation(20, 150);
    ob.setVisible(true);
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

        setIconImage(Toolkit.getDefaultToolkit().getImage("ico.jpg"));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
// TODO add your handling code here:
      try{
           sob.sc1.close();
           sob.sc2.close();
           sob.sc3.close();
           sob.sc4.close();
           sob.sc5.close();
           sob.sct.close();
           sob.child.destroy();
           sob.child1.destroy();
           sob.child2.destroy();
           sob.child3.destroy();
           sob.child4.destroy();
           sob.child5.destroy();
           
            }catch(Exception ee){}
    //System.exit(0);
}//GEN-LAST:event_formWindowClosing

    /**
    * @param args the command line arguments
    */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
