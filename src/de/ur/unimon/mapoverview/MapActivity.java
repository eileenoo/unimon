package de.ur.unimon.mapoverview;

import android.app.Activity;
import android.content.Context;
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
import de.ur.unimon.appstart.StartScreenActivity;
import de.ur.unimon.battle.ChooseBattleUnimonsActivity;
import de.ur.unimon.battle.TrainerList;

public class MapActivity extends Activity implements NavigationListener{
	
	Button inventoryButton, unimonsButton, mapButton, movePlayerButton;
	Bitmap map;
	Bitmap player;
	public int playerXCoord, playerYCoord;
	LinearLayout canvasLayout;
	private NavigationController navigationController;
	private double playerLatitude, playerLongitude;
	public static final double leftUpperCornerLongitude = 12.09154;
	public static final double leftUpperCornerLatitude = 49.00106;
	public static final double bottomRightCornerLongitude = 12.09969;
	public static final double bottomRightCornerLatitude = 48.99169; //Latitude oben unten
	public static final float PIXEL_X = 596;//1169;
	public static final float PIXEL_Y = 2048;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_activity);
		initNavigation();
		initUI();
			
		playerXCoord = playerYCoord = 0;
		
		map = BitmapFactory.decodeResource(getResources(),R.drawable.map);			
		MapView canvasMap = new MapView(this);				
		canvasLayout.addView(canvasMap, map.getWidth(), map.getHeight());	
		//onPlayerPositionDetailChanged(new PlayerPositionDetail(12.096143d,48.997719d));

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
				player = BitmapFactory.decodeResource(getResources(),R.drawable.player);
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
			double diffY = Math.abs(bottomRightCornerLongitude - leftUpperCornerLongitude);
			double helpVarY = diffY/PIXEL_Y;
			double diffX = Math.abs(bottomRightCornerLatitude - leftUpperCornerLatitude);
			double helpVarX = diffX/PIXEL_X;
			playerLatitude = playerPosDetail.getLatitude();
			playerLongitude = playerPosDetail.getLongitude();
			playerYCoord = (int) (Math.abs(playerLongitude-leftUpperCornerLatitude)/helpVarY);
			playerXCoord = (int) (Math.abs(playerLatitude-leftUpperCornerLongitude)/helpVarX);
			Log.d("hallo",""+playerXCoord);
			Log.d("hallo",""+playerYCoord);
			
		}

}
