package de.ur.unimon.battle;

import java.util.ArrayList;

import de.ur.mi.android.excercises.starter.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import de.ur.unimon.mapoverview.MapActivity;
import de.ur.unimon.startgame_logic.PlayerListener;
import de.ur.unimon.unimons.Spell;
import de.ur.unimon.unimons.Unimon;
import de.ur.unimon.unimons.UnimonList;

public class BattleActivity extends Activity {

	private Button changeUnimonButton, attackButton, useItemButton,
			escapeButton, healpotButton, uniballButton, unimonTwoButton, unimonThreeButton;
	private Unimon battleUnimon;
	private Toast toast;
	private BattleController battleController;
	private Intent map = new Intent(BattleActivity.this, MapActivity.class);
	private Intent intent = getIntent();
	private boolean gameWon = false;
	private Unimon[] currentBattleUnimonList = new Unimon[2];
	private int unimonTwoIndex = 0; 
	private int unimonThreeIndex = 1;
	
	// muss noch entfernt & ersetzt werden
	private PlayerListener playerListener = (PlayerListener) this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battle_activity);
		initButtons();
		initBattleController();
		initClickListeners();
	}

	private void initButtons() {
		attackButton = (Button) findViewById(R.id.battle_attack_button);
		useItemButton = (Button) findViewById(R.id.battle_use_item_button);
		escapeButton = (Button) findViewById(R.id.battle_escape_button);
		changeUnimonButton = (Button) findViewById(R.id.battle_change_unimon_button);
	}

	private void initBattleController() {
		Bundle extra = intent.getExtras();
		// �berpr�fen auf Richtigkeit :D XD -> cast funktioniert glaub ich nicht..
		ArrayList<Unimon> battleUnimonList = (ArrayList<Unimon>) extra
				.get("unimonList");

		// EnemyUnimon wird sp�ter auch �ber den Intent aus ChooseBattleUnimon
		// geholt, hier: Ersatzcode
		
		UnimonList listAllUnimons = new UnimonList();
		Unimon enemyUnimon = listAllUnimons.getUnimonList().get(0);

		battleUnimon = battleUnimonList.get(0);
		currentBattleUnimonList[0] = battleUnimonList.get(1);
		currentBattleUnimonList[1] = battleUnimonList.get(2);
		battleController = new BattleController(enemyUnimon, battleUnimon,
				currentBattleUnimonList);
	}

	private void initClickListeners() {
		attackButtonClicked();
		itemButtonClicked();
		escapeButtonClicked();
		changeUnimonButtonClicked();
	}

	private void escapeButtonClicked() {
		escapeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (battleController.escape()) {
					showToast(R.string.escape_toast_text);
					startActivity(map);
				} else {
					showToast(R.string.escape_not_successfull_toast_text);
				}
			}
		});
	}

	private void changeUnimonButtonClicked() {
		changeUnimonButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				unimonTwoButton = (Button) findViewById(R.id.battle_unimon_two_button);
				unimonThreeButton = (Button) findViewById(R.id.battle_unimon_three_button);
				
				String unimonTwoButtonText = currentBattleUnimonList[0].getName();
				String unimonThreeButtonText = currentBattleUnimonList[1].getName();
				
				unimonTwoButton.setText(unimonTwoButtonText);
				unimonThreeButton.setText(unimonThreeButtonText);
				
				clickToChangeUnimon(unimonTwoButton, unimonTwoIndex);
				clickToChangeUnimon(unimonThreeButton, unimonThreeIndex);
			}
		});
	}
	
	private void clickToChangeUnimon(Button unimonNameButton,
			final int unimonNameIndex) {
		unimonNameButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				battleController.changeCurrentUnimon(unimonNameIndex);
				currentBattleUnimonList[unimonNameIndex] = battleUnimon;
			}
		});
	}

	private void attackButtonClicked() {
		attackButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showSpellList();
			}
		});

	}

	private void showSpellList() {
		ArrayList<Spell> ownedSpells = battleUnimon.getOwnedSpells();
		int numOwnedSpells = ownedSpells.size();
		for (int i = 0; i < numOwnedSpells; i++) {
			// Erzeugen einer Zeile
			// Name der Zeile (Das was angezeigt wird) = Name des Spells
			// Vllt Besser ein ListView?
		}
		// Spell currentSpell = onClickListenerReadIndexBla();
		// battleController.ownUnimonAttack(currentSpell);
		//
	}

	private void itemButtonClicked() {
		healpotButton = (Button) findViewById(R.id.battle_healpot_button);
		uniballButton = (Button) findViewById(R.id.battle_uniball_button);

		// Abfrage ob ein Healpot im Inventar verf�gbar ist
		if (playerListener.onHealPotAvailable()) {
			healpotButton.setClickable(true);
		} else {
			healpotButton.setClickable(false);
		}

		// Abfrage ob ein Uniball im Inventar verf�gbar ist
		if (playerListener.onUniBallAvailable()) {
			uniballButton.setClickable(true);
		} else {
			uniballButton.setClickable(false);
		}

		healpotButtonClicked();
		uniballButtonClicked();
	}

	// Wird aufgerufen, nachdem eine Aktion durchgef�hrt wurde, mit einem Delay
	// von ca.2 Sec
	private void enemyFight() {

	}

	private void fightEnd() {
		if (gameWon) {
			showToast(R.string.battle_won);
		} else {
			showToast(R.string.battle_lost);
		}
		startActivity(map);
	}

	// Uniball Count wird heruntergez�hlt & im BattleController wird das
	// gefangene Unimon zur ownUnimonList hinzugef�gt (wenn es gefangen wurde)
	private void uniballButtonClicked() {
		uniballButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				playerListener.onUniBallCountChange();
				if (battleController.catchUnimon()) {
					battleController.unimonCatchSuccess();
					showToast(R.string.catch_unimon_success_text);
					startActivity(map);
				} else {
					showToast(R.string.catch_unimon_fail_text);
				}
			}
		});
	}

	// HealpotCount wird im BattleController �ber Playerlistener den
	// heruntergezt�hlt
	private void healpotButtonClicked() {
		healpotButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				battleController.useHealpot();
			}
		});
	}

	private void showToast(int toastText) {
		int duration = Toast.LENGTH_SHORT;
		toast = Toast.makeText(this, toastText, duration);
		toast.show();
	}

}
