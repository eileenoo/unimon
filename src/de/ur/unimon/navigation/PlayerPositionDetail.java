package de.ur.unimon.navigation;

public class PlayerPositionDetail {

	private double latitude;
	private double longitude;
	
	public PlayerPositionDetail(double longitude, double latitude) {		
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

}
