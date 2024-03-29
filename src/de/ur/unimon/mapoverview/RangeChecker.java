package de.ur.unimon.mapoverview;

import java.util.ArrayList;
import de.ur.unimon.navigation.PlayerPositionDetail;
import de.ur.unimon.trainer.Trainer;
import de.ur.unimon.trainer.TrainerListController;

public class RangeChecker {

	private ArrayList<Trainer> trainerList;
	private TrainerListController trainerListController;
	RangeListener rangeListener;

	private double playerLongitude, playerLatitude;
	private boolean isShopInRange = false;
	private boolean isDompteurInRange = false;
	private boolean isHospitalInRange = false;

	private boolean isTrainerOneInBattleRange = false;
	private boolean isTrainerTwoInBattleRange = false;
	private boolean isTrainerThreeInBattleRange = false;
	private boolean isTrainerFourInBattleRange = false;
	private boolean isTrainerFiveInBattleRange = false;
	private boolean isTrainerSixInBattleRange = false;
	private boolean isTrainerBossInBattleRange = false;

	private double rangeTrainer = 80;
	private double rangeTrainerBattle = 25;
	private double rangeBuildings = 25;

	public RangeChecker() {
		trainerList = trainerListController.getInstance().getTrainerList();
	}

	public void setOnRangeChecker(RangeListener rangeListener) {
		this.rangeListener = rangeListener;
	}

	public boolean isPlayerInMapRange(PlayerPositionDetail playerPosDetail) {
		playerLongitude = playerPosDetail.getLongitude();
		playerLatitude = playerPosDetail.getLatitude();
		if (MapActivity.leftUpperCornerLongitude > playerLongitude
				|| MapActivity.bottomRightCornerLongitude < playerLongitude
				|| MapActivity.leftUpperCornerLatitude < playerLatitude
				|| MapActivity.bottomRightCornerLatitude > playerLatitude) {
			return false;
		}
		return true;
	}

	private void isTrainerSeen(PlayerPositionDetail playerPosDetail, int pos) {
		if (trainerList.get(pos).isSeen() == false
				&& playerPosDetail.getDistanceToTrainer(pos) < rangeTrainer) {
			trainerList.get(pos).setVisible();
		}
	}

	public void isPlayerInTrainerRange(PlayerPositionDetail playerPosDetail) {
		for (int i = 0; i < trainerList.size(); i++) {
			isTrainerSeen(playerPosDetail, i);
		}
		rangeListener.onTrainerVisibilityChanged(trainerList);
	}

	public void onBuildingIsInRange(PlayerPositionDetail playerPosDetail) {
		if (isShopInRange == false
				&& playerPosDetail.getDistanceToBuilding(0) < rangeBuildings) {
			isShopInRange = true;
			rangeListener.onShowFragmentForBuilding("Shop");
		} else if (isDompteurInRange == false
				&& playerPosDetail.getDistanceToBuilding(1) < rangeBuildings) {
			isDompteurInRange = true;
			rangeListener.onShowFragmentForBuilding("Dompteur");
		} else if (isHospitalInRange == false
				&& playerPosDetail.getDistanceToBuilding(2) < rangeBuildings) {
			isHospitalInRange = true;
			rangeListener.onShowFragmentForBuilding("Hospital");
		}

	}

	public void onBuildingIsNotInRange(PlayerPositionDetail playerPosDetail) {
		if (isShopInRange == true
				&& playerPosDetail.getDistanceToBuilding(0) >= rangeBuildings) {
			isShopInRange = false;
			rangeListener.onCloseFragment();
		} else if (isDompteurInRange == true
				&& playerPosDetail.getDistanceToBuilding(1) >= rangeBuildings) {
			isDompteurInRange = false;
			rangeListener.onCloseFragment();
		} else if (isHospitalInRange == true
				&& playerPosDetail.getDistanceToBuilding(2) >= rangeBuildings) {
			isHospitalInRange = false;
			rangeListener.onCloseFragment();
		}
	}

	public void onPlayerIsInBattleRange(PlayerPositionDetail playerPosDetail) {
		if (isTrainerOneInBattleRange == false
				&& playerPosDetail.getDistanceToTrainer(0) < rangeTrainerBattle) {
			isTrainerOneInBattleRange = true;
			rangeListener.onShowFragmentForTrainer("Trainer", 0);
		} else if (isTrainerTwoInBattleRange == false
				&& playerPosDetail.getDistanceToTrainer(1) < rangeTrainerBattle) {
			isTrainerTwoInBattleRange = true;
			rangeListener.onShowFragmentForTrainer("Trainer", 1);
		} else if (isTrainerThreeInBattleRange == false
				&& playerPosDetail.getDistanceToTrainer(2) < rangeTrainerBattle) {
			isTrainerThreeInBattleRange = true;
			rangeListener.onShowFragmentForTrainer("Trainer", 2);
		} else if (isTrainerFourInBattleRange == false
				&& playerPosDetail.getDistanceToTrainer(3) < rangeTrainerBattle) {
			isTrainerFourInBattleRange = true;
			rangeListener.onShowFragmentForTrainer("Trainer", 3);
		} else if (isTrainerFiveInBattleRange == false
				&& playerPosDetail.getDistanceToTrainer(4) < rangeTrainerBattle) {
			isTrainerFiveInBattleRange = true;
			rangeListener.onShowFragmentForTrainer("Trainer", 4);
		} else if (isTrainerSixInBattleRange == false
				&& playerPosDetail.getDistanceToTrainer(5) < rangeTrainerBattle) {
			isTrainerSixInBattleRange = true;
			rangeListener.onShowFragmentForTrainer("Trainer", 5);
		} else if (isTrainerBossInBattleRange == false
				&& playerPosDetail.getDistanceToTrainer(6) < rangeTrainerBattle) {
			isTrainerBossInBattleRange = true;
			rangeListener.onShowFragmentForTrainer("Trainer", 6);
		}

	}

	public void onPlayerIsNotInBattleRange(PlayerPositionDetail playerPosDetail) {
		if (isTrainerOneInBattleRange == true
				&& playerPosDetail.getDistanceToTrainer(0) >= rangeTrainerBattle) {
			isTrainerOneInBattleRange = false;
			rangeListener.onCloseFragment();
		} else if (isTrainerTwoInBattleRange == true
				&& playerPosDetail.getDistanceToTrainer(1) >= rangeTrainerBattle) {
			isTrainerTwoInBattleRange = false;
			rangeListener.onCloseFragment();
		} else if (isTrainerThreeInBattleRange == true
				&& playerPosDetail.getDistanceToTrainer(2) >= rangeTrainerBattle) {
			isTrainerThreeInBattleRange = false;
			rangeListener.onCloseFragment();
		} else if (isTrainerFourInBattleRange == true
				&& playerPosDetail.getDistanceToTrainer(3) >= rangeTrainerBattle) {
			isTrainerFourInBattleRange = false;
			rangeListener.onCloseFragment();
		} else if (isTrainerFiveInBattleRange == true
				&& playerPosDetail.getDistanceToTrainer(4) >= rangeTrainerBattle) {
			isTrainerFiveInBattleRange = false;
			rangeListener.onCloseFragment();
		} else if (isTrainerSixInBattleRange == true
				&& playerPosDetail.getDistanceToTrainer(5) >= rangeTrainerBattle) {
			isTrainerSixInBattleRange = false;
			rangeListener.onCloseFragment();
		} else if (isTrainerBossInBattleRange == true
				&& playerPosDetail.getDistanceToTrainer(6) >= rangeTrainerBattle) {
			isTrainerBossInBattleRange = false;
			rangeListener.onCloseFragment();
		}
	}

}
