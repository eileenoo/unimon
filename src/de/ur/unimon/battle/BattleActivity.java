package de.ur.unimon.battle;

import java.util.ArrayList;

import de.ur.mi.android.excercises.starter.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import de.ur.unimon.mapoverview.MapActivity;
import de.ur.unimon.startgame_logic.PlayerListener;
import de.ur.unimon.unimons.Spell;
import de.ur.unimon.unimons.Unimon;

public class BattleActivity extends Activity {

	private Button changeUnimonButton, attackButton, useItemButton,
			escapeButton, healpotButton, uniballButton;
	private Unimon battleUnimon;
	private Toast toast;
	private BattleController battleController;
	private Intent map = new Intent(BattleActivity.this, MapActivity.class);
	// Ist der Listener jetzt initalisiert? Oder muss der Context der Klasse, in
	// der das Interface implementiert ist, Ÿbergeben werden?
	private PlayerListener playerListener = (PlayerListener) this;
	private boolean gameWon = false;

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
		// Unimon enemyUnimon = UnimonList.get(bestimmtesUnimon);
		// Unimon battleUnimon = getIntenBUndleExtraBla();
		// Unimon[] unimonList = getIntenundleExtraBla();
		// battleController = new BattleController(enemyUnimon, battleUnimon,
		// unimonList);
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
				// int battleUnimonsListIndex = onClickListenerReadIndexBla();
				// battleController.changeCurrentUnimon(battleUnimonsListIndex);
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

		// Abfrage ob ein Healpot im Inventar verfŸgbar ist
		if (playerListener.onHealPotAvailable()) {
			healpotButton.setClickable(true);
		} else {
			healpotButton.setClickable(false);
		}

		// Abfrage ob ein Uniball im Inventar verfŸgbar ist
		if (playerListener.onUniBallAvailable()) {
			uniballButton.setClickable(true);
		} else {
			uniballButton.setClickable(false);
		}

		healpotButtonClicked();
		uniballButtonClicked();
	}

	// Wird aufgerufen, nachdem eine Aktion durchgefŸhrt wurde, mit einem Delay
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

	// Uniball Count wird heruntergezŠhlt & im BattleController wird das
	// gefangene Unimon zur ownUnimonList hinzugefŸgt (wenn es gefangen wurde)
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

	// HealpotCount wird im BattleController Ÿber Playerlistener den
	// heruntergeztŠhlt
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
