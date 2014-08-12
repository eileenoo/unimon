package de.ur.unimon.buildings;

import java.util.ArrayList;

import de.ur.unimon.unimons.Unimon;

public class HospitalLogic {
	
	public Unimon heal(Unimon unimon){
		unimon.setAlive(true);
		unimon.setHealth(unimon.getMaxHealth());
		return unimon;	
	}
	
	public void healAll(ArrayList<Unimon> unimons){
		for (int i=0; i<unimons.size();i++){
			unimons.get(i).setHealth(unimons.get(i).getMaxHealth());
		}

		
	}
}
