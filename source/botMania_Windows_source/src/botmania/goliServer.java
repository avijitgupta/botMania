/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package botmania;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author avijit
 */
public class goliServer implements Runnable{
    
    goli goliObj;
    startGame s;
    int id;
    goliServer(goli gObj, startGame st,int i)
    {
    goliObj=gObj;
    s=st;
    id=i;
    }
    
    public void run()
    {
        int ct=0;
    while(ct<100)
    {
            try {
                goliObj.x = goliObj.x + Math.cos(Math.toRadians(goliObj.angle));
                goliObj.y = goliObj.y + Math.sin(Math.toRadians(goliObj.angle));
                ct++;
                s.gServer.repaint();
                Thread.sleep(40);
            } catch (Exception ex) {
                Logger.getLogger(goliServer.class.getName()).log(Level.SEVERE, null, ex);
            }
    }  
    s.playerObj[id].hasfired=0; 
    }

}
