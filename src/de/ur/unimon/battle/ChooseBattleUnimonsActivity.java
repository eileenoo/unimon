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
import de.ur.unimon.startgame_logic.Player;
import de.ur.unimon.startgame_logic.PlayerController;
import de.ur.unimon.unimons.Unimon;

public class ChooseBattleUnimonsActivity extends Activity {

	public static final String TAG = "Hoi";

	ListView listUnimons;
	Button confirmButton;
	TextView chooseUnimonText;
	ListAdapter listUnimons_adpater;
	private ArrayList<Unimon> unimons;
	private Unimon[] chosenUnimons;
	private String[] chosenUnimonsStringArray;
	private int selectionStage, trainerID;
	private Player player;
	private PlayerController playerController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_battle_unimons_activity);
		getTrainer();
		initUI();
	}

	private void getTrainer() {
		Bundle extras = getIntent().getExtras();
		trainerID = extras.getInt("trainerID");
	}

	private void initUI() {
		player = playerController.getInstance();
		unimons = new ArrayList<Unimon>();
		for (Unimon unimon: player.getUnimonList()) {
			unimons.add((Unimon) unimon);
		}
		initListAdapter();
		chosenUnimons = new Unimon[3];
		chooseUnimonText = (TextView) findViewById(R.id.choose_unimon_for_battle_textView);
		initClickListener();

	}

	private void initClickListener() {
		selectionStage = 0;
		listUnimons.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				chosenUnimons[selectionStage] = unimons.get(position);
				Log.d("hallo", "pos: "+position);
				unimons.remove(position);
				selectionStage++;
				if (unimons.size() == 0) {
					selectionStage = 3;
				}

				if (selectionStage == 3) {
					Intent toBattleActivity = new Intent(
							ChooseBattleUnimonsActivity.this,
							BattleActivity.class);

					chosenUnimonsStringArray = new String[3];
					for (int i = 0; i < chosenUnimons.length; i++) {
						chosenUnimonsStringArray[i] = chosenUnimons[i].getName();
					}
					toBattleActivity.putExtra("trainerID", trainerID);
					toBattleActivity.putExtra("chosenUnimonStringArray", chosenUnimonsStringArray);
					
					// toBattleActivity.outExtra("trainer", trainer);
					startActivity(toBattleActivity);

				} else if (selectionStage == 1) {
					chooseUnimonText.setText(R.string.choose_second_unimon);
				} else if (selectionStage == 2) {
					chooseUnimonText.setText(R.string.choose_third_unimon);
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
