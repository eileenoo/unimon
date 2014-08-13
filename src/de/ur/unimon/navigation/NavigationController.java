package de.ur.unimon.navigation;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class NavigationController implements LocationListener{
	
	public static final long UPDATE_TIME = 500;
	public static final float UPDATE_DISTANCE = 1;
	LocationManager locationManager;
	private Location lastLocation;
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
		if (navigationListener == null) {
			return;
		}
		float latitude = (float) lastLocation.getLatitude();
		float longitude = (float) lastLocation.getLongitude();
		playerPosDetail = new PlayerPositionDetail (latitude, longitude);
		navigationListener.onPlayerPositionDetailChanged(playerPosDetail);
	}

	@Override
	public void onLocationChanged(Location location) {
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
