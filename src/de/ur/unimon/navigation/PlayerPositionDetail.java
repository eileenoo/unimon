package de.ur.unimon.navigation;

public class PlayerPositionDetail {

	private double latitude, longitude;
	private float distanceShop, distanceDompteur, distanceHospital;
	
	public PlayerPositionDetail(double latitude, double longitude, float distanceShop, float distanceDompteur, float distanceHospital) {				
		this.latitude = latitude;
		this.longitude = longitude;
		this.distanceShop = distanceShop;
		this.distanceDompteur = distanceDompteur;
		this.distanceHospital = distanceHospital;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}
	
	public float getDistanceShop(){
		return distanceShop;
	}
	
	public float getDistanceDompteur(){
		return distanceDompteur;
	}
	
	public float getDistanceHospital(){
		return distanceHospital;
	}

}
