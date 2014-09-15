package de.ur.unimon.mapoverview;

import java.util.ArrayList;

import de.ur.unimon.battle.Trainer;
import de.ur.unimon.navigation.PlayerPositionDetail;

public interface RangeListener {	

	public void onShowFragmentForBuilding(String building);	
	public void onShowFragmentForTrainer(String building, int trainerID);	
	public void onCloseFragment();
	public void onTrainerVisibilityChanged(ArrayList<Trainer> trainerList);
}
