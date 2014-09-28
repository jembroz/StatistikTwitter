package com.s3.project.dao;

import java.sql.*;
import com.s3.project.bean.UserBean;
import com.s3.project.connection.ConnectionManager;

public class UserDAO 	
{
   static Connection currentCon = null;
   static ResultSet rs = null;  
	
   public static UserBean login(UserBean bean) {
	
      //preparing some objects for connection 
      /*Statement stmt = null;  */  
	
      String username = bean.getUsername();    
      String password = bean.getPassword();   
	    
      String searchQuery =
            "select * from users where username='"
                     + username
                     + "' AND password='"
                     + password
                     + "'";
	    
   // "System.out.println" prints in the console; Normally used to trace the process
   System.out.println("Your user name is " + username);          
   System.out.println("Your password is " + password);
   System.out.println("Query: "+searchQuery);
	    
   try 
   {
      //connect to DB 
	   /*currentCon = ConnectionManager.getConnection();
      stmt=currentCon.createStatement();
      rs = stmt.executeQuery(searchQuery);	        
      boolean more = rs.next();*/
	   boolean more = true;
	       
      // if user does not exist set the isValid variable to false
      if (!more) 
      {
         System.out.println("Sorry, you are not a registered user! Please sign up first");
         bean.setValid(false);
      } 
	        
      //if user exists set the isValid variable to true
      else if (more) 
      {

         String firstName = username;
         String lastName = password;
         
         System.out.println("Welcome " + firstName);
         bean.setFirstName(firstName);
         bean.setLastName(lastName);
         bean.setValid(true);
      }
   } 

   catch (Exception ex) 
   {
      System.out.println("Log In failed: An Exception has occurred! " + ex);
   } 
	    
   //some exception handling
   /*finally 
   {
      if (rs != null)	{
         try {
            rs.close();
         } catch (Exception e) {}
            rs = null;
         }
	
      if (stmt != null) {
         try {
            stmt.close();
         } catch (Exception e) {}
            stmt = null;
         }
	
      if (currentCon != null) {
         try {
            currentCon.close();
         } catch (Exception e) {
         }

         currentCon = null;
      }
   }*/

return bean;
	
   }	
   
   public static boolean CreateUser(UserBean user){
	   try{
		   currentCon = ConnectionManager.getConnection();
		   PreparedStatement ps = currentCon.prepareStatement("insert into user (firstname,"
		   		+ "lastname,Username, Password) values (?,?,?,?)");
		   ps.setString(1, user.getFirstName());
		   ps.setString(2, user.getLastName());
		   ps.setString(3, user.getUsername());
		   ps.setString(4, user.getPassword());
		   ps.executeUpdate();
		   ps.close();
		   currentCon.close();
		   return true;
	   }catch (Exception e){
		   e.printStackTrace();
		   return false;
	   }
   }
   
   public static boolean DeleteUser(UserBean user){
	   try{
		   currentCon = ConnectionManager.getConnection();
		   PreparedStatement ps = currentCon.prepareStatement("delete from user where username = ?");
		   ps.setString(1, user.getUsername());
		   ps.executeUpdate();
		   ps.close();
		   currentCon.close();
		   return true;
	   }catch (Exception e){
		   return false;
	   }
   }
   
   public static boolean UpdateUser(UserBean user){
	   try{
		   currentCon = ConnectionManager.getConnection();
		   PreparedStatement ps = currentCon.prepareStatement("update table user set firstname = ?"
		   		+ "lastname = ?, Passowrd = ? where username = ?");
		   ps.setString(1, user.getFirstName());
		   ps.setString(2, user.getLastName());
		   ps.setString(3, user.getPassword());
		   ps.setString(4, user.getUsername());
		   ps.executeUpdate();
		   ps.close();
		   currentCon.close();
		   return true;
	   }catch (Exception e){
		   return false;
	   }
   }
   
   public static UserBean getUser(String Username){
	   UserBean user = new UserBean();
	   try{
		   currentCon = ConnectionManager.getConnection();
		   PreparedStatement ps = currentCon.prepareStatement("select * from user where "
		   		+ "username = ?");
		   ps.setString(1, Username);
		   ResultSet rs = ps.executeQuery();
		   while (rs.next()){
			   user.setFirstName(rs.getString("FirstName"));
			   user.setLastName(rs.getString("LastName"));
			   user.setUsername(rs.getString("Username"));
			   user.setPassword(rs.getString("Password"));
		   }
		   ps.close();
		   currentCon.close();
		   return user;
	   }catch (Exception e){
		   return user;
	   }
   }
   
}