package de.ur.unimon.navigation;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import de.ur.unimon.trainer.TrainerList;
import de.ur.unimon.trainer.TrainerListController;

public class NavigationController implements LocationListener {

	public static final long UPDATE_TIME = 2500;
	public static final float UPDATE_DISTANCE = 2;

	LocationManager locationManager;
	private Location lastLocation;

	Location shop, dompteur, hospital, trainerOne, trainerTwo, trainerThree,
			trainerFour, trainerFive, trainerSix, trainerBoss;
	ArrayList<Float> trainerDistances, buildingDistances;
	TrainerList trainerList;
	private TrainerListController trainerListController;

	private NavigationListener navigationListener;
	PlayerPositionDetail playerPosDetail;
	ProgressDialog progressDialog;
	private String gps_lost_info = "Searching for GPS-Signal";

	public NavigationController(Context context,
			NavigationListener navigationListener) {
		this.navigationListener = navigationListener;
		trainerList = trainerListController.getInstance();
		init(context);
	}

	private void init(Context context) {
		locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		lastLocation = locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		if (lastLocation == null
				|| locationManager
						.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			Toast toast = Toast.makeText(context, gps_lost_info,
					Toast.LENGTH_SHORT);
			toast.show();
			locationManager.removeUpdates((LocationListener) this);
		}

	}

	public void start() {
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				UPDATE_TIME, UPDATE_DISTANCE, this);
	}

	public void stop() {
		locationManager.removeUpdates(this);
	}

	public Location getLastKnownLocation() {
		return lastLocation;
	}

	private void updateNavigationInformation() {
		buildingDistances = new ArrayList<Float>();
		trainerDistances = new ArrayList<Float>();
		trainerList = trainerListController.getInstance();
		trainerOne = new Location("");
		trainerTwo = new Location("");
		trainerThree = new Location("");
		trainerFour = new Location("");
		trainerFive = new Location("");
		trainerSix = new Location("");
		trainerBoss = new Location("");
		shop = new Location("");
		dompteur = new Location("");
		hospital = new Location("");
		setTrainerOneCoords();
		setTrainerTwoCoords();
		setTrainerThreeCoords();
		setTrainerFourCoords();
		setTrainerFiveCoords();
		setTrainerSixCoords();
		setTrainerBossCoords();
		setShopCoords();
		setDompteurCoords();
		setHospitalCoords();
		if (navigationListener == null || shop == null || dompteur == null

		|| hospital == null || trainerOne == null || trainerTwo == null
				|| trainerThree == null || trainerFour == null
				|| trainerFive == null || trainerSix == null
				|| trainerBoss == null) {

			return;
		}
		double latitude = lastLocation.getLatitude();
		double longitude = lastLocation.getLongitude();
		float distanceShop = lastLocation.distanceTo(shop);
		float distanceDompteur = lastLocation.distanceTo(dompteur);
		float distanceHospital = lastLocation.distanceTo(hospital);
		buildingDistances.add(distanceShop);
		buildingDistances.add(distanceDompteur);
		buildingDistances.add(distanceHospital);
		

		float distanceTrainerOne = lastLocation.distanceTo(trainerOne);
		float distanceTrainerTwo = lastLocation.distanceTo(trainerTwo);
		float distanceTrainerThree = lastLocation.distanceTo(trainerThree);
		float distanceTrainerFour = lastLocation.distanceTo(trainerFour);
		float distanceTrainerFive = lastLocation.distanceTo(trainerFive);
		float distanceTrainerSix = lastLocation.distanceTo(trainerSix);
		float distanceTrainerBoss = lastLocation.distanceTo(trainerBoss);
		trainerDistances.add(distanceTrainerOne);
		trainerDistances.add(distanceTrainerTwo);
		trainerDistances.add(distanceTrainerThree);
		trainerDistances.add(distanceTrainerFour);
		trainerDistances.add(distanceTrainerFive);
		trainerDistances.add(distanceTrainerSix);
		trainerDistances.add(distanceTrainerBoss);

		playerPosDetail = new PlayerPositionDetail(latitude, longitude,
				buildingDistances,
				trainerDistances);

		navigationListener.onPlayerPositionDetailChanged(playerPosDetail);
	}

	private void setTrainerOneCoords() {
		trainerOne.setLatitude(trainerList.getTrainerList().get(0)
				.getLatitude());
		trainerOne.setLongitude(trainerList.getTrainerList().get(0)
				.getLongitude());
	}

	private void setTrainerTwoCoords() {
		trainerTwo.setLatitude(trainerList.getTrainerList().get(1)
				.getLatitude());
		trainerTwo.setLongitude(trainerList.getTrainerList().get(1)
				.getLongitude());
	}

	private void setTrainerThreeCoords() {
		trainerThree.setLatitude(trainerList.getTrainerList().get(2)
				.getLatitude());
		trainerThree.setLongitude(trainerList.getTrainerList().get(2)
				.getLongitude());
	}

	private void setTrainerFourCoords() {
		trainerFour.setLatitude(trainerList.getTrainerList().get(3)
				.getLatitude());
		trainerFour.setLongitude(trainerList.getTrainerList().get(3)
				.getLongitude());
	}

	private void setTrainerFiveCoords() {
		trainerFive.setLatitude(trainerList.getTrainerList().get(4)
				.getLatitude());
		trainerFive.setLongitude(trainerList.getTrainerList().get(4)
				.getLongitude());
	}

	private void setTrainerSixCoords() {
		trainerSix.setLatitude(trainerList.getTrainerList().get(5)
				.getLatitude());
		trainerSix.setLongitude(trainerList.getTrainerList().get(5)
				.getLongitude());
	}

	private void setTrainerBossCoords() {
		trainerBoss.setLatitude(trainerList.getTrainerList().get(6)
				.getLatitude());
		trainerBoss.setLongitude(trainerList.getTrainerList().get(6)
				.getLongitude());
	}

	private void setShopCoords() {
		shop.setLatitude(49.0001500);
		shop.setLongitude(12.0942936);
	}

	private void setDompteurCoords() {
		dompteur.setLatitude(48.9992075);
		dompteur.setLongitude(12.095735);
	}

	private void setHospitalCoords() {
		hospital.setLatitude(48.9981304);
		hospital.setLongitude(12.0932311);
	}

	@Override
	public void onLocationChanged(Location location) {
		Log.d("hoi", "onLocationChanged");
		lastLocation = location;
		updateNavigationInformation();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

}
