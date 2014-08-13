package de.ur.unimon.startgame_logic;

import java.util.ArrayList;

import de.ur.unimon.unimons.Unimon;

public interface PlayerListener {
	
	public void onUniBallCountChange();
	public void onHealPotCountChange();
	public void onReviveCountChange();
	
	public void onAddUnimonToOwnUnimonList(Unimon unimon);
	
	public boolean onUniBallAvailable();
	public boolean onHealPotAvailable();
	
	public ArrayList<Unimon> onGetOwnUnimonList();

}
