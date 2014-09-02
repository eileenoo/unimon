package de.ur.unimon.mapoverview;

import java.util.ArrayList;
import java.util.Random;

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
import android.widget.Toast;
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.actionbar.InventoryActivity;
import de.ur.unimon.actionbar.UnimonListActivity;
import de.ur.unimon.battle.ChooseBattleUnimonsActivity;
import de.ur.unimon.battle.Trainer;
import de.ur.unimon.battle.TrainerList;
import de.ur.unimon.buildings.DompteurActivity;
import de.ur.unimon.buildings.ShopActivity;
import de.ur.unimon.navigation.NavigationController;
import de.ur.unimon.navigation.NavigationListener;
import de.ur.unimon.navigation.PlayerPositionDetail;

public class MapActivity extends Activity implements NavigationListener {

	Button inventoryButton, unimonsButton, mapButton, movePlayerButton;
	Bitmap map, player, trainer1, trainer2, trainer3, trainer4, trainer5,
			trainer6, trainerBoss;
	public int playerXCoord, playerYCoord;
	public int trainer1XCoord, trainer1YCoord;
	public int trainer2XCoord, trainer2YCoord;
	public int trainer3XCoord, trainer3YCoord;
	public int trainer4XCoord, trainer4YCoord;
	public int trainer5XCoord, trainer5YCoord;
	public int trainer6XCoord, trainer6YCoord;
	public int trainerBossXCoord, trainerBossYCoord;

	LinearLayout canvasLayout;
	private Random rnd;
	private NavigationController navigationController;
	private double playerLatitude, playerLongitude;
	private double trainer1Latitude, trainer1Longitude;
	private double trainer2Latitude, trainer2Longitude;
	private double trainer3Latitude, trainer3Longitude;
	private double trainer4Latitude, trainer4Longitude;
	private double trainer5Latitude, trainer5Longitude;
	private double trainer6Latitude, trainer6Longitude;
	private double trainerBossLatitude, trainerBossLongitude;
	public static final double leftUpperCornerLongitude = 12.091562;
	public static final double leftUpperCornerLatitude = 49.0010367;
	public static final double bottomRightCornerLongitude = 12.09969;
	public static final double bottomRightCornerLatitude = 48.99169;

	private double rangeBuildings = 25;
	private double rangeTrainer = 70;
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

	private ArrayList<Trainer> trainerList;
	private Toast playerOutOfRange;

	private FragmentManager fragmentManager;
	EnterAlertFragment alertFragment;

	AlertDialog.Builder builder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.map_activity);
		playerXCoord = playerYCoord = 0;
		builder = new AlertDialog.Builder(this);
		trainerList = new TrainerList().getTrainerList();

		initUI();
		initNavigation();
		initFragmentManager();
		getTrainerPositions();

		// onPlayerPositionDetailChanged(new PlayerPositionDetail(12, 48, 100,
		// 100, 1000, 5, 100, 100, 100, 100, 100, 100));
		// onPlayerPositionDetailChanged(new PlayerPositionDetail(12, 48, 100,
		// 25, 1000, 100, 100, 100, 100, 100, 100, 100));
		// onPlayerPositionDetailChanged(new PlayerPositionDetail(12, 48, 100,
		// 24, 1000, 100, 100, 100, 100, 100, 100, 100));
		// onPlayerPositionDetailChanged(new PlayerPositionDetail(12, 48, 100,
		// 13, 1000, 100, 100, 100, 100, 100, 100, 100));
		// closeFragment();
		// onPlayerPositionDetailChanged(new PlayerPositionDetail(12, 48, 100,
		// 30, 1000, 100, 100, 100, 100, 100, 100, 100));
		// onPlayerPositionDetailChanged(new PlayerPositionDetail(12, 48, 100,
		// 15, 1000, 100, 100, 100, 100, 100, 100, 100));

	}

	private void initFragmentManager() {
		fragmentManager = getFragmentManager();
		alertFragment = new EnterAlertFragment();
		// transaction = fragmentManager.beginTransaction();
		// transaction.setCustomAnimations(R.animator.slide_in_bottom,
		// R.animator.slide_out_top);
	}

	@Override
	protected void onResume() {
		navigationController.start();
		super.onResume();
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
				inventory.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
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
						ChooseBattleUnimonsActivity.class);
				startBattle.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startBattle.putExtra("trainerID", 1);
				startActivity(startBattle);
			}
		});

		movePlayerButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				/*
				 * Intent shop = new Intent(MapActivity.this,
				 * ShopActivity.class); startActivity(shop);
				 */

				Intent dompteur = new Intent(MapActivity.this,
						DompteurActivity.class);
				startActivity(dompteur);
			}
		});
	}

	private class MapView extends View {

		public MapView(Context context) {
			super(context);
			player = BitmapFactory.decodeResource(getResources(),
					R.drawable.blauer_punkt);

			trainer1 = BitmapFactory.decodeResource(getResources(),
					R.drawable.blauer_punkt);

			trainer2 = BitmapFactory.decodeResource(getResources(),
					R.drawable.blauer_punkt);

			trainer3 = BitmapFactory.decodeResource(getResources(),
					R.drawable.blauer_punkt);

			trainer4 = BitmapFactory.decodeResource(getResources(),
					R.drawable.blauer_punkt);

			trainer5 = BitmapFactory.decodeResource(getResources(),
					R.drawable.blauer_punkt);

			trainer6 = BitmapFactory.decodeResource(getResources(),
					R.drawable.blauer_punkt);

			trainerBoss = BitmapFactory.decodeResource(getResources(),
					R.drawable.blauer_punkt);

		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			canvas.drawBitmap(map, 0, 0, null);
			canvas.drawBitmap(player, playerXCoord, playerYCoord, null);
			if (trainerList.get(0).isSeen() == true) {
				canvas.drawBitmap(trainer1, trainer1XCoord, trainer1YCoord,
						null);
			}
			if (trainerList.get(1).isSeen() == true) {
				canvas.drawBitmap(trainer2, trainer2XCoord, trainer2YCoord,
						null);
			}
			if (trainerList.get(2).isSeen() == true) {
				canvas.drawBitmap(trainer3, trainer3XCoord, trainer3YCoord,
						null);
			}
			if (trainerList.get(3).isSeen() == true) {
				canvas.drawBitmap(trainer4, trainer4XCoord, trainer4YCoord,
						null);
			}
			if (trainerList.get(4).isSeen() == true) {
				canvas.drawBitmap(trainer5, trainer5XCoord, trainer5YCoord,
						null);
			}
			if (trainerList.get(5).isSeen() == true) {
				canvas.drawBitmap(trainer6, trainer6XCoord, trainer6YCoord,
						null);
			}
			if (trainerList.get(6).isSeen() == true) {
				canvas.drawBitmap(trainerBoss, trainerBossXCoord,
						trainerBossYCoord, null);
			}
			invalidate();
		}
	}

	@Override
	public void onPlayerPositionDetailChanged(
			PlayerPositionDetail playerPosDetail) {

		if (isPlayerInMapRange(playerPosDetail) == true) {

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

			playerYCoord = (int) (Math.abs(playerLatitude
					- leftUpperCornerLatitude) / helpVarY);
			// gettingAttacked();

			checkRangeTrue(playerPosDetail);
			checkRangeFalse(playerPosDetail);
		}
		else {
			playerOutOfRange = Toast.makeText(getApplicationContext(),
					getResources().getString(R.string.playerOutOfRange_info),
					Toast.LENGTH_LONG);
			playerOutOfRange.show();
		}
	}

	private boolean isPlayerInMapRange(PlayerPositionDetail playerPosDetail) {
		playerLongitude = playerPosDetail.getLongitude();
		playerLatitude = playerPosDetail.getLatitude();
		if (leftUpperCornerLongitude > playerLongitude
				|| bottomRightCornerLongitude < playerLongitude
				|| leftUpperCornerLatitude < playerLatitude
				|| bottomRightCornerLatitude > playerLatitude) {
			return false;
		}
		return true;
	}

	public void getTrainerPositions() {
		double diffX1 = Math.abs(bottomRightCornerLongitude
				- leftUpperCornerLongitude);
		double helpVarX1 = diffX1 / PIXEL_X;
		double diffY1 = Math.abs(bottomRightCornerLatitude
				- leftUpperCornerLatitude);
		double helpVarY1 = diffY1 / PIXEL_Y;

		trainer1Longitude = trainerList.get(0).getLongitude();
		trainer1Latitude = trainerList.get(0).getLatitude();
		trainer1XCoord = (int) (Math.abs(trainer1Longitude
				- leftUpperCornerLongitude) / helpVarX1);
		trainer1YCoord = (int) (Math.abs(trainer1Latitude
				- leftUpperCornerLatitude) / helpVarY1);

		trainer2Longitude = trainerList.get(1).getLongitude();
		trainer2Latitude = trainerList.get(1).getLatitude();
		trainer2XCoord = (int) (Math.abs(trainer2Longitude
				- leftUpperCornerLongitude) / helpVarX1);
		trainer2YCoord = (int) (Math.abs(trainer2Latitude
				- leftUpperCornerLatitude) / helpVarY1);

		trainer3Longitude = trainerList.get(2).getLongitude();
		trainer3Latitude = trainerList.get(2).getLatitude();
		trainer3XCoord = (int) (Math.abs(trainer3Longitude
				- leftUpperCornerLongitude) / helpVarX1);
		trainer3YCoord = (int) (Math.abs(trainer3Latitude
				- leftUpperCornerLatitude) / helpVarY1);

		trainer4Longitude = trainerList.get(3).getLongitude();
		trainer4Latitude = trainerList.get(3).getLatitude();
		trainer4XCoord = (int) (Math.abs(trainer4Longitude
				- leftUpperCornerLongitude) / helpVarX1);
		trainer4YCoord = (int) (Math.abs(trainer4Latitude
				- leftUpperCornerLatitude) / helpVarY1);

		trainer5Longitude = trainerList.get(4).getLongitude();
		trainer5Latitude = trainerList.get(4).getLatitude();
		trainer5XCoord = (int) (Math.abs(trainer5Longitude
				- leftUpperCornerLongitude) / helpVarX1);
		trainer5YCoord = (int) (Math.abs(trainer5Latitude
				- leftUpperCornerLatitude) / helpVarY1);

		trainer6Longitude = trainerList.get(5).getLongitude();
		trainer6Latitude = trainerList.get(5).getLatitude();
		trainer6XCoord = (int) (Math.abs(trainer6Longitude
				- leftUpperCornerLongitude) / helpVarX1);
		trainer6YCoord = (int) (Math.abs(trainer6Latitude
				- leftUpperCornerLatitude) / helpVarY1);

		trainerBossLongitude = trainerList.get(6).getLongitude();
		trainerBossLatitude = trainerList.get(6).getLatitude();
		trainerBossXCoord = (int) (Math.abs(trainerBossLongitude
				- leftUpperCornerLongitude) / helpVarX1);
		trainerBossYCoord = (int) (Math.abs(trainerBossLatitude
				- leftUpperCornerLatitude) / helpVarY1);

	}

	// private void gettingAttacked() {
	// int rand = rnd.nextInt(100);
	// if (rand == 0) {
	// Intent battleStart = new Intent(MapActivity.this,
	// ChooseBattleUnimonsActivity.class);
	// startActivity(battleStart);
	// }
	// }

	private void checkRangeTrue(PlayerPositionDetail playerPosDetail) {
		if (isShopInRange == true
				&& playerPosDetail.getDistanceToBuilding(0) >= rangeBuildings) {
			isShopInRange = false;
			closeFragment();
		}

		else if (isDompteurInRange == true
				&& playerPosDetail.getDistanceToBuilding(1) >= rangeBuildings) {
			isDompteurInRange = false;
			closeFragment();
		}

		else if (isHospitalInRange == true
				&& playerPosDetail.getDistanceToBuilding(2) >= rangeBuildings) {
			isHospitalInRange = false;
			closeFragment();
		} else if (isTrainerOneInRange == true
				&& playerPosDetail.getDistanceToTrainer(0) >= rangeTrainer) {
			isTrainerOneInRange = false;
			closeFragment();
		} else if (isTrainerTwoInRange == true
				&& playerPosDetail.getDistanceToTrainer(1) >= rangeTrainer) {
			isTrainerTwoInRange = false;
			closeFragment();
		}

		else if (isTrainerThreeInRange == true
				&& playerPosDetail.getDistanceToTrainer(2) >= rangeTrainer) {
			isTrainerThreeInRange = false;
			closeFragment();
		}

		else if (isTrainerFourInRange == true
				&& playerPosDetail.getDistanceToTrainer(3) >= rangeTrainer) {
			isTrainerFourInRange = false;
			closeFragment();
		}

		else if (isTrainerFiveInRange == true
				&& playerPosDetail.getDistanceToTrainer(4) >= rangeTrainer) {
			isTrainerFiveInRange = false;
			closeFragment();
		}

		else if (isTrainerSixInRange == true
				&& playerPosDetail.getDistanceToTrainer(5) >= rangeTrainer) {
			isTrainerSixInRange = false;
			closeFragment();
		}

		else if (isTrainerBossInRange == true
				&& playerPosDetail.getDistanceToTrainer(6) >= rangeTrainer) {
			isTrainerBossInRange = false;
			closeFragment();
		}

	}

	private void closeFragment() {
		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		transaction.setCustomAnimations(R.animator.slide_in_bottom,
				R.animator.slide_out_top);
		transaction.remove(alertFragment).commit();
	}

	private void checkRangeFalse(PlayerPositionDetail playerPosDetail) {
		if (isShopInRange == false
				&& playerPosDetail.getDistanceToBuilding(0) < rangeBuildings) {
			isShopInRange = true;

			showFragmentForBuildings("Shop");
		} else if (isDompteurInRange == false
				&& playerPosDetail.getDistanceToBuilding(1) < rangeBuildings) {
			isDompteurInRange = true;
			showFragmentForBuildings("Dompteur");
		} else if (isHospitalInRange == false
				&& playerPosDetail.getDistanceToBuilding(2) < rangeBuildings) {
			isHospitalInRange = true;
			showFragmentForBuildings("Hospital");
		} else if (isTrainerOneInRange == false
				&& playerPosDetail.getDistanceToTrainer(0) < rangeTrainer) {
			trainerList.get(0).setVisible();
			isTrainerOneInRange = true;
			showFragmentForTrainer("Trainer", 0);
		} else if (isTrainerTwoInRange == false
				&& playerPosDetail.getDistanceToTrainer(1) < rangeTrainer) {
			trainerList.get(1).setVisible();
			isTrainerTwoInRange = true;
			showFragmentForTrainer("Trainer", 1);
		} else if (isTrainerThreeInRange == false
				&& playerPosDetail.getDistanceToTrainer(2) < rangeTrainer) {
			trainerList.get(2).setVisible();
			isTrainerThreeInRange = true;
			showFragmentForTrainer("Trainer", 2);
		} else if (isTrainerFourInRange == false
				&& playerPosDetail.getDistanceToTrainer(3) < rangeTrainer) {
			trainerList.get(3).setVisible();
			isTrainerFourInRange = true;
			showFragmentForTrainer("Trainer", 3);
		} else if (isTrainerFiveInRange == false
				&& playerPosDetail.getDistanceToTrainer(4) < rangeTrainer) {
			trainerList.get(4).setVisible();
			isTrainerFiveInRange = true;
			showFragmentForTrainer("Trainer", 4);
		} else if (isTrainerSixInRange == false
				&& playerPosDetail.getDistanceToTrainer(5) < rangeTrainer) {
			trainerList.get(5).setVisible();
			isTrainerSixInRange = true;
			showFragmentForTrainer("Trainer", 5);
		} else if (isTrainerBossInRange == false
				&& playerPosDetail.getDistanceToTrainer(6) < rangeTrainer) {
			trainerList.get(6).setVisible();
			isTrainerBossInRange = true;
			showFragmentForTrainer("Trainer", 6);
		}

	}

	private void showFragmentForTrainer(String building, int trainerID) {
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
		extras.putString("building", building);
		extras.putInt("trainerID", trainerID);
		alertFragment.setArguments(extras);
		transaction.commit();
	}

	private void showFragmentForBuildings(String building) {
		// EnterAlertFragment alertFragment = new EnterAlertFragment();
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
		extras.putString("building", building);

		alertFragment.setArguments(extras);
		transaction.commit();
	}
}
