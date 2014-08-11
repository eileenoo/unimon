package de.ur.unimon.mapoverview;

import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.actionbar.InventoryActivity;
import de.ur.unimon.actionbar.UnimonListActivity;
import de.ur.unimon.appstart.GuideScreenActivity;
import de.ur.unimon.appstart.OptionsScreenActivity;
import de.ur.unimon.appstart.StartScreenActivity;
import de.ur.unimon.start.newgame.NewGameActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MapActivity extends Activity {
	
	Button inventory;
	Button unimons;
	Button map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_activity);
		initUI();

	}

	private void initUI() {
		inventory = (Button) findViewById(R.id.inventory);
		unimons = (Button) findViewById(R.id.unimons);
		map = (Button) findViewById(R.id.map_overview);
		setButtonsOnClick();
	}

	private void setButtonsOnClick() {
		inventory.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent inventory = new Intent(MapActivity.this,
						InventoryActivity.class);
				startActivity(inventory);
			}
		});
		unimons.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent unimons = new Intent(MapActivity.this,
						UnimonListActivity.class);
				startActivity(unimons);
			}
		});
		map.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent map = new Intent(MapActivity.this,
						MapActivity.class);
				startActivity(map);
			}
		});
	}

}
