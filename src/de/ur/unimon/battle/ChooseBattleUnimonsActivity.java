package de.ur.unimon.battle;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.actionbar.UnimonListAdapter;
import de.ur.unimon.mapoverview.MapActivity;
import de.ur.unimon.startgame_logic.Player;
import de.ur.unimon.unimons.Unimon;

public class ChooseBattleUnimonsActivity extends Activity{
	
	public static final String TAG = "Hoi";
	
	ListView listUnimons;
	Button confirmButton;
	TextView chooseUnimonText;
	ListAdapter listUnimons_adpater;
	private ArrayList <Unimon> unimons;
	private Unimon[] chosenUnimons;
	private int selectionStage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_battle_unimons_activity);
		
		initUI();
	}
	
	private void initUI() {
		Player player = de.ur.unimon.appstart.StartScreenActivity.player;
		unimons = new ArrayList<Unimon>();
		for (Unimon unimon: player.getUnimonList()){
			unimons.add((Unimon) unimon);
		}
		initListAdapter();
		chosenUnimons = new Unimon[3];
		chooseUnimonText = (TextView) findViewById(R.id.choose_unimon_for_battle_textView);
		initClickListener();		
		
	}
	
	private void initClickListener() {
		Log.d(TAG, "imclicklistener");
		selectionStage=0;
		listUnimons.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Log.d(TAG, "click");
				
				chosenUnimons[selectionStage] = unimons.get(position);
				unimons.remove(position);
				selectionStage++;
				
				if (selectionStage == 1){
					chooseUnimonText.setText(R.string.choose_second_unimon);
				}
				else if (selectionStage == 2){
					chooseUnimonText.setText(R.string.choose_third_unimon);
				}
				else if (selectionStage == 3){
					Intent toBattleActivity = new Intent(ChooseBattleUnimonsActivity.this, BattleActivity.class);
					toBattleActivity.putExtra("chosenUnimons", chosenUnimons);
					//startActivity(toBattleActivity);
					Intent backToMap = new Intent(ChooseBattleUnimonsActivity.this, MapActivity.class);
					startActivity(backToMap);
					
				}
			}
		});
		
		
	}

	private void initListAdapter() {
		listUnimons = (ListView) findViewById(R.id.choose_unimon_listView);
		listUnimons_adpater = new UnimonListAdapter(this, unimons);
		listUnimons.setAdapter(listUnimons_adpater);
	}
	

}
