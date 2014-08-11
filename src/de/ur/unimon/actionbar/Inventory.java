package de.ur.unimon.actionbar;

public class Inventory {
	// private String healpot;
	// private String uniball;
	// private String revive;
	// private String protection;
	private int healpots = 0;
	private int uniballs = 0;
	private int revive = 0;
	private int protectors = 0;

	private void addHealpot() {
		healpots++;
	}
	private void addUniball() {
		uniballs++;
	}
	private void addRevive() {
		revive++;
	}
	private void addProtector() {
		protectors++;
	}
	private int getHealpotCount(){
		return healpots;
	}
	private int getUniballCount(){
		return uniballs;
	}
	private int getReviveCount(){
		return revive;
	}
	private int getProtectorCount(){
		return protectors;
	}
	private int decreaseHealpots(){
		if (healpots > 0){
			healpots--;
			return healpots;
		} else {
			return -1;
		}
	}
	private int decreaseUniball(){
		if (uniballs > 0){
			uniballs--;
			return uniballs;
		} else {
			return -1;
		}
	}
	private int decreaseRevive(){
		if (revive > 0){
			revive--;
			return revive;
		} else {
			return -1;
		}
	}
	private int decreaseProtector(){
		if (protectors > 0){
			protectors--;
			return protectors;
		} else {
			return -1;
		}
	}

}
