/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package botmania;
import java.sql.*;
import java.util.*;
import java.io.*;
/**
 *
 * @author cool
 */
public class dataEngine
{

public Connection dbConnection()throws Exception
{
  
        BufferedReader in = new BufferedReader(new FileReader(".botMania/1.txt"));
        String uname="", passwd="",driver="",host="jdbc:mysql://";
        uname = in.readLine();
        in = new BufferedReader(new FileReader(".botMania/2.txt"));
        passwd=in.readLine();
        in = new BufferedReader(new FileReader(".botMania/3.txt"));
        host+=in.readLine();
        in = new BufferedReader(new FileReader(".botMania/4.txt"));
        driver=in.readLine();
        in = new BufferedReader(new FileReader(".botMania/5.txt"));
        host+=":"+in.readLine()+"/";

  Class.forName(driver);
  String myDB =host+"botMania";
  Connection conn=null;
  try{
  conn= DriverManager.getConnection(myDB,uname,passwd);
  } catch(Exception e)
    {
     errorForm o=new errorForm("Could not connect to database. This might be due to the following reasons:\n\n 1. Your mysql server has not been started\n\n 2. You might not have configured mySql server for botMania.\n\n Please see documentation for more details");
     o.setVisible(true);
    }
  return conn;
}
 /**
 * This method will load vector of vector of string and load all the data in
 * the vector
 * @return vector of vector of string
 * @throws java.lang.Exception
 */
 public Vector getScore()throws Exception
 {
 Vector<Vector<String>> scoreVector = new Vector<Vector<String>>();

 Connection conn = dbConnection();
 PreparedStatement pre = conn.prepareStatement("SELECT * from scores ORDER BY score DESC");

 ResultSet rs = pre.executeQuery();

 while(rs.next())
 {
 Vector<String> teamscore = new Vector<String>();
 teamscore.add(rs.getString(1)); //TeamName
 teamscore.add(rs.getString(2)); //Score
 scoreVector.add(teamscore);
 }

 /*Close the connection after use (MUST)*/
 if(conn!=null)
 conn.close();

 return scoreVector;
 }
 }
