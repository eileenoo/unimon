package de.ur.unimon.mapoverview;

import android.app.Activity;
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
import de.ur.unimon.navigation.NavigationController;
import de.ur.unimon.navigation.NavigationListener;
import de.ur.unimon.navigation.PlayerPositionDetail;

public class MapActivity extends Activity implements NavigationListener{
	
	Button inventoryButton, unimonsButton, mapButton, movePlayerButton;
	Bitmap map;
	Bitmap player;
	public int playerXCoord, playerYCoord;
	LinearLayout canvasLayout;
	private NavigationController navigationController;
	private double playerLatitude, playerLongitude;
	public static final double leftUpperCornerLongitude = 12.091562;
	public static final double leftUpperCornerLatitude = 49.0010367;
	public static final double bottomRightCornerLongitude = 12.09969;//x
	public static final double bottomRightCornerLatitude = 48.99169; //Latitude oben unten Breitengrad y
	public float PIXEL_X; //1559; //1169
	public float PIXEL_Y; //2731; //2048
	private int shopXCoord, dompteurXCoord;
	private int shopYCoord, dompteurYCoord;
	private boolean shopRangeChecked = false;
	private boolean dompteurRangeChecked = false;
	private EnterAlertFragment alertFragment;
	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_activity);
		initNavigation();
		initUI();
		initFragmentManager();	
		
		Log.d("hallo", ""+map.getWidth());
		Log.d("hallo", ""+map.getHeight());
		
		
		onPlayerPositionDetailChanged(new PlayerPositionDetail(48.99787089, 12.09574285));
	}
	
	private void initFragmentManager() {
		alertFragment = new EnterAlertFragment();
		fragmentManager = getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.add(R.id.map_activity_layout, alertFragment, "alertFragment");
		transaction.commit();
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
		setButtonsOnClick();
		
		map = BitmapFactory.decodeResource(getResources(),R.drawable.map);			
		MapView canvasMap = new MapView(this);		
		
		playerXCoord = playerYCoord = 0;
		
		canvasLayout.addView(canvasMap, map.getWidth(), map.getHeight());
		
		PIXEL_X = map.getWidth();
		PIXEL_Y = map.getHeight();
	}
	
	/**
	private int getWindowWidth() {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		return displaymetrics.widthPixels;		
	}
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
				playerXCoord += 20;
				playerYCoord += 50;
			}
		});
	}
	
	
		private class MapView extends View{						
	
			public MapView(Context context) {
				super(context);
				player = BitmapFactory.decodeResource(getResources(),R.drawable.blauer_punkt);
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
		public void onPlayerPositionDetailChanged(PlayerPositionDetail playerPosDetail) {
			double diffX = Math.abs(bottomRightCornerLongitude - leftUpperCornerLongitude);
			double helpVarX = diffX/PIXEL_X;
			double diffY = Math.abs(bottomRightCornerLatitude - leftUpperCornerLatitude);
			double helpVarY = diffY/PIXEL_Y;
			playerLongitude = playerPosDetail.getLongitude();
			Log.d("hallo", ""+playerLongitude);
			playerLatitude = playerPosDetail.getLatitude();	
			Log.d("hallo", ""+playerLatitude);
			playerXCoord = (int) (Math.abs(playerLongitude-leftUpperCornerLongitude)/helpVarX);
			playerYCoord = (int) (Math.abs(playerLatitude-leftUpperCornerLatitude)/helpVarY);
			Log.d("hallo", ""+playerXCoord);
			Log.d("hallo", ""+playerYCoord);		
		}
		

		/*private void checkIfShopIsInRange(int playerXCoord, int playerYCoord) {
			shopXCoord = 830;
			shopYCoord = 535;
			int playerXCoordMax = playerXCoord + 30;
			int playerXCoordMin = playerXCoord - 30;
			int playerYCoordMax = playerYCoord + 30;
			int playerYCoordMin = playerYCoord - 30;
			
			
			if(shopXCoord > playerXCoordMin &&  shopXCoord < playerXCoordMax && 
					shopYCoord > playerYCoordMin && shopYCoord < playerYCoordMax && shopRangeChecked == false){
				
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				 
				builder.setTitle("Shop");
				builder.setMessage("Möchtest du den Shop betreten?");
				 
				 
				builder.setPositiveButton("JA", new DialogInterface.OnClickListener() {
				 
				   @Override
				   public void onClick(DialogInterface dialog, int which) {
				         
					   Intent shopActivity = new Intent (MapActivity.this, ShopActivity.class);
					   startActivity(shopActivity);
				 
				        dialog.dismiss();
				   }
				 
				});
				 				 
				builder.setNegativeButton("NEIN", new DialogInterface.OnClickListener() {
				 
				   @Override
				   public void onClick(DialogInterface dialog, int which) {
				 
				        // Code der ausgeführt wird wenn NEIN geklickt wurde
					   shopRangeChecked = true;
				 
				        dialog.dismiss();
				   }
				 
				});				 				 
				AlertDialog alert = builder.create();
				alert.show();
				
			}
		}*/
		
		/*private void checkIfDompteurIsInRange(int playerXCoord,int playerYCoord) {
			
			dompteurXCoord = 830;
			dompteurYCoord = 535;
			int playerXCoordMax = playerXCoord + 30;
			int playerXCoordMin = playerXCoord - 30;
			int playerYCoordMax = playerYCoord + 30;
			int playerYCoordMin = playerYCoord - 30;
			
			
			if(dompteurXCoord > playerXCoordMin &&  dompteurXCoord < playerXCoordMax && 
					dompteurYCoord > playerYCoordMin && dompteurYCoord < playerYCoordMax && dompteurRangeChecked == false){
				
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				 
				builder.setTitle("Dompteur");
				builder.setMessage("Möchtest du den Dompteur besuchen?");
				 
				 
				builder.setPositiveButton("JA", new DialogInterface.OnClickListener() {
				 
				   @Override
				   public void onClick(DialogInterface dialog, int which) {
				         
					   Intent shopActivity = new Intent (MapActivity.this, ShopActivity.class);
					   startActivity(shopActivity);
				 
				        dialog.dismiss();
				   }
				 
				});
				 				 
				builder.setNegativeButton("NEIN", new DialogInterface.OnClickListener() {
				 
				   @Override
				   public void onClick(DialogInterface dialog, int which) {
				 
				        // Code der ausgeführt wird wenn NEIN geklickt wurde
					   dompteurRangeChecked = true;
				 
				        dialog.dismiss();
				   }
				 
				});				 				 
				AlertDialog alert = builder.create();
				alert.show();
				
			}
			
			
		}*/

}
