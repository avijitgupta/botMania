/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package botmania;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;
import java.applet.*;
/**
 *
 * @author avijit
 */
public class startGame {
   gDisplay gServer;
   status stat;
   int apiCount=0;
    playerInfo playerObj[]=new playerInfo[6];
    //flagInfo flags[]=new flagInfo[2];
    char arr[][]=new char[700][700];
    flagInfo teamObj=new flagInfo();
    flagInfo enemyObj= new flagInfo();
    int paintedOnce=0;
    Process child1,child2,child3,child4,child5,child;
    int initialPaint=0;
    String str1="";
    int th[]=new int[6];
    int tar[]=new int[6];
     Socket sct,sc1,sc2,sc3,sc4,sc5;
     int ref_k=0;
    public startGame()
    {
        //default blank map
        for(int i=0;i<700;i++)
        {
            for(int j=0;j<700;j++)
            {
                arr[i][j]='E';
            }    
        }
        arr[350][650]='A';
        arr[350][50]='Z';
        
                teamObj.x=350;
                teamObj.y=650;
                teamObj.istaken=0;
                teamObj.ofteam=1;
                teamObj.pid=0;
                teamObj.tid=0;
                
                enemyObj.x=350;
                enemyObj.y=50;
                enemyObj.istaken=0;
                enemyObj.ofteam=2;
                enemyObj.pid=0;
                teamObj.tid=0;
        
      //  initAll();
        
    }
     public startGame(String filePath)
    {
   
    try
    {
        
   
    BufferedReader br=new BufferedReader(new FileReader(filePath));
    for(int i=0;i<700;i++)
    {
        for(int j=0;j<700;j++)
        {
            arr[i][j]=(char)br.read();
            // System.out.print(arr[i][j]);
            if(arr[i][j]=='A')
            {
                //System.out.print("Flag 1\n");
                teamObj.x=i;
                teamObj.y=j;
                teamObj.istaken=0;
                teamObj.ofteam=1;
                teamObj.pid=0;
                teamObj.tid=0;
            }
            
            if(arr[i][j]=='Z')
            {
               // System.out.print("Flag 2\n");
                enemyObj.x=i;
                enemyObj.y=j;
                enemyObj.istaken=0;
                enemyObj.ofteam=2;
                enemyObj.pid=0;
                teamObj.tid=0;
            }
            
        }    
    }
    //initAll();
     
    }catch(Exception e){System.out.print(e);}
    
    }
     
    
    public void initAll()
    {
        
        
        
        /////////This is the final game server
        
        
       gServer=new gDisplay(this);
       int i; 
       for(i=0;i<6;i++)
       playerObj[i]=new playerInfo();
       //System.out.print(gServer+" ");
        Thread threadForGrafix=new Thread(gServer);
        gServer.setSize(700, 700);
        gServer.setResizable(false);
        threadForGrafix.start();
        gServer.setVisible(true);
       //flagInfo o=new flagInfo();
        
        
        //////scoreboard
        
       stat=new status(this);
       stat.setSize(300,750);
       stat.setResizable(false);
       Thread ss=new Thread(stat);
       ss.start();
       stat.setLocation(720, 0);
       stat.setVisible(true);
      
        
        
      try{
                child = Runtime.getRuntime().exec("bot1.exe");
		//System.out.print(child);
               
                ServerSocket sock=new ServerSocket(1155);

		sct=sock.accept();
		//bt=new Thread
                if(sct!=null)
                {
                botThread bt[]=new botThread[6];
		bt[0]=new botThread(sct,0,this);
		//bt[i] = new botThread;
		gServer.addBot(playerObj[0]);
		//bt=new Thread();
		Thread bi[]=new Thread[6];
                bi[0]=new Thread(bt[0]);
		 
		
		
		 child1 = Runtime.getRuntime().exec("bot2.exe");
		//System.out.print(child1);
               // System.out.print("\n");
		
                sc5=sock.accept();
		//bt=new Thread
		if(sc5!=null)
                {
                bt[1]=new botThread(sc5,1,this);
		//bt[i] = new botThread;
		gServer.addBot(playerObj[1]);
		//bt=new Thread();
		bi[1]=new Thread(bt[1]);
		//bi[1].setPriority(5);
		 
		
		child2 = Runtime.getRuntime().exec("bot3.exe");
		//System.out.print(child2);
              //  System.out.print("\n");
		
		
		sc1=sock.accept();
		//bt=new Thread
		if(sc1!=null)
                {
                bt[2]=new botThread(sc1,2,this);
		//bt[i] = new botThread;
		gServer.addBot(playerObj[2]);
		//bt=new Thread();
		bi[2]=new Thread(bt[2]);
		//bi[1].setPriority(4);
		
		
		
		 child3 = Runtime.getRuntime().exec("bot4.exe");
		//System.out.print(child3);
             //   System.out.print("\n");
		
		
		sc2=sock.accept();
		//bt=new Thread
		if(sc2!=null)
                {
                bt[3]=new botThread(sc2,3,this);
		//bt[i] = new botThread;
		gServer.addBot(playerObj[3]);
		//bt=new Thread();
		bi[3]=new Thread(bt[3]);
		//bx.setPriority(3);
		
		
		child4 = Runtime.getRuntime().exec("bot5.exe");
		//System.out.print(child4);
              //  System.out.print("\n");
		
		
		sc3=sock.accept();
		//bt=new Thread
		if(sc3!=null)
                {
                bt[4]=new botThread(sc3,4,this);
		//bt[i] = new botThread;
		gServer.addBot(playerObj[4]);
		//bt=new Thread();
		bi[4]=new Thread(bt[4]);
		//bx.setPriority(2);
		
		
		
		 child5 = Runtime.getRuntime().exec("bot6.exe");
		//System.out.print(child5);
              //  System.out.print("\n");
		
		sc4=sock.accept();
		//bt=new Thread
		if(sc4!=null)
                {
                bt[5]=new botThread(sc4,5,this);
		//bt[i] = new botThread;
		gServer.addBot(playerObj[5]);
		//bt=new Thread();
		bi[5]=new Thread(bt[5]);
		//bx.setPriority(1);
		
               
               
               	bi[0].start();	
		bi[3].start();
		bi[1].start();	
                bi[4].start();		
                bi[2].start();		
                bi[5].start();		
                }
                }
                }
                }
                }
                }
                
      }catch(Exception e){System.out.print(e);}
       
    }
    

}
