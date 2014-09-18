package de.ur.unimon.mapoverview;

import java.util.ArrayList;

import de.ur.unimon.navigation.PlayerPositionDetail;
import de.ur.unimon.trainer.Trainer;

public interface RangeListener {	

	public void onShowFragmentForBuilding(String building);	
	public void onShowFragmentForTrainer(String building, int trainerID);	
	public void onCloseFragment();
	public void onTrainerVisibilityChanged(ArrayList<Trainer> trainerList);
}
