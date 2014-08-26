package de.ur.unimon.navigation;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class NavigationController implements LocationListener{
	
	public static final long UPDATE_TIME = 500;
	public static final float UPDATE_DISTANCE = 1;
	LocationManager locationManager;
	private Location lastLocation;
	Location shop, dompteur, hospital;
	
	private NavigationListener navigationListener;
	PlayerPositionDetail playerPosDetail;
	ProgressDialog progressDialog;
	private String gps_lost_info = "Searching for GPS-Signal";

	public NavigationController(Context context,NavigationListener navigationListener) {
		this.navigationListener = navigationListener;
		init(context);
	}

	private void init(Context context) {
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		if (lastLocation == null|| locationManager
						.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			Toast toast = Toast.makeText(context, gps_lost_info,
					Toast.LENGTH_LONG);
			toast.show();
			locationManager.removeUpdates((LocationListener) this);
		}

	}

	public void start() {
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, UPDATE_TIME, UPDATE_DISTANCE, this);
	}

	public void stop() {
		locationManager.removeUpdates(this);
	}

	public Location getLastKnownLocation() {
		return lastLocation;
	}
	
	private void updateNavigationInformation() {
		shop = new Location("");
		dompteur = new Location("");
		hospital = new Location("");
		setShopCoords();
		setDompteurCoords();
		setHospitalCoords();
		Log.d("hoi", "updateNavigationInformation");
		if (navigationListener == null || shop == null || dompteur == null || hospital == null) {
			return;
		}
		double latitude =  lastLocation.getLatitude();
		double longitude =  lastLocation.getLongitude();
		float distanceShop = lastLocation.distanceTo(shop);
		float distanceDompteur = lastLocation.distanceTo(dompteur);
		float distanceHospital = lastLocation.distanceTo(hospital);
		playerPosDetail = new PlayerPositionDetail (latitude, longitude, distanceShop, distanceDompteur, distanceHospital);
		navigationListener.onPlayerPositionDetailChanged(playerPosDetail);
	}

	
	private void setShopCoords(){
		shop.setLatitude(48.9977715); //richtige Koordinaten fehlen!!! 48.9977715
		Log.d("hoi", "shopLatitude " + shop.getLatitude());
		shop.setLongitude(12.0938617);//12.0938617
	}
	
	private void setDompteurCoords(){
		dompteur.setLatitude(48.9992075); //richtige Koordinaten fehlen!!! // 48.9992075
		dompteur.setLongitude(12.095735); //12.095735
	}
	
	private void setHospitalCoords(){
		hospital.setLatitude(48.9981304); //richtige Koordinaten fehlen!!! // 48.9981304
		hospital.setLongitude(12.0932311);// 12.0932311
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
