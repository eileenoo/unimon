package de.ur.unimon.mapoverview;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
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
import de.ur.unimon.appstart.StartScreenActivity;
import de.ur.unimon.battle.ChooseBattleUnimonsActivity;
import de.ur.unimon.database.DatabaseController;
import de.ur.unimon.navigation.NavigationController;
import de.ur.unimon.navigation.NavigationListener;
import de.ur.unimon.navigation.PlayerPositionDetail;
import de.ur.unimon.player.PlayerController;
import de.ur.unimon.trainer.Trainer;
import de.ur.unimon.trainer.TrainerListController;

public class MapActivity extends Activity implements NavigationListener,
		RangeListener {

	Button inventoryButton, unimonsButton, menuButton, saveButton;
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

	public float PIXEL_X; // 1559; //1169
	public float PIXEL_Y; // 2731; //2048

	private Toast toast;

	private ArrayList<Trainer> trainerList;
	private TrainerListController trainerListController;

	private int count;
	AlertDialog.Builder builder;

	RangeChecker rangeChecker;

	FragmentManager fragmentManager;
	EnterAlertFragment alertFragment;	
	DatabaseController controller;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.map_activity);
		playerXCoord = playerYCoord = 0;
		trainerList = trainerListController.getInstance().getTrainerList();
		builder = new AlertDialog.Builder(this);
		rnd = new Random();
		rangeChecker = new RangeChecker();

		initUI();
		initNavigation();
		initFragmentManager();
		getTrainerPositions();
		
		controller = new DatabaseController(this);

	}

	private void initFragmentManager() {
		fragmentManager = getFragmentManager();
		alertFragment = new EnterAlertFragment();

	}

	@Override
	public void onBackPressed() {
		// Hardware Zurückbutton disabled
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
		rangeChecker.setOnRangeChecker(this);

		canvasLayout = (LinearLayout) findViewById(R.id.canvas_layout);
		inventoryButton = (Button) findViewById(R.id.inventory);
		unimonsButton = (Button) findViewById(R.id.unimons);
		menuButton = (Button) findViewById(R.id.back_to_start_screen);
		saveButton = (Button) findViewById(R.id.save_button);
		map = BitmapFactory.decodeResource(getResources(), R.drawable.map);
		MapView canvasMap = new MapView(this);

		playerXCoord = playerYCoord = 0;
		count = 0;

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
		menuButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent backToStart = new Intent(MapActivity.this,
						StartScreenActivity.class);
				backToStart.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(backToStart);
			}
		});
		saveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.save(PlayerController.getInstance());
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

		if (rangeChecker.isPlayerInMapRange(playerPosDetail) == true) {
			Log.d("hallo", "onPlayerPositionDetailChanged aufgerufen");
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

			startRandomUnimonBattle(count);
			
			rangeChecker.isPlayerInTrainerRange(playerPosDetail);
			rangeChecker.onBuildingIsInRange(playerPosDetail);
			rangeChecker.onBuildingIsNotInRange(playerPosDetail);
			rangeChecker.onPlayerIsInBattleRange(playerPosDetail);
			rangeChecker.onPlayerIsNotInBattleRange(playerPosDetail);

		} else {
			showToast(R.string.playerOutOfRange_info);
		}
		count++;
		Log.d("hallo", "Count: " + count);
	}

	private void startRandomUnimonBattle(int count) {
		Log.d("hallo", "startRandomUnimonBattle");
		if (count % 24 == 0) {
			gettingAttacked();
		} else
			return;
	}

	private void gettingAttacked() {
		int rand = rnd.nextInt(10);
		if (rand == 1 && InventoryActivity.isProtectorActive == false) {
			Log.d("hallo", "Kampf start");
			builder.setTitle(getResources().getString(
					R.string.alert_random_unimon_battle_title));
			builder.setMessage(getResources().getString(
					R.string.alert_random_unimon_battle_message));

			builder.setPositiveButton(
					getResources().getString(
							R.string.start_random_unimon_battle),
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent startRandomUnimonBattle = new Intent(
									MapActivity.this,
									ChooseBattleUnimonsActivity.class);
							startRandomUnimonBattle.putExtra("trainerID", 1);
							startActivity(startRandomUnimonBattle);
							dialog.dismiss();
						}
					});
			AlertDialog randomUnimonBattleAlert = builder.create();
			randomUnimonBattleAlert.show();
			randomUnimonBattleAlert.setCancelable(false);
		} else if (rand == 1 && InventoryActivity.isProtectorActive == true) {
			Log.d("hallo", "Kampf abgewehrt");
			showToast(R.string.protector_used_toast);
			InventoryActivity.isProtectorActive = false;
		}
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

	private void showToast(int toastText) {
		int duration = Toast.LENGTH_LONG;
		toast = Toast.makeText(this, toastText, duration);
		toast.show();
	}

	@Override
	public void onShowFragmentForBuilding(String building) {
		Log.d("hallo", "onShowFragmentForBuilding");
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

	@Override
	public void onShowFragmentForTrainer(String building, int trainerID) {
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

	@Override
	public void onCloseFragment() {
		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		transaction.setCustomAnimations(R.animator.slide_in_bottom,
				R.animator.slide_out_top);
		transaction.remove(alertFragment).commit();

	}

	@Override
	public void onTrainerVisibilityChanged(ArrayList<Trainer> trainerList) {
		Log.d("hallo", "onvisi");
			this.trainerList = trainerList;
	}

}
