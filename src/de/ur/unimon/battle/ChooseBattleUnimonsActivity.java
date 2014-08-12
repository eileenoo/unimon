package de.ur.unimon.battle;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.actionbar.UnimonListAdapter;
import de.ur.unimon.unimons.Unimon;

public class ChooseBattleUnimonsActivity extends Activity{
	
	ListView listUnimons;
	Button confirmButton;
	TextView chooseUnimonText;
	ListAdapter listUnimons_adpater;
	private ArrayList <Unimon> unimons = new ArrayList <Unimon>();
	private ArrayList <Unimon> chosenUnimons = new ArrayList<Unimon>();
	private int selectionStage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_battle_unimons_activity);
		
		initUI();
	}
	
	private void initUI() {
		initClickListener();
		initListAdapter();
		
	}
	
	private void initClickListener() {
		selectionStage=0;
		listUnimons = (ListView) findViewById(R.id.choose_unimon_listView);
		listUnimons.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				chosenUnimons.add(unimons.get(position));
				unimons.remove(position);
				selectionStage++;
				
				if (selectionStage == 1){
					chooseUnimonText.setText(R.string.choose_third_unimon);
				}
				if (selectionStage == 2){
					chooseUnimonText.setText(R.string.choose_third_unimon);
				}
				if (selectionStage == 3){
					Intent toBattleActivity = new Intent(ChooseBattleUnimonsActivity.this, BattleActivity.class);
					toBattleActivity.putExtra("chosenUnimons", chosenUnimons);
					startActivity(toBattleActivity);
					
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
