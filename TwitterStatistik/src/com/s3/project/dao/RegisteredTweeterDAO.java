package com.s3.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.s3.project.connection.ConnectionManager;

public class RegisteredTweeterDAO {
	   static Connection currentCon = null;
	   static ResultSet rs = null;  
	   
	   public static boolean CreateUser(String screenName){
		   try{
			   currentCon = ConnectionManager.getConnection();
			   PreparedStatement ps = currentCon.prepareStatement("insert into RegisteredTweeter (screenname,"
			   		+ "registereddate) values (?,?)");
			   ps.setString(1, screenName);
			   ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
			   ps.executeUpdate();
			   ps.close();
			   currentCon.close();
			   return true;
		   }catch (Exception e){
			   e.printStackTrace();
			   return false;
		   }
	   }
}
