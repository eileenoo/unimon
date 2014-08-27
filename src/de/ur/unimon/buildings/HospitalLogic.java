package de.ur.unimon.buildings;

import java.util.ArrayList;

import de.ur.unimon.unimons.Unimon;

public class HospitalLogic {
		
	public void healAll(ArrayList<Unimon> unimons){
		for (int i=0; i<unimons.size();i++){
			unimons.get(i).setHealth(unimons.get(i).getMaxHealth());
			unimons.get(i).setAlive(true);
		}

		
	}
}
