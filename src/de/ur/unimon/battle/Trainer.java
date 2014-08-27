package de.ur.unimon.battle;

import java.util.Random;

import de.ur.unimon.unimons.Unimon;

public class Trainer {
	int id;
	String name;
	int expValue, moneyValue;
	Unimon unimon;
	double longitude, latitude;
	Random rand;
	boolean isSeen;

	public Trainer(int id, String name, double latitude, double longitude,
			Unimon unimon, int expValue, int moneyValue) {
		this.id = id;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.unimon = unimon;
		this.expValue = expValue;
		this.moneyValue = moneyValue;
		this.isSeen = false;
	}
	
	public boolean isSeen(){
		return isSeen;
	}
	
	public void setVisible(){
		isSeen = true;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public int getMoneyValue() {
		return moneyValue;
	}

	public int getExpValue() {
		return expValue;
	}

	public Unimon getUnimon() {
		return unimon;
	}

}