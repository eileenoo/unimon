package de.ur.unimon.navigation;

import java.util.ArrayList;

public class PlayerPositionDetail {

	private double latitude, longitude;
	private ArrayList<Float> buildingDistances;
	private ArrayList<Float> trainerDistances;

	public PlayerPositionDetail(double latitude, double longitude,
			ArrayList<Float> buildingDistances,
			ArrayList<Float> trainerDistances) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.buildingDistances = buildingDistances;
		this.trainerDistances = trainerDistances;

	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}
	
	public float getDistanceToBuilding(int i) {
		return buildingDistances.get(i);
	}

	public float getDistanceToTrainer(int j) {
		return trainerDistances.get(j);
	}
}