package de.ur.unimon.navigation;

public class PlayerPositionDetail {

	private double latitude;
	private double longitude;
	
	public PlayerPositionDetail(double d, double e) {		
		this.latitude = d;
		this.longitude = e;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

}
