package de.ur.unimon.mapoverview;

import android.app.Activity;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.actionbar.InventoryActivity;
import de.ur.unimon.actionbar.UnimonListActivity;
import de.ur.unimon.battle.ChooseBattleUnimonsActivity;
import de.ur.unimon.buildings.DompteurActivity;
import de.ur.unimon.navigation.NavigationController;
import de.ur.unimon.navigation.NavigationListener;
import de.ur.unimon.navigation.PlayerPositionDetail;

public class MapActivity extends Activity implements NavigationListener {

	Button inventoryButton, unimonsButton, mapButton, movePlayerButton;
	Bitmap map, player;
	public int playerXCoord, playerYCoord;

	LinearLayout canvasLayout;
	private NavigationController navigationController;
	private double playerLatitude, playerLongitude;
	public static final double leftUpperCornerLongitude = 12.091562;
	public static final double leftUpperCornerLatitude = 49.0010367;
	public static final double bottomRightCornerLongitude = 12.09969;// x
	public static final double bottomRightCornerLatitude = 48.99169; // Latitude
																		// oben
																		// unten
																		// Breitengrad
																		// y
	private double rangeBuildings = 25;
	private double rangeTrainer = 20;
	public float PIXEL_X; // 1559; //1169
	public float PIXEL_Y; // 2731; //2048

	private boolean isShopInRange = false;
	private boolean isDompteurInRange = false;
	private boolean isHospitalInRange = false;
	private boolean isTrainerOneInRange = false;
	private boolean isTrainerTwoInRange = false;
	private boolean isTrainerThreeInRange = false;
	private boolean isTrainerFourInRange = false;
	private boolean isTrainerFiveInRange = false;
	private boolean isTrainerSixInRange = false;
	private boolean isTrainerBossInRange = false;

	private FragmentManager fragmentManager;

	AlertDialog.Builder builder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_activity);
		playerXCoord = playerYCoord = 0;
		builder = new AlertDialog.Builder(this);
		initUI();
		initNavigation();
		initFragmentManager();
	}

	private void initFragmentManager() {
		fragmentManager = getFragmentManager();

	}

	@Override
	protected void onPause() {
		navigationController.stop();
		super.onPause();
	}

	private void initNavigation() {
		navigationController = new NavigationController(this, this);
		navigationController.start();

	}

	private void initUI() {
		canvasLayout = (LinearLayout) findViewById(R.id.canvas_layout);
		inventoryButton = (Button) findViewById(R.id.inventory);
		unimonsButton = (Button) findViewById(R.id.unimons);
		mapButton = (Button) findViewById(R.id.map_overview);
		movePlayerButton = (Button) findViewById(R.id.move_player_test_button);
		map = BitmapFactory.decodeResource(getResources(), R.drawable.map);
		MapView canvasMap = new MapView(this);

		playerXCoord = playerYCoord = 0;

		canvasLayout.addView(canvasMap, map.getWidth(), map.getHeight());

		PIXEL_X = map.getWidth();
		PIXEL_Y = map.getHeight();
		setButtonsOnClick();
	}

	/**
	 * private int getWindowWidth() { DisplayMetrics displaymetrics = new
	 * DisplayMetrics();
	 * getWindowManager().getDefaultDisplay().getMetrics(displaymetrics); return
	 * displaymetrics.widthPixels; }
	 **/

	private void setButtonsOnClick() {
		inventoryButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent inventory = new Intent(MapActivity.this,
						InventoryActivity.class);
				startActivity(inventory);
			}
		});
		unimonsButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent unimons = new Intent(MapActivity.this,
						UnimonListActivity.class);
				startActivity(unimons);
			}
		});
		mapButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent startBattle = new Intent(MapActivity.this,
						DompteurActivity.class);
				startActivity(startBattle);
			}
		});

		movePlayerButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent dompteur = new Intent(MapActivity.this,
						DompteurActivity.class);
				startActivity(dompteur);
				// playerXCoord += 20;
				// playerYCoord += 50;

			}
		});
	}

	private class MapView extends View {

		public MapView(Context context) {
			super(context);
			player = BitmapFactory.decodeResource(getResources(),
					R.drawable.blauer_punkt);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			canvas.drawBitmap(map, 0, 0, null);
			canvas.drawBitmap(player, playerXCoord, playerYCoord, null);
			invalidate();
		}
	}

	@Override
	public void onPlayerPositionDetailChanged(
			PlayerPositionDetail playerPosDetail) {
		double diffX = Math.abs(bottomRightCornerLongitude
				- leftUpperCornerLongitude);
		double helpVarX = diffX / PIXEL_X;
		double diffY = Math.abs(bottomRightCornerLatitude
				- leftUpperCornerLatitude);
		double helpVarY = diffY / PIXEL_Y;
		playerLongitude = playerPosDetail.getLongitude();
		playerLatitude = playerPosDetail.getLatitude();
		playerXCoord = (int) (Math.abs(playerLongitude
				- leftUpperCornerLongitude) / helpVarX);
		playerYCoord = (int) (Math
				.abs(playerLatitude - leftUpperCornerLatitude) / helpVarY);
		checkRangeTrue(playerPosDetail);
		checkRangeFalse(playerPosDetail);
	}

	private void checkRangeTrue(PlayerPositionDetail playerPosDetail) {
		if (isShopInRange == true
				&& playerPosDetail.getDistanceShop() >= rangeBuildings) {
			isShopInRange = false;
		}

		else if (isDompteurInRange == true
				&& playerPosDetail.getDistanceDompteur() >= rangeBuildings) {
			isDompteurInRange = false;
		}

		else if (isHospitalInRange == true
				&& playerPosDetail.getDistanceHospital() >= rangeBuildings) {
			isHospitalInRange = false;
		}

		else if (isTrainerThreeInRange == true
				&& playerPosDetail.getDistanceTrainerThree() >= rangeTrainer) {
			isTrainerThreeInRange = false;
		}

	}

	private void checkRangeFalse(PlayerPositionDetail playerPosDetail) {
		if (isShopInRange == false
				&& playerPosDetail.getDistanceShop() < rangeBuildings) {
			// showShopAlert();
			isShopInRange = true;
			showFragmentForBuildings("Shop");
		} else if (isDompteurInRange == false
				&& playerPosDetail.getDistanceDompteur() < rangeBuildings) {
			// showDompteurAlert();
			isDompteurInRange = true;
			showFragmentForBuildings("Dompteur");
		} else if (isHospitalInRange == false
				&& playerPosDetail.getDistanceHospital() < rangeBuildings) {
			// showHospitalAlert();
			isHospitalInRange = true;
			showFragmentForBuildings("Hospital");
		} else if (isTrainerOneInRange == false
				&& playerPosDetail.getDistanceTrainerOne() < rangeTrainer) {
			// showHospitalAlert();
			isTrainerOneInRange = true;
			Intent battleStart = new Intent(MapActivity.this,
					ChooseBattleUnimonsActivity.class);
			startActivity(battleStart);
		} else if (isTrainerTwoInRange == false
				&& playerPosDetail.getDistanceTrainerTwo() < rangeTrainer) {
			// showHospitalAlert();
			isTrainerTwoInRange = true;
			Intent battleStart = new Intent(MapActivity.this,
					ChooseBattleUnimonsActivity.class);
			startActivity(battleStart);
		}

		else if (isTrainerThreeInRange == false
				&& playerPosDetail.getDistanceTrainerThree() < rangeTrainer) {
			// showHospitalAlert();
			isTrainerThreeInRange = true;
			Intent battleStart = new Intent(MapActivity.this,
					ChooseBattleUnimonsActivity.class);
			startActivity(battleStart);
		}

		else if (isTrainerFourInRange == false
				&& playerPosDetail.getDistanceTrainerFour() < rangeTrainer) {
			// showHospitalAlert();
			isTrainerFourInRange = true;
			Intent battleStart = new Intent(MapActivity.this,
					ChooseBattleUnimonsActivity.class);
			startActivity(battleStart);
		}

		else if (isTrainerFiveInRange == false
				&& playerPosDetail.getDistanceTrainerFive() < rangeTrainer) {
			// showHospitalAlert();
			isTrainerFiveInRange = true;
			Intent battleStart = new Intent(MapActivity.this,
					ChooseBattleUnimonsActivity.class);
			startActivity(battleStart);
		}

		else if (isTrainerSixInRange == false
				&& playerPosDetail.getDistanceTrainerSix() < rangeTrainer) {
			// showHospitalAlert();
			isTrainerSixInRange = true;
			Intent battleStart = new Intent(MapActivity.this,
					ChooseBattleUnimonsActivity.class);
			startActivity(battleStart);
		}

		else if (isTrainerBossInRange == false
				&& playerPosDetail.getDistanceTrainerBoss() < rangeTrainer) {
			// showHospitalAlert();
			isTrainerBossInRange = true;
			Intent battleStart = new Intent(MapActivity.this,
					ChooseBattleUnimonsActivity.class);
			startActivity(battleStart);
		}
	}

	private void showFragmentForBuildings(String building) {
		EnterAlertFragment alertFragment = new EnterAlertFragment();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.setCustomAnimations(R.animator.slide_in_bottom,
				R.animator.slide_out_top);
		transaction.add(R.id.map_activity_layout, alertFragment,
				"alertFragment");
		Bundle extras = new Bundle();
		if (alertFragment.getArguments() != null) {
			extras = alertFragment.getArguments();
			extras.clear();
		}
		;
		extras.putString("building", building);
		alertFragment.setArguments(extras);
		transaction.commit();
	}

	// Alert für Shop Activity

	/*
	 * private void showShopAlert() {
	 * 
	 * builder.setTitle(getResources().getString(R.string.shop_name));
	 * builder.setMessage("Möchtest du den Shop betreten?");
	 * 
	 * builder.setPositiveButton(getResources().getString(R.string.ok), new
	 * DialogInterface.OnClickListener() {
	 * 
	 * @Override public void onClick(DialogInterface dialog, int which) {
	 * 
	 * Intent shopActivity = new Intent(MapActivity.this, ShopActivity.class);
	 * startActivity(shopActivity);
	 * 
	 * dialog.dismiss(); }
	 * 
	 * });
	 * 
	 * builder.setNegativeButton(getResources().getString(R.string.cancel), new
	 * DialogInterface.OnClickListener() {
	 * 
	 * @Override public void onClick(DialogInterface dialog, int which) {
	 * shopRangeChecked = true; dialog.dismiss(); } }); AlertDialog alert =
	 * builder.create(); alert.show(); }
	 * 
	 * // Alert für Dompteur Activity
	 * 
	 * private void showDompteurAlert() {
	 * builder.setTitle(getResources().getString(R.string.dompteur_name));
	 * builder.setMessage("Möchtest du den Dompteur besuchen?");
	 * 
	 * builder.setPositiveButton(getResources().getString(R.string.ok), new
	 * DialogInterface.OnClickListener() {
	 * 
	 * @Override public void onClick(DialogInterface dialog, int which) {
	 * 
	 * Intent dompteurActivity = new Intent(MapActivity.this,
	 * DompteurActivity.class); startActivity(dompteurActivity);
	 * 
	 * dialog.dismiss(); }
	 * 
	 * });
	 * 
	 * builder.setNegativeButton(getResources().getString(R.string.cancel), new
	 * DialogInterface.OnClickListener() {
	 * 
	 * @Override public void onClick(DialogInterface dialog, int which) {
	 * dompteurRangeChecked = true; dialog.dismiss(); } }); AlertDialog alert =
	 * builder.create(); alert.show();
	 * 
	 * }
	 * 
	 * // Alert für Hospital Activity
	 * 
	 * private void showHospitalAlert() {
	 * builder.setTitle(getResources().getString(R.string.hospital_name));
	 * builder.setMessage("Möchtest du das Hospital betreten?");
	 * 
	 * builder.setPositiveButton(getResources().getString(R.string.ok), new
	 * DialogInterface.OnClickListener() {
	 * 
	 * @Override public void onClick(DialogInterface dialog, int which) {
	 * 
	 * Intent hospitalActivity = new Intent(MapActivity.this,
	 * HospitalActivity.class); startActivity(hospitalActivity);
	 * 
	 * dialog.dismiss(); }
	 * 
	 * });
	 * 
	 * builder.setNegativeButton(getResources().getString(R.string.cancel), new
	 * DialogInterface.OnClickListener() {
	 * 
	 * @Override public void onClick(DialogInterface dialog, int which) {
	 * hospitalRangeChecked = true; dialog.dismiss(); } }); AlertDialog alert =
	 * builder.create(); alert.show();
	 * 
	 * }
	 */

}
