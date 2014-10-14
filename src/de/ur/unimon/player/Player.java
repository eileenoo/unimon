package de.ur.unimon.player;

import java.util.ArrayList;

import de.ur.unimon.actionbar.Inventory;
import de.ur.unimon.unimons.Unimon;

public class Player {

	private int ownedMoney;
	public ArrayList<Unimon> ownUnimonList;
	private Inventory inventory;

	public Player() {
		ownedMoney = 0;
		inventory = new Inventory(0, 0, 0, 0);
		ownUnimonList = new ArrayList<Unimon>();
	}

	public Player(int Money, ArrayList<Unimon> ownUnimonList,
			Inventory inventory) {
		ownedMoney = Money;
		this.ownUnimonList = ownUnimonList;
		this.inventory = inventory;
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

	public ArrayList<Unimon> getUnimonList() {
		return ownUnimonList;
	}
	
	public Unimon getUnimonByName(String unimonName) {

		for (int i = 0; i < ownUnimonList.size(); i++) {
			if (ownUnimonList.get(i).getName().equals(unimonName)) {
				return ownUnimonList.get(i);
			}
		}
		return null;
	}

	public void addHealpotToInventory() {
		inventory.addHealpot();
	}
	
	public void takeHealpotOutOfInventory() {
		inventory.reduceHealpot();
	}
	
	public void addUniballToInventory() {
		inventory.addUniball();
	}
	
	public void addReviveToInventory() {
		inventory.addRevive();
	}
	
	public void takeReviveOutOfInventory() {
		inventory.reduceRevive();
	}
	
	public void addProtectorToInventory() {
		inventory.addProtector();
	}

	public Inventory getInventory() {
		return inventory;
	}
	
	public int getHigheslevelOfUnimnon(){
		int highestLevel = 0;
		for (Unimon u : ownUnimonList){
			if (highestLevel < u.getLevel()){
				highestLevel = u.getLevel();
			}
		}
		return highestLevel;
	}
}
