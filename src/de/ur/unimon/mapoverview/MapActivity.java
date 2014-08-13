package de.ur.unimon.mapoverview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.DisplayMetrics;
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

public class MapActivity extends Activity implements NavigationListener{
	
	Button inventoryButton, unimonsButton, mapButton, movePlayerButton;
	Bitmap map;
	Bitmap player;
	public int playerXCoord, playerYCoord;
	LinearLayout canvasLayout;
	private NavigationController navigationController;
	private float playerLatitude, playerLongitude;

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
				Intent map = new Intent(MapActivity.this,
						MapActivity.class);
				startActivity(map);
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
			playerLatitude = playerPosDetail.getLatitude();
			playerLongitude = playerPosDetail.getLongitude();
		}

}
