#include"apis2.h"
#include<stdio.h>
#include<math.h>
#define RAD(x) 180.0*7*x/22
#define FIR 6


inline int getdist(int a, int b, int c, int d)
{return sqrt((a-c)*(a-c) + (b-d)*(b-d));    
}

int main ()
{
  init ("ACRush");             
  int tid, pid, ti, pi, x, y, xxx, yyy, itaken1, myx, myy, im, ex[3],ey[3], hel,temp,last;
  register int i, times;
  char msg[20];
  register double ang, ang1;
  getmyids (&ti, &pi);
  flagdc (&xxx, &yyy);


while(1)
{

statusinfo (ex, ey, &myx, &myy, &im, &hel, msg);
      

if (ex[1] != 999)               // =====================
	    {
	      while (ex[1] != 999)
		{
		  statusinfo (ex, ey, &myx, &myy, &im, &hel, msg);
		  move (RAD (atan2 (ey[1] - myy, ex[1] - myx)), 0);
		  times = FIR;
  temp=getdist(ex[1],ey[1],myx,myy);
		  while (times--)
		    fire (temp, 2);
		}
	    }
	
if (ex[2] != 999)
	    {
	      while (ex[2] != 999)
		{
		  statusinfo (ex, ey, &myx, &myy, &im, &hel, msg);
		  move (RAD (atan2 (ey[2] - myy, ex[2] - myx)), 0);
		  times = 6;
  temp=getdist(ex[2],ey[2],myx,myy);
		  while (times--)
		    fire (temp, 2);
		}
	    }

if (ex[0] != 999)
	    {
	      while (ex[0] != 999)
		{
		  statusinfo (ex, ey, &myx, &myy, &im, &hel, msg);
		  move (RAD (atan2 (ey[0] - myy, ex[0] - myx)), 0);
		  times = FIR;
  temp=getdist(ex[0],ey[0],myx,myy);
		  while (times--)
		    fire (temp, 2);
		}
	    }

}

}
