package com.logic.evil.component;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Unit {
	int id;
	String name;
	String tipe;
	String lokasi;
	TextButton buttonName;
	
	public String getTipe() {
		return tipe;
	}
	public void setTipe(String tipe) {
		this.tipe = tipe;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLokasi() {
		return lokasi;
	}
	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}
	public TextButton getButtonName() {
		return buttonName;
	}
	public void setButtonName(TextButton buttonName) {
		this.buttonName = buttonName;
	}
	
}
