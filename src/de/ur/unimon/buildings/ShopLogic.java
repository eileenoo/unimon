package de.ur.unimon.buildings;

import de.ur.unimon.actionbar.Inventory;

public class ShopLogic {
	
	public Inventory buy(Inventory inv, String name){
		
		if (name == "healpot"){
			inv.addHealpot();
		}
		if (name == "uniball"){
			inv.addUniball();
		}
		if (name == "revive"){
			inv.addRevive();
		}
		if (name == "protector"){
			inv.addProtector();
		}
		return inv;
		
	}

}
