package de.ur.unimon.buildings;

import java.util.ArrayList;


import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.actionbar.UnimonListActivity;
import de.ur.unimon.actionbar.UnimonListAdapter;
import de.ur.unimon.actionbar.UnimonListDetailActivity;
import de.ur.unimon.appstart.StartScreenActivity;
import de.ur.unimon.mapoverview.MapActivity;
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
import android.widget.TextView;

public class DompteurActivity extends Activity{
	Player player;
	Button newSkill, improveSkill;
	TextView money, skillpoints, dompteurText;
	private ArrayList <Unimon> unimons;
	ListView listUnimons;
	ListAdapter listUnimons_adpater;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dompteur_activity);
		player = de.ur.unimon.appstart.StartScreenActivity.player;
		initUI();
}

	private void initUI() {
		unimons = de.ur.unimon.appstart.StartScreenActivity.player.getUnimonList();
		money = (TextView) findViewById(R.id.money);
		skillpoints = (TextView) findViewById(R.id.skillpoints);
		dompteurText = (TextView) findViewById(R.id.dompteur_text);
		initListAdapter();
		initListView();
	}

	private void initListView() {
		listUnimons.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Intent unimonDetail = new Intent(DompteurActivity.this,
						DompteurUnimonListActivity.class);
				Unimon chosenUnimon = (Unimon) listUnimons.getItemAtPosition(position);
				String chosenUnimonName = chosenUnimon.getName();
			//	Image chosenUnimmonImage = chosenUnimon.getImage();
				unimonDetail.putExtra("chosen_unimon_name", chosenUnimonName);
			//	unimonDetail.putExtra("chosen_unimon_image", chosenUnimonImage);
				startActivity(unimonDetail);								
								
				}
		});
	}
	
	
	private void initListAdapter() {
		listUnimons = (ListView) findViewById(R.id.listView_unimons);
		listUnimons_adpater = new UnimonListAdapter(this, unimons);
		listUnimons.setAdapter(listUnimons_adpater);
	}
	

}