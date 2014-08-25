package de.ur.unimon.mapoverview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.actionbar.InventoryActivity;
import de.ur.unimon.actionbar.UnimonListActivity;
import de.ur.unimon.navigation.NavigationController;
import de.ur.unimon.navigation.NavigationListener;
import de.ur.unimon.navigation.PlayerPositionDetail;
import de.ur.unimon.start.newgame.NewGameActivity;
import de.ur.unimon.appstart.StartScreenActivity;
import de.ur.unimon.battle.ChooseBattleUnimonsActivity;
import de.ur.unimon.battle.TrainerList;
import de.ur.unimon.buildings.DompteurActivity;
import de.ur.unimon.buildings.HospitalActivity;
import de.ur.unimon.buildings.ShopActivity;

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
	public static final float PIXEL_X = 1169;
	public static final float PIXEL_Y = 2048;
	private int shopXCoord, dompteurXCoord, hospitalXCoord;
	private int shopYCoord, dompteurYCoord, hospitalYCoord;
	private boolean shopRangeChecked = false;
	private boolean dompteurRangeChecked = false;
	private boolean hospitalRangeChecked = false;
	AlertDialog.Builder builder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_activity);
		playerXCoord = playerYCoord = 0;
		builder = new AlertDialog.Builder(this);
		initUI();
		initNavigation();
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
		int mapHeight = map.getHeight();
		Log.d("hallo", "mapHeight " + mapHeight);
		int mapWidth = map.getWidth();
		Log.d("hallo", "mapWidth " + mapWidth);
		MapView canvasMap = new MapView(this);
		canvasLayout.addView(canvasMap, map.getWidth(), map.getHeight());
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
						ChooseBattleUnimonsActivity.class);
				startActivity(startBattle);
			}
		});

		movePlayerButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent shop = new Intent(MapActivity.this, ShopActivity.class);
				startActivity(shop);
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
		if (shopRangeChecked == true && playerPosDetail.getDistanceShop() >= rangeBuildings) {
			shopRangeChecked = false;
		}

		else if (dompteurRangeChecked == true
				&& playerPosDetail.getDistanceDompteur() >= rangeBuildings) {
			dompteurRangeChecked = false;
		}

		else if (hospitalRangeChecked == true
				&& playerPosDetail.getDistanceHospital() >= rangeBuildings) {
			hospitalRangeChecked = false;
		}
	}

	private void checkRangeFalse(PlayerPositionDetail playerPosDetail) {
		if (shopRangeChecked == false && playerPosDetail.getDistanceShop() < rangeBuildings) {
			showShopAlert();
		} else if (dompteurRangeChecked == false
				&& playerPosDetail.getDistanceDompteur() < rangeBuildings) {
			showDompteurAlert();
		} else if (hospitalRangeChecked == false
				&& playerPosDetail.getDistanceHospital() < rangeBuildings) {
			showHospitalAlert();
		}
	}

	// Alert f�r Shop Activity

	private void showShopAlert() {

		builder.setTitle(R.string.shop_name);
		builder.setMessage("M�chtest du den Shop betreten?");

		builder.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						Intent shopActivity = new Intent(MapActivity.this,
								ShopActivity.class);
						startActivity(shopActivity);

						dialog.dismiss();
					}

				});

		builder.setNegativeButton(R.string.cancel,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						shopRangeChecked = true;
						dialog.dismiss();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}

	// Alert f�r Dompteur Activity

	private void showDompteurAlert() {
		builder.setTitle(R.string.dompteur_name);
		builder.setMessage("M�chtest du den Dompteur besuchen?");

		builder.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						Intent dompteurActivity = new Intent(MapActivity.this,
								DompteurActivity.class);
						startActivity(dompteurActivity);

						dialog.dismiss();
					}

				});

		builder.setNegativeButton(R.string.cancel,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dompteurRangeChecked = true;
						dialog.dismiss();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();

	}

	// Alert f�r Hospital Activity

	private void showHospitalAlert() {
		builder.setTitle(R.string.hospital_name);
		builder.setMessage("M�chtest du das Hospital betreten?");

		builder.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						Intent hospitalActivity = new Intent(MapActivity.this,
								HospitalActivity.class);
						startActivity(hospitalActivity);

						dialog.dismiss();
					}

				});

		builder.setNegativeButton(R.string.cancel,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						hospitalRangeChecked = true;
						dialog.dismiss();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();

	}
}
