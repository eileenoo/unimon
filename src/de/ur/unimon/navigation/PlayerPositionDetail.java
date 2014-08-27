package de.ur.unimon.navigation;

public class PlayerPositionDetail {

	private double latitude, longitude;
	private float distanceShop, distanceDompteur, distanceHospital,
			distanceTrainerOne, distanceTrainerTwo, distanceTrainerThree,
			distanceTrainerFour, distanceTrainerFive, distanceTrainerSix,
			distanceTrainerBoss;

	public PlayerPositionDetail(double latitude, double longitude,
			float distanceShop, float distanceDompteur, float distanceHospital,
			float distanceTrainerOne, float distanceTrainerTwo,
			float distanceTrainerThree, float distanceTrainerFour,
			float distanceTrainerFive, float distanceTrainerSix,
			float distanceTrainerBoss) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.distanceShop = distanceShop;
		this.distanceDompteur = distanceDompteur;
		this.distanceHospital = distanceHospital;
		this.distanceTrainerOne = distanceTrainerOne;
		this.distanceTrainerTwo = distanceTrainerTwo;
		this.distanceTrainerThree = distanceTrainerThree;
		this.distanceTrainerFour = distanceTrainerFour;
		this.distanceTrainerFive = distanceTrainerFive;
		this.distanceTrainerSix = distanceTrainerSix;
		this.distanceTrainerBoss = distanceTrainerBoss;

	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public float getDistanceShop() {
		return distanceShop;
	}

	public float getDistanceDompteur() {
		return distanceDompteur;
	}

	public float getDistanceHospital() {
		return distanceHospital;
	}
	
	public float getDistanceTrainerOne() {
		return distanceTrainerOne;
	}
	
	public float getDistanceTrainerTwo() {
		return distanceTrainerTwo;
	}
	
	public float getDistanceTrainerThree() {
		return distanceTrainerThree;
	}
	
	public float getDistanceTrainerFour() {
		return distanceTrainerFour;
	}
	
	public float getDistanceTrainerFive() {
		return distanceTrainerFive;
	}
	
	public float getDistanceTrainerSix() {
		return distanceTrainerSix;
	}
	
	public float getDistanceTrainerBoss() {
		return distanceTrainerBoss;
	}

}
