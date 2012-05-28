
#include"apis2.h"
#include<iostream>
#include<stdlib.h>
#include<math.h>
#include<stdio.h>
#define RAD(x) 180.0*7*x/22
using namespace std;
#define NEAR_THRES 15
double angle;
int tid,pid,ti,pi,x,y,itaken,im,ex[3],ey[3],hel,myx,myy, bombCnt =0;
char msg[20];
int eFlagx,eFlagy,eFlagIsTaken,eFlagTid,eFlagPid;
int depositx, deposity;
bool isWall()
{
	int iangle = (int)(angle+0.5);	
	int X_low = 5, Y_low = 40, X_high = 670, Y_high = 670;
	int wall_dist;
	scanwall(&wall_dist);
	if(wall_dist < NEAR_THRES) return true;
	if(myy <= Y_low && iangle > 180 ) return true;
	if(myx <= X_low && iangle > 90 && iangle < 270) return true;
	if(myy >= Y_high && iangle < 180 && iangle > 0 ) return true;
	if(myx >= X_high && (iangle < 90 || iangle > 270)) return true;
	return false;
}

double correct_angle(double angle)
{
	while (angle-360 > -0.000001) angle -= 360;
	while (angle-0 < 0.000001) angle += 360;
	return angle;
}
double dist(int x1, int y1, int x2, int y2)
{
  double val = (x1 - x2) * (x1 - x2) + (y2 - y1)*(y2 - y1);
	
  return sqrt(val*1.);
}
void Fire(double distance)
{
	if(bombCnt < 100) 
	{
		bombCnt++;
		fire((int) distance, 2);
	}
	else fire(100, 1);
	fire((int)distance,1+(bombCnt++<100));
	fire(100,1);
	fire(100,1);
	fire(100,1);
	fire(100,1);
	fire(100,1);
	fire(100,1);
	fire(100,1);
	fire(100,1);
	fire(100,1);
	fire(100,1);
}
void AttackMode()
{
  while (true)
  {
    statusinfo(ex,ey,&myx,&myy,&im,&hel,msg);    
    if(ex[0]==999) return;
    double val = dist(myx,myy, ex[0],ey[0]);
    double rangle = atan2(double(ey[0]-myy), double(ex[0]-myx));
    if( fabs(RAD(rangle) - angle) > 0.0005)
    {
     angle = RAD(rangle);
     move(angle, 0);
    }
    Fire(val);
//    fire(100, 1);
  }
}
//void Move

void roamAbout()
{
	statusinfo(ex,ey,&myx,&myy,&im,&hel,msg);
        int bef = hel;
	while(1)
	{
		while(1) {
			if(isWall())	angle += 10., angle = correct_angle(angle), move(angle,0);
			else break;
		}
		move(angle,1);
		move(angle,1);
		move(angle,1);
		
		statusinfo(ex,ey,&myx,&myy,&im,&hel,msg);
		if(hel > bef) break;
		bef = hel;
		if(ex[0] != 999) 
    {
      AttackMode();
      break;
    }
	}
}
#define PERP_THRES 20
void Move(int targetx, int targety)
{
Again:
	statusinfo(ex,ey,&myx,&myy,&im,&hel,msg);
	int bef = hel;
  	double rangle = atan2(double(targety-myy),double(targetx-myx));
	angle = RAD(rangle);
	move(angle, 0);
	int walldist;
	scanwall(&walldist);
	if(walldist == 999)
	{
		while(true)
		{
			move(angle,1);
			move(angle,1);
			move(angle,1);
			statusinfo(ex,ey,&myx,&myy,&im,&hel,msg);
			if(hel > bef) return;
			bef = hel;
			if(ex[0] != 999)
			{
				AttackMode();
				goto Again;
			}
			if(max(abs(myx-targetx), abs(myy-targety)) < NEAR_THRES) return;
		}
	}
	else {
		for(int i = 0;i+3 < walldist; i++)
		{
			if(i%3 == 0) 
			{
				statusinfo(ex,ey,&myx,&myy,&im,&hel,msg);    
				if(hel > bef) return;
				bef = hel;
				if(ex[0] != 999)
				{
					AttackMode();
					goto Again;
				}
			}
			move(angle, 1);
		}
		// Overcome wall
		statusinfo(ex,ey,&myx,&myy,&im,&hel,msg);
		int stopx = myx, stopy = myy;
		while(true)
		{
			if(!isWall()) 
			{
				move(angle,1),
				move(angle,1),
				move(angle,1);
				angle -= 8;
				angle = correct_angle(angle);
				move(angle, 0);
			}
			else 
			{
				angle += 3;
				angle = correct_angle(angle);
				move(angle, 0);
			}
			statusinfo(ex,ey,&myx,&myy,&im,&hel,msg);
			if(hel > bef) return;
			bef = hel;
			if(ex[0] != 999) 
			{
				AttackMode();
				goto Again;
			}
			double temp = dist(targetx,targety,stopx,stopy);
			double perp_dist = (targetx-stopx)*(stopy-myy)-(stopx-myx)*(targety-stopy)/temp;
			double now_dist = dist(myx,myy, targetx,targety);
			if(perp_dist < PERP_THRES) 
				if(temp - NEAR_THRES >  now_dist)
					break;
		}
	}
}

void Move_sure(int tx, int ty)
{
	Move(tx,ty);
	Move(tx,ty);
}

void captureMyFlag()
{
	
  getmyids(&tid,&pid);
  while(1)
  {
  	myflaginfo(&x,&y,&itaken,&ti,&pi);
  	if(ti==tid && pi == pid) // flag with me
  		return;
	  Move(x,y);
  }
}
void captureEnemyFlag()
{
  getmyids(&tid,&pid);
  while(1)
  {
  	//myflaginfo(&x,&y,&itaken,&ti,&pi);
    enemyflaginfo(&eFlagx, &eFlagy, &eFlagIsTaken, &eFlagTid, &eFlagPid);
  	if(eFlagTid==tid && eFlagPid == pid) // flag with me
  		return;
    else if(eFlagIsTaken && eFlagTid == tid) // flag with team mate
    {
      statusinfo(ex,ey,&myx,&myy,&im,&hel,msg);          
      if(dist(myx,myy,eFlagx,eFlagy) < 40)
        return ;
    }
    
	  Move(eFlagx,eFlagy);
  }
}
void depositIt()
{
  getmyids(&tid,&pid);
  while(1)
  {
    if(tid==eFlagTid&&pid==eFlagPid)
    {
      statusinfo(ex,ey,&myx,&myy,&im,&hel,msg);    
      flagdc(&depositx,&deposity);
      double rangle = atan2(double(deposity-myy), double(depositx-myx));
      angle = RAD(rangle);
      move(angle, 1);
    }
    else break;
  }
}

void depositIt2()
{
  getmyids(&tid,&pid);
  while(1)
  {
    flagdc(&depositx,&deposity);
    enemyflaginfo(&eFlagx, &eFlagy, &eFlagIsTaken, &eFlagTid, &eFlagPid);
  	if(eFlagTid!=tid || eFlagPid != pid) // flag not with me
  		return;
	  Move(depositx,deposity);
  }
}
int main()
{
  init("CSpirit");
flagdc(&depositx,&deposity);
  while(1)
  {
    captureEnemyFlag();
    depositIt2();
    //roamAbout();
  }
}
