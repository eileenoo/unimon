package de.ur.unimon.battle;

import java.util.ArrayList;

import de.ur.mi.android.excercises.starter.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import de.ur.unimon.mapoverview.MapActivity;
import de.ur.unimon.startgame_logic.Player;
import de.ur.unimon.unimons.Spell;
import de.ur.unimon.unimons.Unimon;
import de.ur.unimon.unimons.UnimonList;

public class BattleActivity extends Activity implements
		AllOptionsBattleFragment.OnOptionsSelectorListener,
		AttackBattleFragment.OnSpellSelectedListener,
		ChangeUnimonBattleFragment.OnUnimonChangedListener {

	private Button changeUnimonButton, attackButton, useItemButton,
			escapeButton, healpotButton, uniballButton;
	private Intent map;

	private Unimon battleUnimon;
	private Unimon enemyUnimon;
	private Unimon[] currentBattleUnimonList;
	private String[] battleUnimonListStringArray;
	private Player player;
	private BattleController battleController;

	private Toast toast;

	private boolean playerStatus;
	private boolean gameWon;

	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battle_activity);
		initInstances();
		// initButtons();
		initBattleController();
		// initClickListeners();
		createFirstFragment();
	}

	private void initInstances() {
		map = new Intent(BattleActivity.this, MapActivity.class);
		gameWon = false;
		playerStatus = true;
		player = de.ur.unimon.appstart.StartScreenActivity.player;
	}

	private void initBattleController() {
		battleUnimonListStringArray = getIntent().getStringArrayExtra(
				"chosenUnimonStringArray");
		// EnemyUnimon wird spŠter auch Ÿber den Intent aus ChooseBattleUnimon
		// geholt, hier: Ersatzcode

		UnimonList listAllUnimons = new UnimonList();
		enemyUnimon = listAllUnimons.getUnimonList().get(0);

		String battleUnimonName = battleUnimonListStringArray[0];
		String secondBattleUnimonName = battleUnimonListStringArray[1];
		String thirdBattleUnimonName = battleUnimonListStringArray[2];

		battleUnimon = player.getUnimonByName(battleUnimonName);

		currentBattleUnimonList = new Unimon[2];
		currentBattleUnimonList[0] = player
				.getUnimonByName(secondBattleUnimonName);
		currentBattleUnimonList[1] = player
				.getUnimonByName(thirdBattleUnimonName);
		battleController = new BattleController(enemyUnimon, battleUnimon,
				currentBattleUnimonList);
	}

	private void createFirstFragment() {
		AllOptionsBattleFragment allOptionsFragment = new AllOptionsBattleFragment();

		Bundle extra = new Bundle();
		extra.putBoolean("CurrentUnimonListContent",
				hasCurrentBattleUnimonsListUnimons());
		allOptionsFragment.setArguments(extra);

		fragmentManager = getFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.battle_activity_layout,
				allOptionsFragment, "AllOptionsFragment");
		fragmentTransaction.commit();
	}

	private boolean hasCurrentBattleUnimonsListUnimons() {
		if (currentBattleUnimonList[0] == null) {
			return false;
		} else {
			return true;
		}
	}

	// private void initButtons() {
	// attackButton = (Button) findViewById(R.id.battle_attack_button);
	// useItemButton = (Button) findViewById(R.id.battle_use_item_button);
	// escapeButton = (Button) findViewById(R.id.battle_escape_button);
	// changeUnimonButton = (Button)
	// findViewById(R.id.battle_change_unimon_button);
	// }

	// private void initClickListeners() {
	// attackButtonClicked();
	// itemButtonClicked();
	// escapeButtonClicked();
	// changeUnimonButtonClicked();
	// }

	// private void escapeButtonClicked() {
	// escapeButton.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// if (battleController.ableToEscape()) {
	// showToast(R.string.escape_toast_text);
	// startActivity(map);
	// } else {
	// showToast(R.string.escape_not_successfull_toast_text);
	// }
	// playerStatus = false;
	// }
	// });
	// checkStatus();
	// }

	// private void changeUnimonButtonClicked() {
	//
	// if (currentBattleUnimonList[0] == null) {
	// changeUnimonButton.setClickable(false);
	// } else {
	// changeUnimonButton.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	//
	// unimonTwoButton = (Button) findViewById(R.id.battle_unimon_two_button);
	// unimonThreeButton = (Button)
	// findViewById(R.id.battle_unimon_three_button);
	//
	// String unimonTwoButtonText = currentBattleUnimonList[0]
	// .getName();
	// String unimonThreeButtonText = currentBattleUnimonList[1]
	// .getName();
	//
	// unimonTwoButton.setText(unimonTwoButtonText);
	// int unimonTwoIndex = 0;
	// clickToChangeUnimon(unimonTwoButton, unimonTwoIndex);
	//
	// if (currentBattleUnimonList[1] != null) {
	// unimonThreeButton.setText(unimonThreeButtonText);
	// int unimonThreeIndex = 1;
	// clickToChangeUnimon(unimonThreeButton, unimonThreeIndex);
	// } else {
	// unimonThreeButton.setVisibility(View.GONE);
	// }
	// }
	// });
	// }
	// }

	// private void attackButtonClicked() {
	// attackButton.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// showSpellList();
	// }
	// });
	//
	// }

	private void itemButtonClicked() {
		healpotButton = (Button) findViewById(R.id.battle_healpot_button);
		uniballButton = (Button) findViewById(R.id.battle_uniball_button);

		// Sollen wir zu den Items in Klammer noch die aktuelle Anzahl
		// dazuschreiben?

		useItemButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Abfrage ob ein Healpot im Inventar verfŸgbar ist
				if (player.getInventory().healpotAvailable()) {
					healpotButton.setClickable(true);
				} else {
					healpotButton.setClickable(false);
				}

				// Abfrage ob ein Uniball im Inventar verfŸgbar ist
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

	// Uniball Count wird heruntergezŠhlt & im BattleController wird das
	// gefangene Unimon zur ownUnimonList hinzugefŸgt (wenn es gefangen wurde)
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

	// HealpotCount wird im BattleController Ÿber Playerlistener den
	// heruntergeztŠhlt
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

	// Wird aufgerufen, nachdem eine Aktion durchgefŸhrt wurde
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

	// _____________________ONOPTIONSSELECTORLISTENER_METHODEN__________________


	@Override
	public void onEscapeSuccessfull() {		
		showToast(R.string.escape_toast_text);
		fragmentManager.popBackStack();
		startActivity(map);
	}
	
	@Override
	public void onEscapeFailed() {
		showToast(R.string.escape_not_successfull_toast_text);
		 playerStatus = false;
		// checkStatus();
	}

	@Override
	public boolean onIsEscapeAvailable() {
		boolean isEscapeAvailable = battleController.ableToEscape();
		return isEscapeAvailable;
	}
	

	// In showSpellList muss noch ein neues Fragment erzeugt werden
	// @Override
	// public void onAttackButtonClicked() {
	// AttackBattleFragment attackFragment = new AttackBattleFragment();
	//
	// ArrayList<Spell> ownedSpells = battleUnimon.getOwnedSpells();
	// // Dem AttackFragment muss die SpellList Ÿbergeben werden
	// // Bundle extra = new Bundle();
	// // extra.putSparseParcelableArray(key, value);
	// // extra.putArrayList("CurrentUnimonListContent",
	// // hasCurrentBattleUnimonsListUnimons());
	// // attackFragment.setArguments(extra);
	//
	// fragmentTransaction.add(R.id.battle_activity_layout, attackFragment,
	// "AttackBattleFragment");
	// fragmentTransaction.commit();
	// }

	// _____OnSpellSelectedLister des AttackBattleFragments_____
	@Override
	public void onSpellSelected(Spell chosenSpell) {
		Spell currentSpell = chosenSpell;
		enemyUnimon = battleController.ownUnimonAttack(currentSpell);
		if (enemyUnimon.getHealth() >= 0) {
			gameWon = true;
			fightEnd();
		} else {
			playerStatus = false;
//			checkStatus();
		}

	}

	@Override
	public ArrayList<Spell> getSpellList() {
		if (battleUnimon.getPossibleSpells().isEmpty()) {
			Log.d("isEmpty:", "true");
		}
		// for (int i = 0; i < battleUnimon.getPossibleSpells().size(); i++) {
		// Log.d("Spell" + i + " :",
		// battleUnimon.getPossibleSpells().get(i).toString());
		// }

		// ArrayList<Spell> ownedSpells = battleUnimon.getPossibleSpells();
		// return ownedSpells;
		return null;
	}

	
	@Override
	public void onUnimonChanged(Unimon chosenUnimon, int unimonNameIndex) {
		battleController.changeCurrentUnimon(chosenUnimon);
		currentBattleUnimonList[unimonNameIndex] = battleUnimon;
		battleUnimon = chosenUnimon;
		playerStatus = false;
//		checkStatus();
		Log.d("ChosenUnimon", "fu");
	}

	@Override
	public Unimon[] onGetBattleUnimonListArray() {
		return currentBattleUnimonList;
	}



}
