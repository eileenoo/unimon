package de.ur.unimon.actionbar;

import java.util.ArrayList;

import de.ur.unimon.unimons.Unimon;

public class Player {
	
	private int ownedMoney = 0;
	public ArrayList <Unimon> ownedUnimon = null;

	public void addMoney(int newMoney) {
		ownedMoney += newMoney; 
	}

}
