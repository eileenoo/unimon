package de.ur.unimon.startgame_logic;

import java.util.ArrayList;

import de.ur.unimon.actionbar.Inventory;
import de.ur.unimon.unimons.Unimon;
import de.ur.unimon.unimons.UnimonList;

public final class PlayerController {
	private static Player player;
	
	private PlayerController() {
		
	}
	
	public synchronized static Player getInstance(){
		if (player == null){
			player = new Player();
		}
		return player;
	}
	
	public static Player getInstanceFromDB(){
		Inventory inventory = new Inventory(4,5,5,4);
		ArrayList<Unimon> unimonList = new UnimonList().getUnimonList();
		player = new Player(200, unimonList, inventory);
		return player;
	}

}
