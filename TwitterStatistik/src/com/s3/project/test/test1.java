package com.s3.project.test;

import java.sql.Connection;

import junit.framework.TestCase;

import com.s3.project.connection.*;
import com.s3.project.bean.UserBean;
import com.s3.project.dao.UserDAO;;

public class test1 extends TestCase{

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
			UserBean user = new UserBean();
			assertNotNull(user);
			user.setFirstName("DiditFirst");
			user.setLastName("jembrozlast");
			user.setUsername("jembrozuser");
			user.setPassword("jembrozpass");
			assertEquals("DiditFirst", user.getFirstName());
			assertEquals("jembrozlast", user.getLastName());
			assertEquals("jembrozuser", user.getUsername());
			assertEquals("jembrozpass", user.getPassword());
			
			ud = UserDAO.CreateUser(user);
			if(ud){
				System.out.println("Berhasil Create");
			}else {
				System.out.println("Tidak berhasil Create");
			}
			
			UserBean user2 = new UserBean();
			user2 = UserDAO.getUser("jembrozUser");
			System.out.println("Query User");
			System.out.println(user2.getFirstName());
			System.out.println(user2.getLastName());
			System.out.println(user2.getUsername());
			System.out.println(user2.getPassword());
			
			boolean deleteUser = UserDAO.DeleteUser(user2);
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
