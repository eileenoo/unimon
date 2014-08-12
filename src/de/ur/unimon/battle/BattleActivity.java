package de.ur.unimon.battle;

import java.util.ArrayList;

import de.ur.mi.android.excercises.starter.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import de.ur.unimon.mapoverview.MapActivity;
import de.ur.unimon.unimons.Unimon;

public class BattleActivity extends Activity {
	private Unimon[] battleUnimons = new Unimon[3];
	private Unimon battleUnimon;
	private Unimon enemyUnimon;
	private BattleLogic battleLogic;
	private Toast toast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battle_activity);
		init();
	}

	private void init() {
		initBattleUnimonList();
		initBattleUnimon();
		initEnemyUnimon();
		initBattleLogic();
	}

	private void initEnemyUnimon() {
		ArrayList<Unimon> UnimonList = new ArrayList<Unimon>();
		enemyUnimon = UnimonList.get(0);
	}

	private void initBattleUnimon() {
		// battleUnimon = getIntent;
	}

	private void initBattleUnimonList() {
		// battleUnimons = getIntentbla;

	}

	private void initBattleLogic() {
		battleLogic = new BattleLogic(battleUnimon, enemyUnimon);
	}

	// Wird aufgerufen, wenn der Spieler versucht zu fliehen
	private void escape() {
		if (battleLogic.tryToEscape()) {
			showToast(R.string.escape_toast_text);
			Intent intent = new Intent(BattleActivity.this, MapActivity.class);
		} else {
			showToast(R.string.escape_not_successfull_toast_text);
		}
	}

	private void catchUnimon() {
		// Player mit Listener verändern?
		if (battleLogic.ableToCatchUnimon()) {
			// Unimons zur UnimonList des Players hinzufügen
			// UnimonList.add(enemyUnimon);
			// Uniball Potion runter zählen
			
			Intent map = new Intent(BattleActivity.this, MapActivity.class);
			startActivity(map);

			showToast(R.string.catch_unimon_success_text);
		} else {
			showToast(R.string.catch_unimon_fail_text);
		}
	}

	private void showToast(int toastText) {
		int duration = Toast.LENGTH_SHORT;
		toast = Toast.makeText(this, toastText, duration);
		toast.show();
	}

}
