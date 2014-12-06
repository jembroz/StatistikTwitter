package com.s3.project.bean;

import java.io.Serializable;
import java.util.Date;

/*
Author : Di2t
Created: 9-9-2014
Class  : Bean untuk menyimpan informasi Bio Tagger
*/
public class BioTaggerBean implements Serializable {

	private static final long serialVersionUID = -6908802243366539324L;	
	private int biotagID;
	private int nourut;
	private String deskripsi;
	private String jenisDesc;
	private String kategori;
	private Date tanggal;
	public int getBiotagID() {
		return biotagID;
	}
	public void setBiotagID(int biotagID) {
		this.biotagID = biotagID;
	}
	public int getNourut() {
		return nourut;
	}
	public void setNourut(int nourut) {
		this.nourut = nourut;
	}
	public String getDeskripsi() {
		return deskripsi;
	}
	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}
	public String getJenisDesc() {
		return jenisDesc;
	}
	public void setJenisDesc(String jenisDesc) {
		this.jenisDesc = jenisDesc;
	}
	public String getKategori() {
		return kategori;
	}
	public void setKategori(String kategori) {
		this.kategori = kategori;
	}
	public Date getTanggal() {
		return tanggal;
	}
	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}
	
}
