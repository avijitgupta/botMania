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

/**
 *
 * @author avijit
 */
public class playerInfo {
    String name;
    String packet;
    //int plen;
    Socket socket;
    int teamid;
    int playerid;
    int health;
    int ownflagcount;
    int enemyflagcount;
    String message;
    int unread;
    int messagesender;
    //int movespeed;
    int movedir;
    double myx,myy;
    int ismoving;
    //int iscaptured;
    int isdead;
    int score;
    int nobombs;
    int hasfired;
    double fired[]=new double[6];
    double scan[]=new double[4];
    int tofire;
     goli goliObj=new goli();
     int blocked;
    int range;
    int hasscanned;
    int img;
    int myflagcount;
    int eflagcount;
}
