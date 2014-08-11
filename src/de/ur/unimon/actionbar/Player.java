package de.ur.unimon.actionbar;

import java.util.ArrayList;

import de.ur.unimon.unimons.Unimon;

public class Player {
	
	private int ownedMoney;
	public ArrayList <Unimon> ownUnimonList;
	//Inventory inventory = new Inventory (0, 0, 0, 0);
	
	public Player () {
		ownedMoney = 0;
//		inventory = new Inventory (0, 0, 0, 0);
		ownUnimonList = new ArrayList<Unimon>();
	}
	
	public Player (int Money, ArrayList<Unimon> ownUnimonList) {
//	public Player (int Money, ArrayList<Unimon> ownUnimonList, Inventory inventory) {
		ownedMoney = Money; 
		this.ownUnimonList = ownUnimonList; 
//		this.inventory = inventory;
	}

	public void addMoney(int newMoney) {
		ownedMoney += newMoney; 
	}
	
	public int getMoney() {
		return ownedMoney;
	}
	
	public void addUnimon(Unimon newUnimon) {
		ownUnimonList.add(newUnimon);
	}
	
	public ArrayList<Unimon> getUnimonList () {
		return ownUnimonList;
	}
	
//	public void addToInventory() {
//		
//	}
//	public Inventory getInventory() {
//		return inventory;
//	}
}
