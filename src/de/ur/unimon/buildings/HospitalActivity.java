package de.ur.unimon.buildings;

import java.util.ArrayList;

import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.actionbar.InventoryActivity;
import de.ur.unimon.actionbar.InventoryUnimonListActivity;
import de.ur.unimon.actionbar.UnimonListAdapter;
import de.ur.unimon.startgame_logic.Player;
import de.ur.unimon.unimons.Unimon;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class HospitalActivity extends Activity{
	
	Button button;
	private ArrayList <Unimon> unimons;
	Player player;
	ListView listUnimons;
	ListAdapter listUnimons_adpater;
	HospitalLogic hospital;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hospital_activty);
		player = de.ur.unimon.appstart.StartScreenActivity.player;
		hospital = new HospitalLogic();
		initUI();

	}

	private void initUI() {
		unimons = de.ur.unimon.appstart.StartScreenActivity.player.getUnimonList();
		button = (Button) findViewById(R.id.button_healAll);
		button.setOnClickListener(new OnClickListener() { 
			
			@Override
			public void onClick(View v) {
				hospital.healAll(unimons);
				initListAdapter();
			}
		});
		
		initListAdapter();

	}

	private void initListAdapter() {
		listUnimons = (ListView) findViewById(R.id.listView_unimons);
		listUnimons_adpater = new UnimonListAdapter(this, unimons);
		listUnimons.setAdapter(listUnimons_adpater);
	}
	

}
