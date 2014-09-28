package com.s3.project.dao;

/*Author : Didit
Date : 28-9-2014
Description : Data Access Layer untuk table UserTweet*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.s3.project.bean.UserTwitterBean;
import com.s3.project.connection.ConnectionManager;

public class UserTwitterDAO {
	static Connection currentCon = null;
	static ResultSet rs = null; 
	
	public static boolean CreateUser(UserTwitterBean user){
		try{
			currentCon = ConnectionManager.getConnection();
			PreparedStatement ps = currentCon.prepareStatement("insert into UserTweet (id,"
			   		+ "screenname,name, friends_count,followers_count,created_at, description,"
			   		+ " statuses_count) values (?,?,?,?,?,?,?,?)");
			   ps.setString(1, user.getId());
			   ps.setString(2, user.getScreenname());
			   ps.setString(3, user.getName());
			   ps.setInt(4, user.getFriends_count());
			   ps.setInt(5, user.getFollowers_count());
			   ps.setString(6, user.getCreated_at());
			   ps.setString(7, user.getDescription());
			   ps.setInt(8, user.getStatuses_count());
			   ps.executeUpdate();
			   ps.close();
			currentCon.close();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public static boolean DeleteUser(UserTwitterBean user){
		try{
			currentCon = ConnectionManager.getConnection();
			 PreparedStatement ps = currentCon.prepareStatement("delete from UserTweet where id = ?");
			   ps.setString(1, user.getId());
			   ps.executeUpdate();
			   ps.close();
			currentCon.close();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public static boolean UpdateUser(UserTwitterBean user){
		try{
			currentCon = ConnectionManager.getConnection();
			PreparedStatement ps = currentCon.prepareStatement("update table UserTweet set screenname = ?"
			   		+ "name = ?, friends_count = ?, followers_count = ?,created_at = ?, description = ?,"
			   		+ "statuses_count = ? where id = ?");
			   ps.setString(1, user.getScreenname());
			   ps.setString(2, user.getName());
			   ps.setInt(3, user.getFriends_count());
			   ps.setInt(4, user.getFollowers_count());
			   ps.setString(5, user.getCreated_at());
			   ps.setString(6, user.getDescription());
			   ps.setInt(7, user.getStatuses_count());
			   ps.setString(8, user.getId());
			   ps.executeUpdate();
			   ps.close();
			currentCon.close();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
}
