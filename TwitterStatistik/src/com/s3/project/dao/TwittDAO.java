package com.s3.project.dao;

/*Author : Didit
Date : 28-9-2014
Description : Data Access Layer untuk handle twitt*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.s3.project.bean.TwittBean;
import com.s3.project.connection.ConnectionManager;

public class TwittDAO {
	static Connection currentCon = null;
	static ResultSet rs = null; 

	public static boolean CreateUser(TwittBean twittBean){
		try{
			currentCon = ConnectionManager.getConnection();
			PreparedStatement ps = currentCon.prepareStatement("insert into twitt (idTwitt,"
			+ "textTwitt,retweetCount, screenName,createdAt) values (?,?,?,?,?)");
			   ps.setString(1, twittBean.getIdTwitt());
			   ps.setString(2, twittBean.getTextTwitt());
			   ps.setInt(3, twittBean.getRetweetCount());
			   ps.setString(4, twittBean.getScreenName());
			   ps.setTimestamp(5, new java.sql.Timestamp(twittBean.getCreatedAt().getTime()));
			   ps.executeUpdate();
			   ps.close();
			currentCon.close();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
}
