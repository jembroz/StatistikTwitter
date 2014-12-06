package com.s3.project.dao;

/*Author : Didit
Date : 28-10-2014
Description : Data Access Layer untuk handle twitt Tagger*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.s3.project.bean.TwittTaggerBean;
import com.s3.project.connection.ConnectionManager;

public class TwittTagDAO {
	static Connection currentCon = null;
	static ResultSet rs = null; 

	public static boolean InsertTwittTagger(TwittTaggerBean twittTaggerBean){
		try{
			currentCon = ConnectionManager.getConnection();
			PreparedStatement ps = currentCon.prepareStatement("insert into twitttagger (twittID,"
			+ "nourut,deskripsi, jenisDesc, kategori,tanggal) values (?,?,?,?,?,?)");
			   ps.setInt(1, twittTaggerBean.getTwittID());
			   ps.setInt(2, twittTaggerBean.getNourut());
			   ps.setString(3, twittTaggerBean.getDeskripsi());
			   ps.setString(4, twittTaggerBean.getJenisDesc());
			   ps.setString(5, twittTaggerBean.getKategori());
			   ps.setTimestamp(6, new java.sql.Timestamp(twittTaggerBean.getTanggal().getTime()));
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
