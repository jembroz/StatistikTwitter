package com.s3.project.bean;

import java.io.Serializable;

/*
Author : Di2t
Created: 9-9-2014
Class  : Bean untuk menyimpan informasi User Aplikasi
*/

public class UserBean implements Serializable {
	 /**
	 * 
	 */
	 private static final long serialVersionUID = -5820322966395753798L;
	 private String username;
	 private String password;
	 private String firstName;
	 private String lastName;
	 public boolean valid;
	
	
     public String getFirstName() {
        return firstName;
	}

     public void setFirstName(String newFirstName) {
        firstName = newFirstName;
	}

	
     public String getLastName() {
        return lastName;
			}

     public void setLastName(String newLastName) {
        lastName = newLastName;
			}
			

     public String getPassword() {
        return password;
	}

     public void setPassword(String newPassword) {
        password = newPassword;
	}
	
			
     public String getUsername() {
        return username;
			}
			
     public boolean isValid() {
        return valid;
	}

     public void setUsername(String username) {
		this.username = username;
	}

	public void setValid(boolean newValid) {
        valid = newValid;
	}	
}
