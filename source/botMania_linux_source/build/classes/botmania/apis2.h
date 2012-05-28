/* Ghost Busters
*
*  ghost.h
*  The file to be included by every client
*/

#include<winsock.h>
#include<windows.h>
#pragma comment(lib,"lwsock32.lib")
#define MAX 100
#define PORT 1155
#ifndef _APIS2_H
#define _APIS2_H

#ifdef __cplusplus
extern "C++"
{
#endif
int init(char *);//
void move(double,int);//
void fire(int,int);//
void myflaginfo(int *,int *,int *, int *,int *);//
int statusinfo(int*,int *,int *,int *,int*,int*,char*);//
int sendmsg(int,char[]);//
void getmyids(int *,int *);//
void enemyflaginfo(int *,int *,int *, int *, int *);//
void flagdc(int *x,int *y);//


SOCKET s;
int init(char *gotname)//done
{
	
	
	struct in_it
	{
	char id;	
	char name[20];
	char term;
	}_init;	

	int numbytes;
	char buf[MAX]="";
	char ch;
	struct hostent *he;
	struct sockaddr_in their_addr;
    WSADATA wsaData;
    WORD wVersionRequested;
    wVersionRequested =MAKEWORD(1,1);
    if(WSAStartup(wVersionRequested,&wsaData)!=0){}
    
    
    //  if ((he=gethostbyname("avijit"))==NULL) 
	//{  // get the host info
      //perror("gethostbyname");
      //exit(1);
  	//}
	
  if ((s = socket(AF_INET, SOCK_STREAM, 0)) == -1) 
	{
    //  perror("socket");
      exit(1);
  	}
	

  their_addr.sin_family = AF_INET;    // host byte order
  their_addr.sin_port = htons(PORT); // short, network byte order
  their_addr.sin_addr.s_addr=inet_addr("127.0.0.1") ;

  memset(&(their_addr.sin_zero), 8,sizeof(their_addr.sin_zero)); // zero the rest of the struct

int i;
for(i = 0;gotname[i] != '\0' && i < 19;i++)
		_init.name[i] = gotname[i];
	
	while(i<19){_init.name[i]=' ';i++;}
	_init.name[19]='\0';
  	

if (connect(s, (struct sockaddr *)&their_addr,sizeof(their_addr)) == -1) 
{
      //perror("connect");
      exit(1);
}
	_init.id='I';
	_init.term='$';	
	
	send(s,(const char*)&_init,sizeof(struct in_it),0);
	recv(s, (char *)&ch,1,0);
//	cout<<ch<<"\n";	
	return ch;
	
}


void move(double angle,int ismoving)//allowed angles... 0-999 done
{
	char ch;
	int k;
	while(angle<0)
	angle+=360;
	if((int)(angle+0.5)>(int)angle)k=(int)angle+1;
	else k=(int)angle;
	if(k>=360)k%=360;
	struct _pspos
	{
		char id;
		char plen;		
		char ang[4];
		char ismov;
		char term;	
	} pspos;
	//cout<<"h";
	pspos.plen =  sizeof(pspos)+'0';
	//pspos.plen =  (char)67;
	pspos.id = 'M';
	pspos.term='$';
	int l=2;
	while(l>=0)
	{
	pspos.ang[l--]=(char)((k%10)+'0');
	k/=10;	
	}
	pspos.ang[3]='\0';
	pspos.ismov=ismoving+'0';
	//pspos.ismov='Z';
	send(s,(const char *)&pspos,sizeof(pspos),0);
	recv(s,(char *)&ch,sizeof(char),0);
	//cout<<ch;
}


void fire(int range,int type)//0 for bullet, 1 for bomb done 
{
	struct _pspos
	{
		char id;
		char plen;		
		char ran[4];
		char t;
		char term;	
	} pspos;

	char ch;
	
	pspos.plen = sizeof(pspos);
	pspos.id = 'F';
	pspos.t=type+'0';
	pspos.term='$';	
	int l,k;
	
	l=2;
	if(range>100)range=100;
	k=range;
	
	while(l>=0)
	{
	pspos.ran[l--]=(k%10)+'0';
	k/=10;	
	}
	pspos.ran[3]='\0';
	
	send(s, (const char *)&pspos,sizeof(pspos),0);
	recv(s, (char *)&ch,sizeof(char),0);
	
	
}


int statusinfo(int *ex,int *ey,int * x,int * y,int * ismoving, int * health,char *mymsg)
{
	struct _pspos
	{	
		char id;
		char plen;
		char term;
	} pspos;

	
	pspos.plen = sizeof(pspos)+'0';
	pspos.id = 'G';
	pspos.term='$';
	send(s, (const char *)&pspos,sizeof(pspos),0);
	char buf[100];	
	recv(s, (char *)&buf,sizeof(buf),0);//to be changed
	int xx,yy,c=0,i;
	
	for(i=0;i<3;i++)
	{xx=100*(buf[c]-'0')+10*(buf[c+1]-'0')+(buf[c+2]-'0');
	ex[i]=xx;
	c+=3;
	}
	
	for(i=0;i<3;i++)
	{yy=100*(buf[c]-'0')+10*(buf[c+1]-'0')+(buf[c+2]-'0');
	ey[i]=yy;
	c+=3;
	}	

	*x=100*(buf[c]-'0')+10*(buf[c+1]-'0')+(buf[c+2]-'0');	
	c+=3;	
	*y=100*(buf[c]-'0')+10*(buf[c+1]-'0')+(buf[c+2]-'0');
	c+=3;	
	*ismoving=buf[c]-'0';
	c++;

	*health=100*(buf[c]-'0')+10*(buf[c+1]-'0')+(buf[c+2]-'0');
	c+=3;


	for(i=c;i<c+18;i++)
	{	
	mymsg[i-c]=buf[i];	
	}
	mymsg[i]='\0';
}

int sendmsg(int ii,char msg[])//done
{
	struct _pspos
	{
	char id;
	char m[20];
	char to;	
	char term;	
	}pspos;
	pspos.id='S';
	int i;
	for(i=0;i<strlen(msg);i++)
	pspos.m[i]=msg[i];
	for(i=strlen(msg);i<19;i++)
	pspos.m[i]=' ';
	pspos.m[19]='\0';
	pspos.term='$';
	pspos.to=ii+'0';
	send(s,(const char*)&pspos,sizeof(pspos),0);
	char buf;
	recv(s,(char *)&buf,sizeof(buf),0);
	//cout<<buf;
	return buf;		

}

void myflaginfo(int *x,int *y,int *istaken, int *teamid, int *playerid)//done
{

struct _pspos
{
char id;
char plen;
char term;
}pspos;
pspos.term='$';
pspos.plen=sizeof(pspos)+'0';
pspos.id='Q';
send(s,(const char*)&pspos,sizeof(pspos),0);
char buf[100];
recv(s,(char *)&buf,sizeof(buf),0);
*x=(buf[0]-'0')*100+(buf[1]-'0')*10+(buf[2]-'0');
*y=(buf[3]-'0')*100+(buf[4]-'0')*10+(buf[5]-'0');
*istaken=buf[6]-'0';
*teamid=buf[7]-'0';
*playerid=buf[8]-'0';
}


void getmyids(int *teamid,int *playerid)
{
struct _pspos
{
char id;
char plen;
char term;
}pspos;
pspos.term='$';
pspos.plen=sizeof(pspos)+'0';
pspos.id='D';
send(s,(const char*)&pspos,sizeof(pspos),0);
char buf[100];
recv(s,(char *)&buf,sizeof(buf),0);
*teamid=buf[0]-'0';
*playerid=buf[1]-'0';

}




void enemyflaginfo(int *x,int *y,int *istaken, int *teamid, int *playerid)//done
{
//cout<<"here";
struct _pspos
{
char id;
char plen;
char term;
}pspos;
pspos.term='$';
pspos.plen=sizeof(pspos)+'0';
pspos.id='T';
send(s,(const char*)&pspos,sizeof(pspos),0);
char buf[100];
recv(s,(char *)&buf,sizeof(buf),0);
*x=(buf[0]-'0')*100+(buf[1]-'0')*10+(buf[2]-'0');
*y=(buf[3]-'0')*100+(buf[4]-'0')*10+(buf[5]-'0');
*istaken=buf[6]-'0';
*teamid=buf[7]-'0';
*playerid=buf[8]-'0';

}


void flagdc(int *x,int *y)
{

struct _pspos
{
char id;
char plen;
char term;
}pspos;
pspos.term='$';
pspos.plen=sizeof(pspos)+'0';
pspos.id='X';
send(s,(const char*)&pspos,sizeof(pspos),0);
char buf[100];
recv(s,(char *)&buf,sizeof(buf),0);
*x=(buf[0]-'0')*100+(buf[1]-'0')*10+(buf[2]-'0');
*y=(buf[3]-'0')*100+(buf[4]-'0')*10+(buf[5]-'0');


}







#ifdef __cplusplus
}
#endif

#ifndef NULL
#define NULL 0L
#endif

#endif
