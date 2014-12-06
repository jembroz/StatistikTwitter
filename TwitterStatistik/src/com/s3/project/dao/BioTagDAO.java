package com.s3.project.dao;

/*Author : Didit
Date : 25-10-2014
Description : Data Access Layer untuk handle BIO Tagger*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.s3.project.bean.BioTaggerBean;
import com.s3.project.connection.ConnectionManager;

public class BioTagDAO {
	static Connection currentCon = null;
	static ResultSet rs = null; 

	public static boolean InsertBioTagger(BioTaggerBean bioTaggerBean){
		try{
			currentCon = ConnectionManager.getConnection();
			PreparedStatement ps = currentCon.prepareStatement("insert into biotagger (bioTagID,"
			+ "nourut,deskripsi, jenisDesc, kategori,tanggal) values (?,?,?,?,?,?)");
			   ps.setInt(1, bioTaggerBean.getBiotagID());
			   ps.setInt(2, bioTaggerBean.getNourut());
			   ps.setString(3, bioTaggerBean.getDeskripsi());
			   ps.setString(4, bioTaggerBean.getJenisDesc());
			   ps.setString(5, bioTaggerBean.getKategori());
			   ps.setTimestamp(6, new java.sql.Timestamp(bioTaggerBean.getTanggal().getTime()));
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
