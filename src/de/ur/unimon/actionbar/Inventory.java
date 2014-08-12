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

	public void addHealpot() {
		healpots++;
	}

	public void addUniball() {
		uniballs++;
	}

	public void addRevive() {
		revive++;
	}

	public void addProtector() {
		protectors++;
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
			return -1;
		}
	}

	public int decreaseUniball() {
		if (uniballs > 0) {
			uniballs--;
			return uniballs;
		} else {
			return -1;
		}
	}

	public int decreaseRevive() {
		if (revive > 0) {
			revive--;
			return revive;
		} else {
			return -1;
		}
	}

	public int decreaseProtector() {
		if (protectors > 0) {
			protectors--;
			return protectors;
		} else {
			return -1;
		}
	}

}
