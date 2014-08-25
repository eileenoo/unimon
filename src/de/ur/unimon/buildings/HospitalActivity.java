package de.ur.unimon.buildings;

import java.util.ArrayList;

import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.actionbar.InventoryActivity;
import de.ur.unimon.actionbar.InventoryUnimonListActivity;
import de.ur.unimon.startgame_logic.Player;
import de.ur.unimon.unimons.Unimon;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HospitalActivity extends Activity{
	
	Button button;
	ArrayList<Unimon> list;
	Player player;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hospital_activty);
		initUI();

	}

	private void initUI() {
		button = (Button) findViewById(R.id.hospital);
		button.setOnClickListener(new OnClickListener() { 
			
			@Override
			public void onClick(View v) {
				list = player.ownUnimonList;
//				HospitalLogic.healAll(list);
				
				
			}
		});
	
	}

}
