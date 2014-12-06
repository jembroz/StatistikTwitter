package com.s3.project.test;

import java.sql.Connection;

import junit.framework.TestCase;

import com.s3.project.connection.*;
import com.s3.project.bean.UserTwitterBean;
import com.s3.project.dao.UserTwitterBioDAO;

public class test2 extends TestCase{

	static Connection currCon=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean ud = false;
		try{

		 currCon = ConnectionManager.getConnection();
		 System.out.println("JDBC-ODBC driver success to load.");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("JDBC-ODBC driver failed to load.");
		}
		
		try{
			UserTwitterBean userTwitter = new UserTwitterBean();
			assertNotNull(userTwitter);
			userTwitter.setId("123");
			userTwitter.setScreenname("Didit Jembroz");
			userTwitter.setName("Syahruloh Jabbaar Hadiid");
			userTwitter.setFollowers_count(5);
			userTwitter.setFriends_count(10);
			userTwitter.setDescription("Nothing impressed me much");
			userTwitter.setCreated_at("23-9-2014");
			userTwitter.setStatuses_count(15);
			assertEquals("123", userTwitter.getId());
			assertEquals("Didit Jembroz", userTwitter.getScreenname());
			assertEquals("Syahruloh Jabbaar Hadiid", userTwitter.getName());
			assertEquals(new Integer(5), userTwitter.getFollowers_count());
			assertEquals(new Integer(10), userTwitter.getFriends_count());
			assertEquals("Nothing impressed me much", userTwitter.getDescription());
			assertEquals("23-9-2014", userTwitter.getCreated_at());
			assertEquals(new Integer(15), userTwitter.getStatuses_count());
			
			/*ud = UserTwitterBioDAO.CreateUser(userTwitter);
			if(ud){
				System.out.println("Berhasil Create");
			}else {
				System.out.println("Tidak berhasil Create");
			}*/
			
			/*UserTwitterBean user2 = new UserTwitterBean();*/
			/*user2 = UserTwitterDAO.getUser("jembrozUser");
			System.out.println("Query User");
			System.out.println(user2.getFirstName());
			System.out.println(user2.getLastName());
			System.out.println(user2.getUsername());
			System.out.println(user2.getPassword());
			*/
			boolean deleteUser = UserTwitterBioDAO.DeleteUser(userTwitter);
			if(deleteUser){
				System.out.println("berhasil Delete");
			}else{
				System.out.println("Tidak berhasil delete");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
