package com.s3.project.bean;

import java.io.Serializable;
import java.util.Date;

/*
Author : Di2t
Created: 9-9-2014
Class  : Bean untuk menyimpan informasi twitt
*/
public class TwittBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6067102350201918544L;
	private String idTwitt;
	private String textTwitt;
	private int retweetCount;
	private String screenName;
	private Date createdAt;
	
	public String getIdTwitt() {
		return idTwitt;
	}
	public void setIdTwitt(String idTwitt) {
		this.idTwitt = idTwitt;
	}
	public String getTextTwitt() {
		return textTwitt;
	}
	public void setTextTwitt(String textTwitt) {
		this.textTwitt = textTwitt;
	}
	public int getRetweetCount() {
		return retweetCount;
	}
	public void setRetweetCount(int retweetCount) {
		this.retweetCount = retweetCount;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	

}
