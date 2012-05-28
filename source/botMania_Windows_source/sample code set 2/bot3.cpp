#include"apis2.h"
#include<stdio.h>
#include<math.h>
#define RAD(x) 180.0*7*x/22


inline int getdist(int a, int b, int c, int d)
{return sqrt((a-c)*(a-c) + (b-d)*(b-d));    
}

int main ()
{
  init ("dormantsreloaded");
  int tid, pid, ti, pi, x, y, xxx, yyy, itaken1, myx, myy, im, ex[3],
    ey[3], hel,temp,last;
  register int i, times;
  char msg[20];
  register double ang, ang1;
  getmyids (&ti, &pi);
  flagdc (&xxx, &yyy);
  while (1)
    {
    label1:
      statusinfo (ex, ey, &myx, &myy, &im, &hel, msg);
      //   ang = atan2 (y - myy, x - myx);
     
      for (i = 2; i >=0 ; i--)
	{
	  if (ex[i] != 999)
	    {
	      while (ex[i] != 999)
		{
		  statusinfo (ex, ey, &myx, &myy, &im, &hel, msg);
		  move (RAD (atan2 (ey[i] - myy, ex[i] - myx)), 0);
		  times = 6;
  temp=getdist(ex[i],ey[i],myx,myy);
		  while (times--)
		    fire (temp, 2);
		}
	    }
	}
      enemyflaginfo (&x, &y, &itaken1, &tid, &pid);
     //   statusinfo (ex, ey, &myx, &myy, &im, &hel, msg);
      ang = atan2 (y - myy, x - myx);
      last=RAD(ang);
      	move (last, 1);
	move (last, 1);
	move (last, 1);

      if (itaken1 == 1 && pi == pid && ti == tid)
	{
	  while (1)
	    {
	      statusinfo (ex, ey, &myx, &myy, &im, &hel, msg);
	      for (i = 0; i < 3; i++)
		{
		  if (ex[i] != 999)
		    {
		      while (ex[i] != 999)
			{
			  statusinfo (ex, ey, &myx, &myy, &im, &hel, msg);
			  move (RAD (atan2 (ey[i] - myy, ex[i] - myx)), 0);
			  times = 6;
			   temp=getdist(ex[i],ey[i],myx,myy);
		  while (times--)
		    fire (temp, 2);
			}
		    }
		}
	      move (RAD (atan2 (yyy - myy, xxx - myx)), 1);
	      if (((665 - myx) * (665 - myx) + (35 - myy) * (35 - myy)) < 100)
		break;
	      if (myx == xxx && myy == yyy)
		goto label1;

	    }
	}
    }
}
