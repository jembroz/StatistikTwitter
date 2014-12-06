package com.s3.project.dao;

/*Author : Didit
Date : 28-9-2014
Description : Data Access Layer untuk table UserTweetBio*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.s3.project.bean.UserTwitterBean;
import com.s3.project.connection.ConnectionManager;

public class UserTwitterBioDAO {
	static Connection currentCon = null;
	static ResultSet rs = null; 
	
	public static boolean CreateUser(UserTwitterBean user, Connection currentCon){
		try{
			//currentCon = ConnectionManager.getConnection();
			PreparedStatement ps = currentCon.prepareStatement("insert into UserTweetBio (id,"
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
			//currentCon.close();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public static boolean DeleteUser(UserTwitterBean user){
		try{
			currentCon = ConnectionManager.getConnection();
			 PreparedStatement ps = currentCon.prepareStatement("delete from UserTweetBio where id = ?");
			   ps.setString(1, user.getId());
			   ps.executeUpdate();
			   ps.close();
			currentCon.close();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public static boolean UpdateUser(UserTwitterBean user, Connection currentCon){
		try{
			PreparedStatement ps = currentCon.prepareStatement("update UserTweetBio set screenname = ?,"
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
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static List<UserTwitterBean> GetUserBio(){
		List<UserTwitterBean> userTwitterBean = new ArrayList<UserTwitterBean>();
		try{
			currentCon = ConnectionManager.getConnection();
			PreparedStatement ps = currentCon.prepareStatement("select id, screenname, name, friends_count, followers_count,"
					+ "created_at, description from UserTweetBio");
			ResultSet rs = ps.executeQuery();
			   while (rs.next()){
				   UserTwitterBean usrTwtBn = new UserTwitterBean();
				   usrTwtBn.setId(rs.getString("id"));
				   usrTwtBn.setScreenname(rs.getString("screenname"));
				   usrTwtBn.setName(rs.getString("name"));
				   usrTwtBn.setFriends_count(rs.getInt("friends_count"));
				   usrTwtBn.setFollowers_count(rs.getInt("followers_count"));
				   usrTwtBn.setCreated_at(rs.getString("created_at"));
				   usrTwtBn.setDescription(rs.getString("description"));
				   userTwitterBean.add(usrTwtBn);
			   }
			currentCon.close();
			return userTwitterBean;
		}catch(Exception e){
			e.printStackTrace();
			return userTwitterBean;
		}
	}
	
}
