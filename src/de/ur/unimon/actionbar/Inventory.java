package de.ur.unimon.actionbar;

public class Inventory {

	private int healpots = 0;
	private int uniballs = 0;
	private int revive = 0;
	private int protectors = 0;

	public Inventory(int healpots, int uniballs, int revive, int protectors) {
		this.healpots = healpots;
		this.uniballs = uniballs;
		this.revive = revive;
		this.protectors = protectors;
	}

	public boolean healpotAvailable() {
		if (healpots > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean uniballAvailable() {
		if (uniballs > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean reviveAvailable() {
		if (revive > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean protectorAvailable() {
		if (protectors > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void addHealpot() {
		healpots++;
	}
	
	public void reduceHealpot() {
		healpots--;
	}

	public void addUniball() {
		uniballs++;
	}
	
	public void reduceUniball() {
		uniballs--;
	}

	public void addRevive() {
		revive++;
	}
	
	public void reduceRevive(){
		revive--;
	}

	public void addProtector() {
		protectors++;
	}
	
	public void reduceProtector() {
		protectors--;
	}

	public int getHealpotCount() {
		return healpots;
	}

	public int getUniballCount() {
		return uniballs;
	}

	public int getReviveCount() {
		return revive;
	}

	public int getProtectorCount() {
		return protectors;
	}

	public int decreaseHealpots() {
		if (healpots > 0) {
			healpots--;
			return healpots;
		} else {
			return 0;
		}
	}

	public int decreaseUniball() {
		if (uniballs > 0) {
			uniballs--;
			return uniballs;
		} else {
			return 0;
		}
	}

	public int decreaseRevive() {
		if (revive > 0) {
			revive--;
			return revive;
		} else {
			return 0;
		}
	}

	public int decreaseProtector() {
		if (protectors > 0) {
			protectors--;
			return protectors;
		} else {
			return 0;
		}
	}

}
