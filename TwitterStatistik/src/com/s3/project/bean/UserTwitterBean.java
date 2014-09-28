package com.s3.project.bean;

/*
Author : Di2t
Created: 9-9-2014
Class  : Bean untuk menyimpan informasi yang di ambil dari file json untuk keperluan 
		 User Twitter
*/

public class UserTwitterBean {

	private String screenname; //nama akun yang ada di belakang tanda @
	private String name; //Nama lengkap
	private Integer friends_count; //Jumlah teman yang di follow
	private Integer followers_count; //Jumlah follower
	private String created_at; //Tanggal dibuatnya Akun
	private String description; //Bio
	private Integer statuses_count; //Jumlah Tweet
	private String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getScreenname() {
		return screenname;
	}
	public void setScreenname(String screenname) {
		this.screenname = screenname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getFriends_count() {
		return friends_count;
	}
	public void setFriends_count(Integer friends_count) {
		this.friends_count = friends_count;
	}
	public Integer getFollowers_count() {
		return followers_count;
	}
	public void setFollowers_count(Integer followers_count) {
		this.followers_count = followers_count;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getStatuses_count() {
		return statuses_count;
	}
	public void setStatuses_count(Integer statuses_count) {
		this.statuses_count = statuses_count;
	}
	
}
