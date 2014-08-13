package de.ur.unimon.navigation;

public class PlayerPositionDetail {

	private float latitude;
	private float longitude;
	
	public PlayerPositionDetail(float latitude, float longitude) {		
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public float getLongitude() {
		return longitude;
	}

}
