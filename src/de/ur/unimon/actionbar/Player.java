package de.ur.unimon.actionbar;

import java.util.ArrayList;

import de.ur.unimon.unimons.Unimon;

public class Player {
	
	private int ownedMoney;
	public ArrayList <Unimon> ownUnimonList;
	//Inventory inventory = new Inventory (0, 0, 0, 0);
	
	public Player () {
		this.ownedMoney = 0;
//		inventory = new Inventory (0, 0, 0, 0);
		ownUnimonList = new ArrayList<Unimon>();
	}
	
	public Player (int Money, ArrayList<Unimon> ownUnimonList) {
		
	}

	public void addMoney(int newMoney) {
		ownedMoney += newMoney; 
	}

}
