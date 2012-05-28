#include"apis2.h"
#include<iostream>
#include<math.h>
#define RAD(x) 180.0*7*x/22
using namespace std;

int check_boundary(int current_x, int current_y)
{
        if(current_x<1 || current_x>697 || current_y<1 || current_y>697)
        return (0);
}



int main()
{
init("Th3_kiLl3R");
int bombcount=100;
int init_x, init_y, count=0;
while(1)
{
	int tid,pid,ti,pi,x,y,itaken,myx,myy,im,ex[3],ey[3],hel,flx,fly,val_bound,x_my,y_my,itaken_my,ti_my,pi_my;
	double distance,xdis,ydis;
	char msg[20];
    double ang,ang1,ang2;
    myflaginfo(&x_my,&y_my,&itaken_my,&ti_my,&pi_my);
    if(count==0)
    {
     init_x=x_my;
     init_y=y_my;
     count++;
    }
	enemyflaginfo(&x,&y,&itaken,&ti,&pi);
	statusinfo(ex,ey,&myx,&myy,&im,&hel,msg);
	getmyids(&tid,&pid);
        /***********************Checking the bot for hit on Boundary ******************/
        val_bound=check_boundary(myx,myy);
        if(val_bound==0)
        {
                //move towards enemy flag
                ang=atan2(y-myy,x-myx);//enemy flag trace
                for(int i=0;i<3;i++)
                move(RAD(ang),1);
        }

	/************************Default movement towards Enemy Flag *******************/
	if(itaken!=1) //If nobody has taken flag, move towards flag
	{
		ang=atan2(y-myy,x-myx);//enemy flag trace
		for(int i=0;i<3;i++)
	        move(RAD(ang),1);
	} 
	else if(itaken!=1 && itaken_my==1)//enemy flag is there but ours is gone
	{
         if(ti_my!=tid)
         {
             ang=atan2(y_my-myy,x_my-myx);
             for(int i=0; i<10 ;i++)
             move(RAD(ang),1);
         }
         else if(pi_my!=pid)
         {
             ang=atan2(y-myy,x-myx);
             for(int i=0; i<10 ;i++)
             move(RAD(ang),1);                 
         }
         else if(pi_my==pid)
         {
             ang=atan2(init_y-myy,init_x-myx); 
             for(int i=0; i<10 ;i++)
             move(RAD(ang),1);       
         }
    }  	
	else if(itaken==1 && pi==pid && ti==tid)//If yourself carrying it
    {
            /*****If flag is with the same bot, deposit it********/
            flagdc(&flx,&fly);
            sendmsg(3, "go");
            sendmsg(1, "go");
            ang2=atan2(fly-myy,flx-myx);
            for(int i=0; i<10 ;i++)            
            move((int)(RAD(ang2)),1);//Moving towards deposition location
    }
	else if(itaken==1 && pi!=pid && ti==tid)//If some other team member carrying it
	{
                //Check Whether ur flag is intact. If yes, move towards closest enemy else
                //move towards enemy with flag
               // myflaginfo(&x,&y,&itaken,&ti,&pi);
                if(itaken_my==1 &&ti_my!=tid)//If enemy taking our flag
                {
                        ang=atan2(y_my-myy,x_my-myx);//Own flag trace
                        for(int i=0; i<4 ;i++)
                        move(RAD(ang),1);
                }
                else
                {
                        ang= atan2(y_my-myy, x_my-myx);
                        xdis= x_my-myx;
                        ydis= y_my-myy;
                        distance= pow((xdis*xdis+ydis*ydis),0.5);
                        if(distance<30)
                        {
                                if(tid==1)                                 
                                move(270,0);
                                else
                                move(90,0);
                        }
                        else
                        {
                            for(int i=0; i<10 ;i++)
                                move(RAD(ang),1);
                        }

                }
	} 
	else if(itaken==1 && ti!=tid)//If other team carrying their own flag
	{
         if(pi_my==pid && ti_my==tid && itaken_my==1)
         {
             ang=atan2(init_y-myy,init_x-myx);
             for(int i=0; i<10 ;i++) 
             move(RAD(ang),1);       
         }
         else if(ti_my!=tid && itaken_my==1)
         {
             ang=atan2(y-myy,x-myx);//enemy flag trace
             for(int i=0;i<4;i++)
             move(RAD(ang),1);
         }
         else
         {
             ang=atan2(y-myy,x-myx);//enemy flag trace
             for(int i=0;i<4;i++)
             move(RAD(ang),1);             
         }         
	}       
        /*else if(strcmp(msg,"help")==0)
        {
             ang=atan2(y-myy,x-myx);//run towards enemy flag for help
               xdis= x-myx;
             ydis= y-myy;
    	     distance= pow((xdis*xdis+ydis*ydis),0.5);
    	     if(distance>50)
    	     move(RAD(ang),1);
    	     else 
    	     move(RAD(ang)+180,0);
        }                 
        else if(strcmp(msg,"go")==0)
        {
             myflaginfo(&x,&y,&itaken,&ti,&pi);
             ang=atan2(y-myy,x-myx);//Own flag trace
             move(RAD(ang),1);
        } */       


	/***********************************************************************************/
	/******************************If some enemy detected in between *******************/
	if(ex[0]!=999)
	{
		ang=atan2(ey[0]-myy,ex[0]-myx);
		move(RAD(ang),0); //Turn towards enemy to fire
                xdis= ex[0]-myx;
                ydis= ey[0]-myy;
                distance= pow((xdis*xdis+ydis*ydis),0.5);
		if(bombcount>0)
		{
	                fire((int)distance,2); //Fire at Enemy Head eactly with bomb
			bombcount--;
		}
		else
			fire(100,1);
		move(RAD(ang)+90,1); //to move sideways in case enemy is detected
	}
	/**********************************************************************************/	
}
} 
