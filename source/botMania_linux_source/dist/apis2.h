/* Ghost Busters
*
*  ghost.h
*  The file to be included by every client
*/


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
void scanwall(int *dist);
#ifdef __cplusplus
}
#endif

#ifndef NULL
#define NULL 0L
#endif

#ifndef M_PI
#define M_PI 3.1415926535897932384626433832795
#endif

#endif
