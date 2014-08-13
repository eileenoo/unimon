package de.ur.unimon.actionbar;

import java.util.ArrayList;

import de.ur.unimon.unimons.Unimon;

public class Player {

	private int ownedMoney, ownedXp;
	public ArrayList<Unimon> ownUnimonList;
	Inventory inventory = new Inventory(0, 0, 0, 0);

	public Player() {
		ownedMoney = 0;
		ownedXp = 0;
		inventory = new Inventory(0, 0, 0, 0);
		ownUnimonList = new ArrayList<Unimon>();
	}

	public Player(int Money, int xp, ArrayList<Unimon> ownUnimonList,
			Inventory inventory) {
		ownedMoney = Money;
		ownedXp = xp;
		this.ownUnimonList = ownUnimonList;
		this.inventory = inventory;
	}

	public void addMoney(int newMoney) {
		ownedMoney += newMoney;
	}

	public int getMoney() {
		return ownedMoney;
	}

	public void addXp(int newXp) {
		ownedMoney += newXp;
	}

	public int getXp() {
		return ownedXp;
	}

	public void addUnimon(Unimon newUnimon) {
		ownUnimonList.add(newUnimon);
	}

	public ArrayList<Unimon> getUnimonList() {
		return ownUnimonList;
	}

	// public void addToInventory() {
	//
	// }
	// public Inventory getInventory() {
	// return inventory;
	// }
}
