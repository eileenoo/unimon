package de.ur.unimon.startgame_logic;

import java.util.ArrayList;

import de.ur.unimon.actionbar.Inventory;
import de.ur.unimon.unimons.Unimon;

public class Player implements PlayerListener {

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
	
	public void addUniballToInventory() {
		inventory.addUniball();
	}
	
	public void addReviveToInventory() {
		inventory.addRevive();
	}
	
	public void addProtectorToInventory() {
		inventory.addProtector();
	}

	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public void onUniBallCountChange() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onHealPotCountChange() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReviveCountChange() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAddUnimonToOwnUnimonList(Unimon unimon) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onUniBallAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onHealPotAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Unimon> onGetOwnUnimonList() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getXp() {
		// TODO Auto-generated method stub
		return null;
	}

}
