/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package botmania;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;
import java.applet.*;
import java.lang.*;

import java.io.*;
/**
 *
 * @author avijit
 */
public class botThread implements Runnable{

    Socket sock;
    int playerId;
   
                                        
    static int count1=0,count2=0;
    startGame s;
    public botThread(startGame st)
    {
        
        s=st;
    }
    
    public botThread(Socket sct, int uid,startGame st)
    {
        
        sock=sct;
        playerId=uid;
        s=st;
    }
     public void move()
     {
         
    }
     
    public void run()
    {
        
       
    
        int ch=0;
        try{		
		OutputStreamWriter out=new OutputStreamWriter(sock.getOutputStream());	
		InputStream in=sock.getInputStream();		
               // System.out.print(s);
             //   errorPage err=new errorPage("here");
      //
                while(ch!=-1)
                {     
                        s.playerObj[playerId].packet="";
			while((ch=in.read())!='$' && ch!=-1)
			{
				s.playerObj[playerId].packet+=(char)ch;	
			}
                       // err.setVisible(true);
                        String str;
                        str=s.playerObj[playerId].packet;
                        //if(str.length()==0)break;
                        
                        char api='P';
                        try{
                        api=str.charAt(0);
                        }catch(Exception e){System.out.print("Im in here");}
                        //if(ch==-1)System.out.print("\n-1\n");
                       //System.out.print(api+" "+playerId+"\n");
                       
                       switch(api)
                       {
                           
                           
                           
                           /////// For Initialization of Bots
                           case 'I' : 
                                        s.playerObj[playerId].name=str.substring(1,21);
					s.playerObj[playerId].socket=sock;
					//System.out.print(playerId+"  ");
					if(playerId==0)
                                        {
                                           
                                        s.playerObj[playerId].teamid=1;
					s.playerObj[playerId].playerid=1;
                                        s.ref_k++;
                                        }
                                        
                                        if(playerId==1)
                                        {
                                        s.playerObj[playerId].teamid=1;
					s.playerObj[playerId].playerid=2;
                                        s.ref_k++;
                                        }
                                        
                                        if(playerId==2)
                                        {
                                        s.playerObj[playerId].teamid=1;
					s.playerObj[playerId].playerid=3;
                                        s.ref_k++;
                                        }
                                        
                                        if(playerId==3)
                                        {
                                        s.playerObj[playerId].teamid=2;
					s.playerObj[playerId].playerid=1;
                                        s.ref_k++;
                                        }
                                        
                                        if(playerId==4)
                                        {
                                        s.playerObj[playerId].teamid=2;
					s.playerObj[playerId].playerid=2;
                                        s.ref_k++;
                                        }
                                        
                                        if(playerId==5)
                                        {
                                        s.playerObj[playerId].teamid=2;
					s.playerObj[playerId].playerid=3;
                                        s.ref_k++;
                                        }
                                        
                                        try{
                                        if(s.ref_k<6)
                                        {
                                            System.out.print("Wait\n");
                                            s.playerObj[playerId].wait();
                                        }
                                        if(s.ref_k==6)
                                        {
                                       s.playerObj[0].notifyAll();
                                       s.playerObj[1].notifyAll();
                                       s.playerObj[2].notifyAll();
                                       s.playerObj[3].notifyAll();
                                       s.playerObj[4].notifyAll();
                                       s.playerObj[5].notifyAll();
                                       
                                        }
                                        }catch(Exception e){}
                                        
                                        System.out.print("Rel\n");
                                        s.playerObj[playerId].health=100;		//initilising health
					s.playerObj[playerId].ownflagcount=0;	//initilising ownflagcount
					s.playerObj[playerId].enemyflagcount=0;	//initilising enemyflagcount
					s.playerObj[playerId].unread=0;
					s.playerObj[playerId].messagesender=0;
					s.playerObj[playerId].nobombs=100;
					s.playerObj[playerId].hasfired=0;
					for(int hh=0;hh<4;hh++)
					s.playerObj[playerId].fired[hh]=999;
					s.playerObj[playerId].fired[4]=0;
					s.playerObj[playerId].myflagcount=0;
                                        s.playerObj[playerId].enemyflagcount=0;
					int ii;
					for(ii=0;ii<20;ii++)
					{
						s.playerObj[playerId].message+=' ';
						
					}					
	
				
					
					if(s.playerObj[playerId].teamid==1 && s.playerObj[playerId].playerid==1)
					{
                                            s.playerObj[playerId].myx=35;
                                            s.playerObj[playerId].myy=665;
                                            s.playerObj[playerId].movedir=315;
                                            
                                        }

					if(s.playerObj[playerId].teamid==1 && s.playerObj[playerId].playerid==2)
					{
                                            s.playerObj[playerId].myx=350;
                                            s.playerObj[playerId].myy=630;
                                            s.playerObj[playerId].movedir=270;
					}
				
					if(s.playerObj[playerId].teamid==1 && s.playerObj[playerId].playerid==3)
					{
                                            s.playerObj[playerId].myx=665;
                                            s.playerObj[playerId].myy=665;
                                            s.playerObj[playerId].movedir=225;
					}

                                        ///confirm 
                                        
					if(s.playerObj[playerId].teamid==2 && s.playerObj[playerId].playerid==3)
					{
                                            s.playerObj[playerId].myx=665;
                                            s.playerObj[playerId].myy=35;
                                            s.playerObj[playerId].movedir=135;
					}
			
					if(s.playerObj[playerId].teamid==2 && s.playerObj[playerId].playerid==2)
					{
                                            s.playerObj[playerId].myx=350;
                                            s.playerObj[playerId].myy=70;
                                            s.playerObj[playerId].movedir=90;
					}

					if(s.playerObj[playerId].teamid==2 && s.playerObj[playerId].playerid==1)
					{
                                            s.playerObj[playerId].myx=35;
                                            s.playerObj[playerId].myy=35;
                                            s.playerObj[playerId].movedir=45;
					}
                                        s.playerObj[playerId].blocked=0;
					s.playerObj[playerId].score=0;	//initilising score
					s.playerObj[playerId].ismoving=0;	//initilising ismoving
					s.playerObj[playerId].isdead=0;	//initilising isdead
                                         
                                        out.write('Y');	
					out.flush();
                                        
                                        
                                        
                                        /*
                                        if(playerId==0)
					{	
                                                s.str1=s.playerObj[playerId].name;
						s.playerObj[playerId].teamid=1;
						s.playerObj[playerId].playerid=1;
                                                s.ref_k=1;
                                                notifyAll();
								
					}	
                                        
                                        if(s.ref_k==0 && playerId!=0)
                                        {
                                            wait();
                                        }
					 if(s.ref_k==1)
					{
						if(s.playerObj[playerId].name.equals(s.str1))
						{ 
							count1++;
							s.playerObj[playerId].teamid=1;
							s.playerObj[playerId].playerid=count1+1;
						}
												
                                            else if(s.ref_k==1)
						{ 
							count2++;
							s.playerObj[playerId].teamid=2;			
							s.playerObj[playerId].playerid=count2;						
						}
					
					
                                        
                                        
					s.playerObj[playerId].health=100;		//initilising health
					s.playerObj[playerId].ownflagcount=0;	//initilising ownflagcount
					s.playerObj[playerId].enemyflagcount=0;	//initilising enemyflagcount
					s.playerObj[playerId].unread=0;
					s.playerObj[playerId].messagesender=0;
					s.playerObj[playerId].nobombs=100;
					s.playerObj[playerId].hasfired=0;
					for(int hh=0;hh<4;hh++)
					s.playerObj[playerId].fired[hh]=999;
					s.playerObj[playerId].fired[4]=0;
					s.playerObj[playerId].myflagcount=0;
                                        s.playerObj[playerId].enemyflagcount=0;
					int ii;
					for(ii=0;ii<20;ii++)
					{
						s.playerObj[playerId].message+=' ';
						
					}					
	
				
					
					if(s.playerObj[playerId].teamid==1 && s.playerObj[playerId].playerid==1)
					{
                                            s.playerObj[playerId].myx=35;
                                            s.playerObj[playerId].myy=665;
                                            s.playerObj[playerId].movedir=315;
                                            
                                        }

					if(s.playerObj[playerId].teamid==1 && s.playerObj[playerId].playerid==2)
					{
                                            s.playerObj[playerId].myx=350;
                                            s.playerObj[playerId].myy=630;
                                            s.playerObj[playerId].movedir=270;
					}
				
					if(s.playerObj[playerId].teamid==1 && s.playerObj[playerId].playerid==3)
					{
                                            s.playerObj[playerId].myx=665;
                                            s.playerObj[playerId].myy=665;
                                            s.playerObj[playerId].movedir=225;
					}

                                        ///confirm 
                                        
					if(s.playerObj[playerId].teamid==2 && s.playerObj[playerId].playerid==3)
					{
                                            s.playerObj[playerId].myx=665;
                                            s.playerObj[playerId].myy=35;
                                            s.playerObj[playerId].movedir=135;
					}
			
					if(s.playerObj[playerId].teamid==2 && s.playerObj[playerId].playerid==2)
					{
                                            s.playerObj[playerId].myx=350;
                                            s.playerObj[playerId].myy=70;
                                            s.playerObj[playerId].movedir=90;
					}

					if(s.playerObj[playerId].teamid==2 && s.playerObj[playerId].playerid==1)
					{
                                            s.playerObj[playerId].myx=35;
                                            s.playerObj[playerId].myy=35;
                                            s.playerObj[playerId].movedir=45;
					}
                                        s.playerObj[playerId].blocked=0;
					s.playerObj[playerId].score=0;	//initilising score
					s.playerObj[playerId].ismoving=0;	//initilising ismoving
					s.playerObj[playerId].isdead=0;	//initilising isdead
                                         }
                                        out.write('Y');	
					out.flush();
                                         * */
					//System.out.print(" II "+playerId+" "+s.playerObj[playerId].teamid+ " "+s.playerObj[playerId].playerid+" " + s.playerObj[playerId].myx + " "+ s.playerObj[playerId].myy+"\n");
                                        /*for(int ty=0;ty<100;ty++)
                                        System.out.print("Init");*/
                                    //    s.th[playerId]++;
                        
                                        break;		
                       
                       
                           case 'M':
                                                String k;	
						k=str.substring(2,5);
						int ismov=str.charAt(6)-'0';
						int angle=(Integer.parseInt(k))%360;
                                                s.playerObj[playerId].ismoving=ismov;
                                                s.playerObj[playerId].movedir=angle;
                                                double ang=(Math.PI*angle*1.0/180.0);
                                                
                                              //  System.out.print("ang="+angle+" ");
                                                double ex=0,ey=0,tx,ty;
                                                tx=s.playerObj[playerId].myx+Math.cos(ang);
                                                ty=s.playerObj[playerId].myy+Math.sin(ang);
                                                if(angle>=0 && angle <90)
                                                {
                                                    ex=tx+25;
                                                    ey=ty+13;
                                                }
                                                if(angle>=90 && angle <180)
                                                {
                                                    ex=tx;
                                                    ey=ty+13;
                                                }
                                                if(angle>=180 && angle <270)
                                                {
                                                    ex=tx;
                                                    ey=ty+13;
                                                }
                                                if(angle>=270 && angle <360)
                                                {
                                                    ex=tx+25;
                                                    ey=ty+13;
                                                }
                                                
                                                if(ismov==1 && (s.playerObj[playerId].myx+Math.cos(ang))>0 && s.playerObj[playerId].myx+Math.cos(ang)<=670 && s.playerObj[playerId].myy+Math.sin(ang)>=30 && s.playerObj[playerId].myy+Math.sin(ang)<=670 )
						{
                                                    if(s.playerObj[playerId].blocked==0)				
                                                    {
                                                       // System.out.print("yeppie");
                                                        s.playerObj[playerId].myx+=Math.cos(ang);
                                                        s.playerObj[playerId].myy+=Math.sin(ang);
                                                    }
                                                    else  s.playerObj[playerId].health-=1;
                                                //System.out.print("\n"+s.playerObj[playerId].myx+"   "+s.playerObj[playerId].myy+" "+ang+" ");
						}
      if(ismov==1 && (s.playerObj[playerId].myx+Math.cos(ang)<=0 || s.playerObj[playerId].myx+Math.cos(ang)>670 || s.playerObj[playerId].myy+Math.sin(ang)<30 || s.playerObj[playerId].myy+Math.sin(ang)>670 )) 
      {
          s.playerObj[playerId].health-=1;
         
      }
 //System.out.print(s.playerObj[playerId].myx+" "+s.playerObj[playerId].myy+"\n");
                                   //////////////////////The following checks for flags
                                                
//////// Nearness to my own flag

/////////////picking of team 1 flag
if(Math.abs(s.playerObj[playerId].myx-s.teamObj.x)<10 && Math.abs(s.playerObj[playerId].myy-s.teamObj.y)<10)
{
    
    
    
    
    ////// increment in score
    //int hh=0;
    if((s.teamObj.istaken==0 && s.playerObj[playerId].teamid==2))
    {
        s.playerObj[playerId].score+=300;
        System.out.print("\nFLAG 1 Taken by tid= "+s.playerObj[playerId].teamid+" PlayerId= "+s.playerObj[playerId].playerid);
        
    }
    
     
    
    s.teamObj.istaken=1;
    s.teamObj.x=s.playerObj[playerId].myx;
    s.teamObj.y=s.playerObj[playerId].myy;
    s.teamObj.pid=s.playerObj[playerId].playerid;
    s.teamObj.tid=s.playerObj[playerId].teamid;
   //if(hh==0) System.out.print("\nTaken by tid= "+s.playerObj[playerId].teamid+"PlayerId= "+s.playerObj[playerId].playerid);
    
    
    
    
    if(s.playerObj[playerId].health<=0)
    {
        s.teamObj.istaken=0;
        s.teamObj.pid=0;
        s.teamObj.tid=0;
    }
 
 }						

                                                
  ////// picking of team 2 flag                                              
if(Math.abs(s.playerObj[playerId].myx-s.enemyObj.x)<10 && Math.abs(s.playerObj[playerId].myy-s.enemyObj.y)<10)
{
    if((s.enemyObj.istaken==0 && s.playerObj[playerId].teamid==1))
    {
        s.playerObj[playerId].score+=300;
        System.out.print("\nFLAG 2 Taken by tid= "+s.playerObj[playerId].teamid+" PlayerId= "+s.playerObj[playerId].playerid);
        
    }
    
    s.enemyObj.istaken=1;
    s.enemyObj.x=s.playerObj[playerId].myx;
    s.enemyObj.y=s.playerObj[playerId].myy;
    s.enemyObj.pid=s.playerObj[playerId].playerid;
    s.enemyObj.tid=s.playerObj[playerId].teamid;
   
    if(s.playerObj[playerId].health<=0)
    {
        s.enemyObj.istaken=0;
        s.enemyObj.pid=0;
        s.enemyObj.tid=0;
    }
    
}	



if(Math.abs(s.teamObj.x-35)<10 && Math.abs(s.teamObj.y-35)<10 && s.teamObj.ofteam!=s.teamObj.tid && s.teamObj.tid==s.playerObj[playerId].teamid && s.teamObj.pid==s.playerObj[playerId].playerid)
{
    //System.out.print("TEAM Initial "+s.playerObj[playerId].score+" ");
    s.playerObj[(s.teamObj.tid-1)*3+(s.teamObj.pid-1)].score+=700;
    s.playerObj[(s.teamObj.tid-1)*3+(s.teamObj.pid-1)].eflagcount++;
   
    //System.out.print("Final "+s.playerObj[playerId].score+" "+playerId+"\n");
    s.teamObj.istaken=0;
    s.teamObj.x=350;
    s.teamObj.y=665;
    s.teamObj.pid=0;
    s.teamObj.tid=0;
     //System.out.print(s.playerObj[playerId].eflagcount+" A ");
}
//System.out.print(s.enemyObj.x+" "+s.enemyObj.y+"\n");
if(Math.abs(s.enemyObj.x-665)<10 && Math.abs(s.enemyObj.y-665)<10 && s.enemyObj.ofteam!=s.enemyObj.tid && s.enemyObj.tid==s.playerObj[playerId].teamid && s.enemyObj.pid==s.playerObj[playerId].playerid)
{
//System.out.print("DEPOSITED s.enemyObj");
    //Thread.sleep(1);
     //System.out.print("ENEMY Initial "+.score+" ");
    s.playerObj[(s.enemyObj.tid-1)*3+(s.enemyObj.pid-1)].score+=700;
   // System.out.print("Final "+s.playerObj[playerId].score+" "+playerId+"\n");
    s.playerObj[(s.enemyObj.tid-1)*3+(s.enemyObj.pid-1)].eflagcount++;
    
    s.enemyObj.istaken=0;
    s.enemyObj.x=350;
    s.enemyObj.y=35;
    s.enemyObj.pid=0;
    s.enemyObj.tid=0;
   // System.out.print(s.playerObj[playerId].eflagcount+" ");
}



if(Math.abs(s.teamObj.x-35)<10 && Math.abs(s.teamObj.y-35)<10 && s.teamObj.ofteam==s.teamObj.tid)
{
    s.playerObj[4].score+=700;
    s.teamObj.istaken=0;
    s.teamObj.x=350;
    s.teamObj.y=665;
    s.teamObj.pid=0;
    s.teamObj.tid=0;
     s.playerObj[4].eflagcount++;
}

if(Math.abs(s.enemyObj.x-665)<10 && Math.abs(s.enemyObj.y-665)<10 && s.enemyObj.ofteam==s.enemyObj.tid)
{
    s.playerObj[1].score+=700;
    s.enemyObj.istaken=0;
    s.enemyObj.x=350;
    s.enemyObj.y=35;
    s.enemyObj.pid=0;
    s.enemyObj.tid=0;
     s.playerObj[1].eflagcount++;
}

if(s.playerObj[playerId].health<=0)
{





					s.playerObj[playerId].isdead=1;
                                        s.th[playerId]++;
					s.playerObj[playerId].score-=50;
					s.playerObj[playerId].health=100;		//initilising health
					s.playerObj[playerId].ownflagcount=0;	//initilising ownflagcount
					s.playerObj[playerId].enemyflagcount=0;	//initilising enemyflagcount
					s.playerObj[playerId].unread=0;
					s.playerObj[playerId].messagesender=0;
					s.playerObj[playerId].nobombs=100;
					s.playerObj[playerId].hasfired=0;
					for(int hh=0;hh<4;hh++)
					s.playerObj[playerId].fired[hh]=150;
					s.playerObj[playerId].fired[4]=0;
                                        
                                        if(s.teamObj.pid==s.playerObj[playerId].playerid && s.teamObj.tid==s.playerObj[playerId].teamid)
					{
						s.teamObj.pid=0;
						s.teamObj.tid=0;
						s.teamObj.istaken=0;
					}

					if(s.enemyObj.pid==s.playerObj[playerId].playerid && s.enemyObj.tid==s.playerObj[playerId].teamid)
					{
						s.enemyObj.pid=0;
						s.enemyObj.tid=0;
						s.enemyObj.istaken=0;
					}
					
					//int ii;
					for(ii=0;ii<20;ii++)
					{
						s.playerObj[playerId].message+=' ';
						
					}					
				
				if(s.playerObj[playerId].teamid==1 && s.playerObj[playerId].playerid==1)
					{
					s.playerObj[playerId].myx=35;
					s.playerObj[playerId].myy=665;
					s.playerObj[playerId].movedir=315;
					}

					if(s.playerObj[playerId].teamid==1 && s.playerObj[playerId].playerid==2)
					{
					s.playerObj[playerId].myx=350;
					s.playerObj[playerId].myy=630;
					s.playerObj[playerId].movedir=270;
					}
				
					if(s.playerObj[playerId].teamid==1 && s.playerObj[playerId].playerid==3)
					{
					s.playerObj[playerId].myx=665;
					s.playerObj[playerId].myy=665;
					s.playerObj[playerId].movedir=225;
					}

					if(s.playerObj[playerId].teamid==2 && s.playerObj[playerId].playerid==3)
					{
					s.playerObj[playerId].myx=665;
					s.playerObj[playerId].myy=35;
					s.playerObj[playerId].movedir=135;
					}
			
					if(s.playerObj[playerId].teamid==2 && s.playerObj[playerId].playerid==2)
					{
					s.playerObj[playerId].myx=350;
					s.playerObj[playerId].myy=70;
					s.playerObj[playerId].movedir=90;
					}

					if(s.playerObj[playerId].teamid==2 && s.playerObj[playerId].playerid==1)
					{
					s.playerObj[playerId].myx=35;
					s.playerObj[playerId].myy=35;
					s.playerObj[playerId].movedir=45;
					}
				
				
					s.playerObj[playerId].ismoving=0;	//initilising ismoving
					s.playerObj[playerId].isdead=0;	//initilising isdead
                                         s.playerObj[playerId].blocked=0;
                                         s.playerObj[playerId].img=1;
					
				






}





if(s.teamObj.istaken==1 && s.teamObj.pid==s.playerObj[playerId].playerid && s.teamObj.tid==s.playerObj[playerId].teamid)
{
s.teamObj.x=s.playerObj[playerId].myx;
s.teamObj.y=s.playerObj[playerId].myy;
}
if(s.enemyObj.istaken==1 && s.enemyObj.pid==s.playerObj[playerId].playerid && s.enemyObj.tid==s.playerObj[playerId].teamid)
{
s.enemyObj.x=s.playerObj[playerId].myx;
s.enemyObj.y=s.playerObj[playerId].myy;
}			         
                                               // s.th[playerId]++;
                        
                                                out.write('Y');	
						out.flush();					
						break;
                          
                                                ////////////////////////////
                                                
                                                
                                                
                                                
                               
                             
                                        
                                 /////// For Flag Deposition Coordinate
                                        
                          case 'X': if(s.playerObj[playerId].teamid==1)out.write("665665");
                                    else out.write("035035");	
                                    out.flush();
                                    break;
                          
                                    
                                    ////// For Message Sending
                          case 'S':	String mm;
					mm=str.substring(1,21);
				
					int kk;
					kk=str.charAt(21)-'0';	
					
					if(s.playerObj[playerId].teamid==1)
					{	
						s.playerObj[kk-1].message=mm;			
						s.playerObj[kk-1].messagesender=s.playerObj[playerId].playerid;
						//System.out.print(s.playerObj[kk-1].message+" "+kk);
					}			
					if(s.playerObj[playerId].teamid==2)
					{
						s.playerObj[kk+2].message=mm;			
						s.playerObj[kk+2].messagesender=s.playerObj[playerId].playerid;
						//System.out.print(s.playerObj[kk+2].message+" "+kk);
					}
					//s.th[playerId]++;
                        
						out.write('Y');	
						out.flush();					
						break;
                                 ///// Team id & playerid           	
                                 case 'D':out.write(""+s.playerObj[playerId].teamid+s.playerObj[playerId].playerid);
				 out.flush();
                                // s.th[playerId]++;
                        
				 break;

                                 
                                 
                                 case 'Q':	if(s.teamObj.ofteam==s.playerObj[playerId].teamid)
					{
					  String strx="";
					  String stry="";
					  int xx,yy;
					  xx=(int)s.teamObj.x;
				          yy=(int)s.teamObj.y;
                                          
					if(xx<10)strx+="00";			  
					if(xx<=99 && xx>=10)strx+="0";
					strx+=xx;			
					
					if(yy<10)stry+="00";			  
					if(yy<=99 && yy>=10)stry+="0";
					stry+=yy;
					out.write(strx+stry+s.teamObj.istaken+s.teamObj.tid+s.teamObj.pid);			
					out.flush();
					}
					
					else if(s.enemyObj.ofteam==s.playerObj[playerId].teamid)
					{
                                              String strx="";
                                              String stry="";
                                              int xx,yy;
                                              xx=(int)s.enemyObj.x;
                                              yy=(int)s.enemyObj.y;
                                            if(xx<10)strx+="00";			  
                                            if(xx<=99 && xx>=10)strx+="0";
                                            strx+=xx;			

                                            if(yy<10)stry+="00";			  
                                            if(yy<=99 && yy>=10)stry+="0";
                                            stry+=yy;
                                            out.write(strx+stry+s.enemyObj.istaken+s.enemyObj.tid+s.enemyObj.pid);	
                                            out.flush();		
					}
                                  //s.th[playerId]++;
                        
					break;
		
			case 'T':	
                                         
                                        if(s.teamObj.ofteam!=s.playerObj[playerId].teamid)
					{
					   String strx="";
					  String stry="";
					  int xx,yy;
                                      
					  xx=(int)s.teamObj.x;
				          yy=(int)s.teamObj.y;
                                           //   System.out.print(xx+" "+yy+"\n");
					if(xx<10)strx+="00";			  
					if(xx<=99 && xx>=10)strx+="0";
					strx+=xx;			
					
					if(yy<10)stry+="00";			  
					if(yy<=99 && yy>=10)stry+="0";
					stry+=yy;
					out.write(strx+stry+s.teamObj.istaken+s.teamObj.tid+s.teamObj.pid);			
					out.flush();
					}
					
					else if(s.enemyObj.ofteam!=s.playerObj[playerId].teamid)
					{
					  String strx="";
					  String stry="";
					  int xx,yy;
					  xx=(int)s.enemyObj.x;
				          yy=(int)s.enemyObj.y;
                                          
					
					if(xx<10)strx+="00";			  
					if(xx<=99 && xx>=10)strx+="0";
					strx+=xx;			
					
					if(yy<10)stry+="00";			  
					if(yy<=99 && yy>=10)stry+="0";
					stry+=yy;
					out.write(strx+stry+s.enemyObj.istaken+s.enemyObj.tid+s.enemyObj.pid);
					
                                        out.flush();			
					}
                                       // System.out.print(strx+" "+stry+" ");
                                       // System.out.print(s.playerObj[playerId].movedir+"\n");
					//s.th[playerId]++;
                        
                                        break;
                                        
                      
                           
                           case 'F':      s.playerObj[playerId].range=100;
                                         try
                                         {
                                             s.playerObj[playerId].range=Integer.parseInt(str.substring(2,5));
                                         }
                                         catch(Exception e)
                                         {
                                             System.out.print("eee");
                                         }
                                         int type=str.charAt(6)-'0';
                                         
                                         int dmin=s.playerObj[playerId].range;
                                         double angr=(Math.PI*s.playerObj[playerId].movedir*1.0/180.0);
                            
                                         
                                         if(s.playerObj[playerId].nobombs>0 && type ==2)
                                         {
                                             s.playerObj[playerId].tofire=2;
                                             s.playerObj[playerId].nobombs--;

                                         }
                                        
                                         else 
                                         {
                                            s.playerObj[playerId].tofire=1;
                                            type = 1;
                                         }
                                         
                                         int r=s.playerObj[playerId].range;
                                         int fx,fy;
                                         ///// use fired array for walls
                                         
                                         int inx=(int)(s.playerObj[playerId].myx)+13;
                                         int iny=(int)(s.playerObj[playerId].myy)+13;;
                                         int length=15;
                                         int finx,finy;
                                         s.playerObj[playerId].fired[0]=(int)(inx+length*Math.cos(angr));
                                         s.playerObj[playerId].fired[1]=(int)(iny+length*Math.sin(angr));
                                         
                                         
                                         finx=(int)(inx+dmin*Math.cos(angr));
                                         finy=(int)(iny+dmin*Math.sin(angr));
                                         
                                         float scx=inx,scy=iny;
                                         int ct=0;
                                         //for(scx=inx;scx<)
                                        // System.out.print(inx+" "+iny+" "+finx+" "+finy+"\n");
                                              while(Math.abs(scx-finx)>1 && Math.abs(scy-finy)>1)
                                         {
                                            
                                             if((int)scx<700 && (int)scx>0 && (int)scy<700 && (int)scy >0 && s.arr[(int)scx][(int)scy]=='W')
                                             {
                                                 dmin=(int)Math.sqrt((scy-iny)*(scy-iny)+(scx-inx)*(scx-inx));
                                                 //System.out.print("WALL IS IN BETWEEN "+dmin);
                                                
                                                 break;
                                             }
                                                 
                                             else 
                                             {
                                                 scx+=Math.cos(angr);
                                                 scy+=Math.sin(angr);
                                                 
                                             }
                                             
                                       // System.out.print(scx+" "+scy+ " "+finx+" "+finy+" "+s.arr[(int)scx][(int)scy]+"\n");
                                         }
                                       //System.out.print("\n\nout\n\n");
                                         int u=999;
                                         s.playerObj[playerId].fired[2]=(int)(inx+dmin*Math.cos(angr));
                                         s.playerObj[playerId].fired[3]=(int)(iny+dmin*Math.sin(angr));
                                       
                                         if(s.playerObj[playerId].teamid==1)
                                         {
                                            for(int m=3;m<6;m++)
                                            {    
                                                 if(Math.abs(s.playerObj[m].myx+13-s.playerObj[playerId].fired[2])<13 && Math.abs(s.playerObj[m].myy+13-s.playerObj[playerId].fired[3])<13 && type==1)
                                                {
                                                    s.playerObj[m].health-=2;
                                                    s.playerObj[playerId].score+=5;
                                                    ///// Lazer
                                                    u=m;
                                                }
                                            
                                                if(Math.abs(s.playerObj[m].myx+13-s.playerObj[playerId].fired[2])<13 && Math.abs(s.playerObj[m].myy+13-s.playerObj[playerId].fired[3])<13 && type==2)
                                                {
                                                    s.playerObj[m].health-=6;
                                                    s.playerObj[playerId].score+=10;
                                                    u=m;
                                                    ///// Bomb
                                                }
                                            }
                                         }
                                         
                                         else if(s.playerObj[playerId].teamid==2)
                                         {
                                            for(int m=0;m<3;m++)
                                            {    
                                                 if(Math.abs(s.playerObj[m].myx+13-s.playerObj[playerId].fired[2])<13 && Math.abs(s.playerObj[m].myy+13-s.playerObj[playerId].fired[3])<13 && type==1)
                                                {
                                                    s.playerObj[m].health-=2;
                                                    s.playerObj[playerId].score+=5;
                                                    ///// Lazer
                                                    u=m;
                                                }
                                            
                                                if(Math.abs(s.playerObj[m].myx+13-s.playerObj[playerId].fired[2])<13 && Math.abs(s.playerObj[m].myy+13-s.playerObj[playerId].fired[3])<13 && type==2)
                                                {
                                                    s.playerObj[m].health-=6;
                                                    s.playerObj[playerId].score+=10;
                                                    ///// Bomb
                                                    u=m;
                                                }
                                            }
                                         }
                                         
                                         s.playerObj[playerId].hasfired=1;
                                         
                                         if(u!=999)
                                         {
                                         ///////////////////
                                             
                                             if(s.playerObj[u].health<=0)
				{
                                        s.th[u]++;
                                        s.tar[playerId]++;
					s.playerObj[u].isdead=1;
					s.playerObj[u].score-=50;
					s.playerObj[u].health=100;		//initilising health
					s.playerObj[u].ownflagcount=0;	//initilising ownflagcount
					s.playerObj[u].enemyflagcount=0;	//initilising enemyflagcount
					s.playerObj[u].unread=0;
					s.playerObj[u].messagesender=0;
					s.playerObj[u].nobombs=100;
					s.playerObj[u].hasfired=0;
					for(int hh=0;hh<4;hh++)
					s.playerObj[u].fired[hh]=150;
					s.playerObj[u].fired[4]=0;
                                        
                                        
                                        
                                        if(s.teamObj.pid==s.playerObj[u].playerid && s.teamObj.tid==s.playerObj[u].teamid)
					{
						s.teamObj.pid=0;
						s.teamObj.tid=0;
						s.teamObj.istaken=0;
					}

					if(s.enemyObj.pid==s.playerObj[u].playerid && s.enemyObj.tid==s.playerObj[u].teamid)
					{
						s.enemyObj.pid=0;
						s.enemyObj.tid=0;
						s.enemyObj.istaken=0;
					}
					
					//int ii;
					for(ii=0;ii<20;ii++)
					{
						s.playerObj[u].message+=' ';
						
					}					
				
				if(s.playerObj[u].teamid==1 && s.playerObj[u].playerid==1)
					{
                                            s.playerObj[u].myx=35;
                                            s.playerObj[u].myy=665;
                                            s.playerObj[u].movedir=315;
					}

					if(s.playerObj[u].teamid==1 && s.playerObj[u].playerid==2)
					{
                                            s.playerObj[u].myx=350;
                                            s.playerObj[u].myy=630;
                                            s.playerObj[u].movedir=270;
					}
				
					if(s.playerObj[u].teamid==1 && s.playerObj[u].playerid==3)
					{
                                            s.playerObj[u].myx=665;
                                            s.playerObj[u].myy=665;
                                            s.playerObj[u].movedir=225;
					}

					if(s.playerObj[u].teamid==2 && s.playerObj[u].playerid==3)
					{
                                            s.playerObj[u].myx=665;
                                            s.playerObj[u].myy=35;
                                            s.playerObj[u].movedir=135;
					}
			
					if(s.playerObj[u].teamid==2 && s.playerObj[u].playerid==2)
					{
                                            s.playerObj[u].myx=350;
                                            s.playerObj[u].myy=70;
                                            s.playerObj[u].movedir=90;
					}

					if(s.playerObj[u].teamid==2 && s.playerObj[u].playerid==1)
					{
                                            s.playerObj[u].myx=35;
                                            s.playerObj[u].myy=35;
                                            s.playerObj[u].movedir=45;
					}
                                        
                                            s.playerObj[u].blocked=0;
                                            s.playerObj[u].ismoving=0;	//initilising ismoving
                                            s.playerObj[u].isdead=0;	//initilising isdead
                                            s.playerObj[u].img=1;
					
                                     }
				
				
                                        

                                             
                                             ///////////////////
                                         
                                         }
                                         
                                        // s.th[playerId]++;
                        
                                        out.write('Y');
                                        out.flush();
                                        
                                        break;
                       
                  case 'G':	                     
				double mx,my,tempx,tempy,dist;
				int i,ka=0;
				int kid=s.playerObj[playerId].teamid;
				int ecox[]=new int[3];				
				int ecoy[]=new int[3];				
				mx=s.playerObj[playerId].myx;
				my=s.playerObj[playerId].myy;
				for(i=0;i<3;i++)
				{
					ecox[i]=999;
					ecoy[i]=999;
				}
				for(i=0;i<6;i++)
				{
				if(s.playerObj[i].teamid!=kid && i!=playerId)
				{	
					tempx=s.playerObj[i].myx;
					tempy=s.playerObj[i].myy;
					
					
					dist=Math.sqrt(((tempx-mx)*(tempx-mx))+((tempy-my)*(tempy-my)));				
						if(dist<=100)
						{
							ecox[ka]=(int)tempx;
							ecoy[ka]=(int)tempy;
							ka++;
						}					
			


                                    }//if

				
                            }//for
		
					String s1="",s2="",s3="",s4="",s5="",s6="",s7="",s8="",s9="",s10="";
				try{		//saving in the stream
						
						//enemy coordinates						
						//if(ecox[0]==0)s1+="000";
						if(ecox[0]<10)s1+="00";
						if(ecox[0]>9 && ecox[0]<100)s1+="0";
						s1+=ecox[0];
						if(ecoy[0]<10)s2+="00";
						if(ecoy[0]>9 && ecoy[0]<100)s2+="0";
						s2+=ecoy[0];
						if(ecox[1]<10)s3+="00";
						if(ecox[1]>9 && ecox[1]<100)s3+="0";
						s3+=ecox[1];
						if(ecoy[1]<10)s4+="00";
						if(ecoy[1]>9 && ecoy[1]<100)s4+="0";
						s4+=ecoy[1];
						if(ecox[2]<10)s5+="00";
						if(ecox[2]>9 && ecox[2]<100)s5+="0";
						s5+=ecox[2];
						if(ecoy[2]<10)s6+="00";
						if(ecoy[2]>9 && ecoy[2]<100)s6+="0";
						s6+=ecoy[2];
						//my coordinates						
						if((int)s.playerObj[playerId].myx<10)s7+="00";
											
						if((int)s.playerObj[playerId].myx>9 && (int)s.playerObj[playerId].myx<100)s7+="0";
												
						s7+=(int)s.playerObj[playerId].myx;
						
						//System.out.print(s7+ " ");	
						if((int)s.playerObj[playerId].myy<10)s8+="00";
						if((int)s.playerObj[playerId].myy>9 && (int)s.playerObj[playerId].myy<100)s8+="0";
						s8+=(int)s.playerObj[playerId].myy;
						//health
						if((int)s.playerObj[playerId].health<10)s9+="00";
						if((int)s.playerObj[playerId].health>9 && s.playerObj[playerId].health<100)s9+="0";
						s9+=(int)s.playerObj[playerId].health;
						//message
						for(i=0;i<20;i++)
						{
                                                    if(i<s.playerObj[playerId].message.length())s10+=s.playerObj[playerId].message.charAt(i);
                                                    else s10+=' ';
						}
					
						out.write(s1+s3+s5+s2+s4+s6+s7+s8+s.playerObj[playerId].ismoving+s9+s10);
						out.flush();
					}catch(Exception e){System.out.print(s1+" "+s3+" "+s5+" "+s2+" "+s4+" "+s6+" "+s7+" "+s8+" "+s.playerObj[playerId].ismoving+" "+s9+" "+s10+"\n");}
                                       // s.th[playerId]++;
                        
                                        break;

                 case 'W'     :     double xx,yy,x1,y1,xx1,xx2,xx3,yy1,yy2,yy3;
                                    xx=s.playerObj[playerId].myx;
                                    yy=s.playerObj[playerId].myy;
                                    
                                    xx1=s.playerObj[playerId].myx+25;
                                    xx2=s.playerObj[playerId].myx+25;
                                    xx3=s.playerObj[playerId].myx;
                                    
                                    yy1=s.playerObj[playerId].myy;
                                    yy2=s.playerObj[playerId].myy+25;
                                    yy3=s.playerObj[playerId].myy+25;
                                    
                                    
                                    double a;
                                    a=(Math.PI*s.playerObj[playerId].movedir)/180.0;
                                    int scr=700;
                                    x1=s.playerObj[playerId].scan[0]=xx;
                                    y1=s.playerObj[playerId].scan[1]=yy;
                                    int flag=0;
                                    while(xx<700 && yy<700 && xx>0 && yy>0)
                                    {
                                      if(s.arr[(int)xx][(int)yy]=='W')
                                      {
                                          flag=1;
                                          break;
                                      }
                                      else 
                                      {
                                          xx+=Math.cos(a);
                                          yy+=Math.sin(a);
                                      }
                                    }
                                    double x2,y2;
                                    
                                    x2=s.playerObj[playerId].scan[0]=xx;
                                    y2=s.playerObj[playerId].scan[1]=yy;
                                    double d;
                                    d=Math.sqrt((y2-y1)*(y2-y1)+(x2-x1)*(x2-x1));
                                    int dis=(int)d;
                                    if(flag==0)dis=999;
                                    
                                    
                                    flag=0;
                                    
                                    x1=s.playerObj[playerId].scan[0]=xx1;
                                    y1=s.playerObj[playerId].scan[1]=yy1;
                                    while(xx1<700 && yy1<700 && xx1>0 && yy1>0)
                                    {
                                      if(s.arr[(int)xx1][(int)yy1]=='W')
                                      {
                                          flag=1;
                                          break;
                                      }
                                      else 
                                      {
                                          xx1+=Math.cos(a);
                                          yy1+=Math.sin(a);
                                      }
                                    }
                                    
                                    x2=s.playerObj[playerId].scan[0]=xx1;
                                    y2=s.playerObj[playerId].scan[1]=yy1;
                                    
                                    d=Math.sqrt((y2-y1)*(y2-y1)+(x2-x1)*(x2-x1));
                                    int dis1=(int)d;
                                    
                                    if(flag==0)
                                    dis1=999;
                                    
                                    
                                    flag=0;
                                    
                                    x1=s.playerObj[playerId].scan[0]=xx2;
                                    y1=s.playerObj[playerId].scan[1]=yy2;
                                    while(xx2<700 && yy2<700 && xx2>0 && yy2>0)
                                    {
                                      if(s.arr[(int)xx2][(int)yy2]=='W')
                                      {
                                          flag=1;
                                          break;
                                      }
                                      else 
                                      {
                                          xx2+=Math.cos(a);
                                          yy2+=Math.sin(a);
                                      }
                                    }
                                    
                                    x2=s.playerObj[playerId].scan[0]=xx2;
                                    y2=s.playerObj[playerId].scan[1]=yy2;
                                    
                                    d=Math.sqrt((y2-y1)*(y2-y1)+(x2-x1)*(x2-x1));
                                    int dis2=(int)d;
                                    
                                    
                                    if(flag==0)
                                    dis2=999;
                                    
                                    flag=0;
                                    
                                    x1=s.playerObj[playerId].scan[0]=xx3;
                                    y1=s.playerObj[playerId].scan[1]=yy3;
                                    while(xx3<700 && yy3<700 && xx3>0 && yy3>0)
                                    {
                                      if(s.arr[(int)xx3][(int)yy3]=='W')
                                      {
                                          flag=1;
                                          break;
                                      }
                                      else 
                                      {
                                          xx3+=Math.cos(a);
                                          yy3+=Math.sin(a);
                                      }
                                    }
                                    
                                    x2=s.playerObj[playerId].scan[0]=xx3;
                                    y2=s.playerObj[playerId].scan[1]=yy3;
                                    
                                    d=Math.sqrt((y2-y1)*(y2-y1)+(x2-x1)*(x2-x1));
                                    int dis3=(int)d;
                                    
                                    if(flag==0)
                                    dis3=999;
                                    
                                    int o=999;
                                    if(dis<o)
                                        o=dis;
                                    if(dis1<o)
                                        o=dis1;
                                    if(dis2<o) 
                                        o=dis2;
                                    if(dis3<o)
                                        o=dis3;
                                        
                                    
                                    String st="";
                                    if(o<10)st="00"+o;
                                    else if(o<100)st="0"+o;
                                    else st+=o;
                                    //s.th[playerId]++;
                        
                                    out.write(st);
                                    //System.out.print(st+" ");
                                    out.flush();
                                        
                       }
                 s.apiCount++;
                 Thread.sleep(3);
                }
        
        
        }catch(Exception e)
        {
            System.out.print("\n\n\n***\n\n\n\n\n\n\n");
            System.out.print(e);
            System.out.print("\n\n\n***\n\n\n\n\n\n\n");
            try{
            Process child1 = Runtime.getRuntime().exec("tskill bot1.exe");
            Process child2 = Runtime.getRuntime().exec("tskill bot2.exe");
            Process child3 = Runtime.getRuntime().exec("tskill bot3.exe");
            Process child4 = Runtime.getRuntime().exec("tskill bot4.exe");
            Process child5 = Runtime.getRuntime().exec("tskill bot5.exe");
            Process child6 = Runtime.getRuntime().exec("tskill bot6.exe");
            }catch(Exception ee){}

        }
        
    }
    
}
