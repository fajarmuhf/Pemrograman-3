package com.logic.evil.component;

import java.util.Date;

public class Player {
	private int id;
	private String username;
	private String password;
	private String tipe;
	private Date tanggal;
	
	public void setId(int x){
		id = x;
	}
	public int getId(){
		return id;
	}
	public void setUsername(String x){
		username = x;
	}
	public String getUsername(){
		return username;
	}
	public void setPassword(String x){
		password = x;
	}
	public String getPassword(){
		return password;
	}
	public void setTipe(String x){
		tipe = x;
	}
	public String getTipe(){
		return tipe;
	}
	public void setTanggal(Date x){
		tanggal = x;
	}
	public Date getTanggal(){
		return tanggal;
	}
}
