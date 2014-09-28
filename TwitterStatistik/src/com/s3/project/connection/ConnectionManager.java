package com.s3.project.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

   static Connection con;
   static String url;
         
   public static Connection getConnection()
   {
     
      try
      {
         String url = "jdbc:odbc:" + "TwitterStatistik"; 
         /* assuming "DataSource" is your TwitterStatisik name
          * TwitterStatistik di setting di ODBC windows, passwordnya juga.. jangan lupa
          * 123456
          */
         Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
         java.util.Properties prop = new java.util.Properties();
         prop.put("charSet", "UTF8");
         try
         {            	
            //con = DriverManager.getConnection(url,"username","password"); 
        	 con = DriverManager.getConnection(url,prop);
         // assuming your SQL Server's	username is "username"               
         // and password is "password"
              
         }
         
         catch (SQLException ex)
         {
            ex.printStackTrace();
         }
      }

      catch(ClassNotFoundException e)
      {
         System.out.println(e);
      }

   return con;
}
}