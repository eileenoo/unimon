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
import de.ur.unimon.startgame_logic.Player;
import de.ur.unimon.startgame_logic.PlayerListener;
import de.ur.unimon.unimons.Spell;
import de.ur.unimon.unimons.Unimon;
import de.ur.unimon.unimons.UnimonList;

public class BattleActivity extends Activity {

	private Button changeUnimonButton, attackButton, useItemButton,
			escapeButton, healpotButton, uniballButton, unimonTwoButton,
			unimonThreeButton;
	private Intent map;

	private Unimon battleUnimon;
	private Unimon enemyUnimon;
	private Unimon[] currentBattleUnimonList;
	private Player player;
	private BattleController battleController;

	private Toast toast;

	private boolean playerStatus;
	private boolean gameWon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battle_activity);
		initInstances();
		initButtons();
		initBattleController();
		initClickListeners();
	}

	private void initInstances() {
		map = new Intent(BattleActivity.this, MapActivity.class);
		gameWon = false;
		playerStatus = true;
		player = de.ur.unimon.appstart.StartScreenActivity.player;
	}

	private void initButtons() {
		attackButton = (Button) findViewById(R.id.battle_attack_button);
		useItemButton = (Button) findViewById(R.id.battle_use_item_button);
		escapeButton = (Button) findViewById(R.id.battle_escape_button);
		changeUnimonButton = (Button) findViewById(R.id.battle_change_unimon_button);
	}

	private void initBattleController() {
		Bundle extra = getIntent().getExtras();
		Unimon[] battleUnimonList = (Unimon[]) extra.get("chosenUnimons");

		// EnemyUnimon wird später auch über den Intent aus ChooseBattleUnimon
		// geholt, hier: Ersatzcode

		UnimonList listAllUnimons = new UnimonList();
		enemyUnimon = listAllUnimons.getUnimonList().get(0);

		battleUnimon = battleUnimonList[0];
		currentBattleUnimonList = new Unimon[2];
		currentBattleUnimonList[0] = battleUnimonList[1];
		currentBattleUnimonList[1] = battleUnimonList[2];
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
				if (battleController.ableToEscape()) {
					showToast(R.string.escape_toast_text);
					startActivity(map);
				} else {
					showToast(R.string.escape_not_successfull_toast_text);
				}
				playerStatus = false;
			}
		});
		checkStatus();
	}

	private void changeUnimonButtonClicked() {

		changeUnimonButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				unimonTwoButton = (Button) findViewById(R.id.battle_unimon_two_button);
				unimonThreeButton = (Button) findViewById(R.id.battle_unimon_three_button);

				String unimonTwoButtonText = currentBattleUnimonList[0]
						.getName();
				String unimonThreeButtonText = currentBattleUnimonList[1]
						.getName();

				// Wenn das Array currentBattleUnimonList (der Länge 2) an
				// Stelle [0] leer ist, dann sind beide buttons der zu
				// wechselnden Buttons nicht klickbar & ""
				// Wenn Stelle [0] nicht leer ist wird noch gecheckt ob es an
				// Stelle [1] leer ist
				if (currentBattleUnimonList[0] != null) {
					unimonTwoButton.setText(unimonTwoButtonText);
					int unimonTwoIndex = 0;
					clickToChangeUnimon(unimonTwoButton, unimonTwoIndex);

					if (currentBattleUnimonList[1] != null) {
						unimonThreeButton.setText(unimonThreeButtonText);
						int unimonThreeIndex = 1;
						clickToChangeUnimon(unimonThreeButton, unimonThreeIndex);
					} else {
						unimonThreeButton.setText("");
						unimonThreeButton.setClickable(false);
					}
				} else {
					unimonTwoButton.setText("");
					unimonTwoButton.setClickable(false);
					unimonThreeButton.setText("");
					unimonThreeButton.setClickable(false);
				}

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
				playerStatus = false;
			}
		});
		checkStatus();
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
		// enemyUnimon = battleController.ownUnimonAttack(currentSpell);
		if (enemyUnimon.getHealth() >= 0) {
			gameWon = true;
			fightEnd();
		} else {
			playerStatus = false;
			checkStatus();
		}
	}

	private void itemButtonClicked() {
		healpotButton = (Button) findViewById(R.id.battle_healpot_button);
		uniballButton = (Button) findViewById(R.id.battle_uniball_button);

		useItemButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Abfrage ob ein Healpot im Inventar verfügbar ist
				if (player.getInventory().healpotAvailable()) {
					healpotButton.setClickable(true);
				} else {
					healpotButton.setClickable(false);
				}

				// Abfrage ob ein Uniball im Inventar verfügbar ist
				if (player.getInventory().uniballAvailable()) {
					uniballButton.setClickable(true);
				} else {
					uniballButton.setClickable(false);
				}

				healpotButtonClicked();
				uniballButtonClicked();
			}
		});

	}

	// Wird aufgerufen, nachdem eine Aktion durchgeführt wurde
	private void enemyFight() {
		battleUnimon = battleController.enemyUnimonAttack();
		if (battleUnimon.getHealth() >= 0) {
			gameWon = false;
			fightEnd();
		}
	}

	private void fightEnd() {
		if (gameWon) {
			showToast(R.string.battle_won);
		} else {
			showToast(R.string.battle_lost);
		}
		startActivity(map);
	}

	// Uniball Count wird heruntergezählt & im BattleController wird das
	// gefangene Unimon zur ownUnimonList hinzugefügt (wenn es gefangen wurde)
	private void uniballButtonClicked() {
		uniballButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				player.getInventory().decreaseUniball();
				if (battleController.ableToCatchUnimon()) {
					battleController.unimonCatchSuccess();
					showToast(R.string.catch_unimon_success_text);
					startActivity(map);
				} else {
					showToast(R.string.catch_unimon_fail_text);
				}
				playerStatus = false;
			}
		});
		checkStatus();
	}

	// HealpotCount wird im BattleController über Playerlistener den
	// heruntergeztählt
	private void healpotButtonClicked() {
		healpotButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				battleController.useHealpot();
				playerStatus = false;
			}
		});
		checkStatus();
	}

	private void showToast(int toastText) {
		int duration = Toast.LENGTH_SHORT;
		toast = Toast.makeText(this, toastText, duration);
		toast.show();
	}

	// Es wird geschaut wer an der Reihe ist -Spieler oder GegnerTrainer
	// Wenn der Spieler nicht mehr an der Reihe ist (weil er schon einen Zug
	// gemacht hat), werden die Buttons disabled & der Gegner greift an
	private void checkStatus() {
		if (playerStatus) {
			escapeButton.setClickable(true);
			changeUnimonButton.setClickable(true);
			attackButton.setClickable(true);
			useItemButton.setClickable(true);
		} else {
			escapeButton.setClickable(false);
			changeUnimonButton.setClickable(false);
			attackButton.setClickable(false);
			useItemButton.setClickable(false);
			enemyFight();
		}
	}

}
