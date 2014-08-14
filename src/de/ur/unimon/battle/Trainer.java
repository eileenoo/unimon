package de.ur.unimon.battle;

import java.util.Random;

import de.ur.unimon.unimons.Unimon;

public class Trainer {
	int id;
	String name;
	int expvalue;
	int moneyvalue;
	Unimon unimon;
	double longitude;
	double latitude;
	Random rand;

	public Trainer(int id, String name, double latitude, double longitude,
			Unimon unimon, int expvalue, int moneyvalue) {
		this.id = id;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.unimon = unimon;
		this.expvalue = expvalue;
		this.moneyvalue = moneyvalue;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getlongitude() {
		return longitude;
	}

	public double getlatitude() {
		return latitude;
	}

	public int getMoneyvalue() {
		return moneyvalue;
	}

	public int getExpvalue() {
		return expvalue;
	}

	public Unimon getUnimon() {
		return unimon;
	}

}