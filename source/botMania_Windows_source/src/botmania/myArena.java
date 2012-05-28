/*
 * myArena.java
 *
 * Created on February 14, 2009, 1:20 PM
 */
package botmania;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics.*;
import java.lang.*;
import java.util.*;
import java.applet.*;
import java.io.*;
/**
 *
 * @author  avijit
 */
public class myArena extends javax.swing.JFrame {
    int mouseX=0,mouseY=0;
    int semaphore=0;
    String str="";
    char arr[][]=new char[700][700];
    int inx[],iny[],finx[],finy[];
    int top=0;
    myArena tvar;
    /** Creates new form myArena */
    public myArena() {
        initComponents();
            
        
   }
   int opt;
   choice ch;
   Image flag1,flag2;
    public myArena(choice c) {
       tvar=this;
        initComponents();
        ch=c;
        addMouseListener(new MyMouseAdapter(this,c));
        //setBackground(Color.BLACK);
        top=0;
        inx=new int[700];
        iny=new int[700];
         finx=new int[700];
        finy=new int[700];
       
        Toolkit t = Toolkit.getDefaultToolkit();
        flag1 = t.createImage ("flag1.png");
	flag2 = t.createImage ("flag2.png");

        // flag1=getImage(getDocumentBase(),"C:\\Documents and Settings\\avijit\\My Documents\\NetBeansProjects\\botMania\\src\\botmania\\flag1.png");
        //flag1=getImage(getDocumentBase(),"C:\\Documents and Settings\\avijit\\My Documents\\NetBeansProjects\\botMania\\src\\botmania\\flag2.png");
        
        for(int i=0;i<700;i++)
        {
            for(int j=0;j<700;j++)
            {
                arr[i][j]='E';
            }
        }
        
        opt=c.option;
       new forSaving(this);
      
   }
    int ct=0;
      
    Image buffer;
    int flag1E=0,flag2E=0;
   @Override
    public void paint(Graphics g)
   {
       //setBackground(new Color(255,255,255));
       Graphics t=g;
       int maxx,maxy,minx=0,miny=0;
       if(ch.flagOneAdded==true)
      {
        
      }      
       if(semaphore==0)
       {
           buffer=createImage(700,700);
        t.fillRect(0, 0, 700, 700); 
       }
      // System.out.print(top+"\n");
       else{
        
            if(inx[top-1]>finx[top-1])
            {
                maxx=inx[top-1];
                minx=finx[top-1];
            }
            else
            {
                 maxx=finx[top-1];
                 minx=inx[top-1];
            }
            
            if(iny[top-1]>finy[top-1])
            {
                 maxy=iny[top-1];
                miny=finy[top-1];
            }
            
             else
            {
                 maxy=finy[top-1];
                 miny=iny[top-1];
            }
if(!(maxx==minx || miny==maxy))
    { 
        try {
            buffer = createImage(maxx - minx, maxy - miny);
            g = buffer.getGraphics();
        } catch (Exception e) {
        }
    }
   if(maxx==minx && miny!=maxy)
   {
            buffer = createImage(maxx - minx, 1);
            g = buffer.getGraphics();
   }
            
    if(maxx!=minx && miny==maxy)
   {
            buffer = createImage(1, maxy-miny);
            g = buffer.getGraphics();
   }
        
    if(maxx==minx && miny==maxy)
   {
            buffer = createImage(1, 1);
            g = buffer.getGraphics();
   }
            
       for(int i=minx;i<=maxx;i++)
           {
                for(int j=miny;j<=maxy;j++)
                {
                   // System.out.print("here");
                if(arr[i][j]=='E')
                {
                    g.setColor(new Color(0,0,0));
                                                   
                }
                
                if(arr[i][j]=='W')
                {
                    if((i)%2==0)
                    { 
                       // if(ct%2==0)
                        g.setColor(new Color(251,173,62));
                      //  else
                      //  g.setColor(new Color(127, 78, 0 ));
                      //  ct++;
                    }
                    else
                    {
                      //  if(ct%2==1)
                        g.setColor(new Color(127, 78, 0 ));
                      //  else
                      //  g.setColor(new Color(251,173,62));
                       // ct++;
                    }//
                    
                    
                    /*
                     * try other colors
                     * 127 78 0 
                     * 13 63 1
                     * 251 173 62
                     * 185 1 92  //
                     * 252 123 211
                                    
                     */
                    //o.setVisible(true);
                }
                
                if(arr[i][j]=='A')
                {   
                    flag1E=1;
                    t.drawImage(flag1, i, j, this);
                }
                if(arr[i][j]=='Z')
                {
                    flag2E=1;
                    t.drawImage(flag2, i, j, this);
                }
                 //System.out.print(i+" "+j+"\n");
                g.fillRect(i-minx, j-miny, 1, 1);
            }
        }
       }
      t.drawImage(buffer,minx,miny, null);
                if(ch.flagOneAdded==true)
                while(t.drawImage(flag1, ch.f1x, ch.f1y, this)==false){}
              
                if(ch.flagTwoAdded==true)
                while(t.drawImage(flag2, ch.f2x, ch.f2y, this)==false){}
       //g.fillRect(0, 0, 600, 600); 
       //setForeground(new Color(255,255,255));
       
       
   }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(0, 0, 0));
        setIconImage(Toolkit.getDefaultToolkit().getImage("ico.jpg"));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
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

private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
// TODO add your handling code here:
    
}//GEN-LAST:event_formWindowClosed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new myArena().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}

class MyMouseAdapter extends MouseAdapter
{
    myArena obj;
    choice c;
    public MyMouseAdapter(myArena ob, choice ch)
    {
        c=ch;
        obj=ob;
    }
    @Override
    public void mousePressed(MouseEvent me)
    {
        obj.mouseX=me.getX();
        obj.mouseY=me.getY();
        //obj.str="Mouse pressed at "+obj.mouseX+" "+obj.mouseY+"\n";
        
        if(obj.mouseX>699) obj.mouseX=699;
         if(obj.mouseX>699) obj.mouseX=699;
         if(obj.mouseX<0) obj.mouseX=0;
        if(obj.mouseY<0) obj.mouseY=0;
        
        
        obj.inx[obj.top]=obj.mouseX;
        obj.iny[obj.top]=obj.mouseY;

       
    }
    
    

    @Override
    public void mouseReleased(MouseEvent me)
    {
        obj.mouseX=me.getX();
        obj.mouseY=me.getY();
        //obj.str="Mouse released at "+obj.mouseX+" "+obj.mouseY+"\n";
//
         if(obj.mouseX>699) obj.mouseX=699;
         if(obj.mouseY>699) obj.mouseY=699;
         if(obj.mouseX<0) obj.mouseX=0;
        if(obj.mouseY<0) obj.mouseY=0;
        
        obj.finx[obj.top]=obj.mouseX;
        obj.finy[obj.top]=obj.mouseY;

        myPaint();
    }

        public void myPaint()
        {
           
            int maxx,maxy,minx,miny;
            if(obj.inx[obj.top]>obj.finx[obj.top])
            {
                maxx=obj.inx[obj.top];
                minx=obj.finx[obj.top];
            }
            else
            {
                 maxx=obj.finx[obj.top];
                 minx=obj.inx[obj.top];
            }
            
            if(obj.iny[obj.top]>obj.finy[obj.top])
            {
                 maxy=obj.iny[obj.top];
                miny=obj.finy[obj.top];
            }
            
             else
            {
                 maxy=obj.finy[obj.top];
                 miny=obj.iny[obj.top];
            }
            
           if( c.option== 0)
            {
               for(int i=minx;i<=maxx;i++)
               {
                    for(int j=miny;j<=maxy;j++)
                    {
                        obj.arr[i][j]='W';
                    }
               }
            }
            
             if( c.option== 1)
            {
              //double d=(obj.finy[obj.top]-obj.iny[obj.top])*1.0/(obj.finx[obj.top]-obj.inx[obj.top]);
              double inx=Math.cos(Math.atan2((obj.finy[obj.top]-obj.iny[obj.top]),(obj.finx[obj.top]-obj.inx[obj.top])));
              double iny=Math.sin(Math.atan2((obj.finy[obj.top]-obj.iny[obj.top]),(obj.finx[obj.top]-obj.inx[obj.top])));
              
              double x=obj.inx[obj.top];
              double y=obj.iny[obj.top];
              double fx=obj.finx[obj.top];
              double fy=obj.finy[obj.top];
              
              /////you can improve this.. do it at last
              
              double xl1=x+0.75;
              double yl1=y-0.75;
              double xl2=x-0.75;
              double yl2=y+0.75;
              
              while(Math.abs(fx-x)>1 || Math.abs(fy-y)>1)
              {
                int i,j;
                i=(int)(x);
                j=(int)(y);
                if(i<700 && j<700)
                {
                    obj.arr[i][j]='W';
                    obj.arr[(int)xl1][(int)yl1]='W';
                    obj.arr[(int)xl2][(int)yl2]='W';
                }
                 else break;
                x+=inx;
                y+=iny;
                xl1+=inx;
                yl1+=iny;
                xl2+=inx;
                yl2+=iny;
              }
              
            }
            
             else if( c.option== 2)
            {
               for(int i=minx;i<=maxx;i++)
               {
                    for(int j=miny;j<=maxy;j++)
                    {
                        obj.arr[i][j]='E';
                        if(i==c.f1x && j==c.f1y)
                        {
                            c.flagOneAdded=false;
                            obj.flag1E=0;
                        }
                        if(i==c.f2x && j==c.f2y)
                        {
                            c.flagTwoAdded=false;
                            obj.flag2E=0;
                        }
                    
                    }
               }
            }
             else if(c.option==3 && c.flagOneAdded==false)
             {
                c.flagOneAdded=true;
                obj.arr[obj.inx[obj.top]][obj.iny[obj.top]]='A';
                c.f1x=obj.inx[obj.top];
                c.f1y=obj.iny[obj.top];
                obj.flag1E=1;
                obj.repaint();
             }
            else if(c.option==4 && c.flagTwoAdded==false)
             {
                c.flagTwoAdded=true;
                obj.arr[obj.inx[obj.top]][obj.iny[obj.top]]='Z';
                 c.f2x=obj.inx[obj.top];
                c.f2y=obj.iny[obj.top];
                obj.flag2E=1;
                obj.repaint();
             
            }
            
            ////////////The following arnt used
            else if(c.option==5 && c.flagOneAdded==true)
             {
                obj.arr[c.f1x][c.f1y]='E';
                obj.flag1E=0;
                c.flagOneAdded=false;
                obj.repaint();
             }
            else if(c.option==6 && c.flagTwoAdded==true)
             {
                obj.arr[c.f2x][c.f2y]='E';
                obj.flag2E=0;
                c.flagTwoAdded=false;
                obj.repaint();
                
            }
            //////////////////////////////////////end
            
            
           obj.semaphore=1;
          //obj.repaint(minx,miny,maxx-minx,maxy-miny);
           obj.repaint();
            obj.top++;
        
        }
    
}

class forSaving implements Runnable
{
    Thread t;
    myArena mine;
    forSaving(myArena aa)
    {
        mine=aa;
        t=new Thread(this);
        t.start();
    }
    
    public void run()
    {
        //System.out.print("T");
        while(true)
        {
        if(mine.ch.option==99)   mine.tvar.setVisible(false); 
         
         if(mine.ch.toBeSaved==true)
         {
             
            try
            {
                
                FileWriter fw=new FileWriter(mine.ch.path+mine.ch.name+".bMap");
                //System.out.print(fw);
                CharArrayWriter ff=new CharArrayWriter();
                char res[]=new char[490000];
                int count=0;
               
                
                if(mine.ch.flagOneAdded==false)
                {
                    mine.arr[350][650]='A';
                    mine.ch.f1x=350;
                    mine.ch.f1y=650;
                    mine.ch.flagOneAdded=true;
                    mine.flag1E=1;
                   
                }
                
                if(mine.ch.flagTwoAdded==false)
                {
                    mine.arr[350][50]='Z';
                    mine.ch.f2x=350;
                    mine.ch.f2y=50;
                    mine.ch.flagTwoAdded=true;
                    mine.flag2E=1;
                    
                }
                
                
                mine.inx[mine.top]=0;
                mine.iny[mine.top]=0;
                mine.finx[mine.top]=699;
                mine.finy[mine.top]=699;
                
                
                 for(int i=0;i<700;i++)
                {
                    for(int j=0;j<700;j++)
                    {
                        res[count++]=mine.arr[i][j];
                        
                    }
                }
                
                ff.write(res);
                ff.writeTo(fw);
                fw.close();
                mine.repaint();
                mine.top++;
            }
            catch(Exception e){}
         mine.ch.toBeSaved=false;
         }
         
        }
        
        
    }
    
}