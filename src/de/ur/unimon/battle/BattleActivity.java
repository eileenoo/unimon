package de.ur.unimon.battle;

import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.mapoverview.MapActivity;
import de.ur.unimon.startgame_logic.Player;
import de.ur.unimon.startgame_logic.PlayerController;
import de.ur.unimon.unimons.Spell;
import de.ur.unimon.unimons.Unimon;
import de.ur.unimon.unimons.UnimonList;

public class BattleActivity extends Activity implements
		AllOptionsBattleFragment.OnOptionsSelectorListener,
		AttackBattleFragment.OnSpellSelectedListener,
		ChangeUnimonBattleFragment.OnUnimonChangedListener,
		ChooseItemFragment.OnChooseItemListener,
		ChooseUnimonForHealpotBattleFragment.OnGetBattleUnimonsListListener {

	private Intent map;

	private Unimon battleUnimon;
	private Unimon enemyUnimon;
	private Unimon[] currentBattleUnimonList;
	private String[] battleUnimonListStringArray;
	private Player player;
	private PlayerController playerController;
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
		initBattleController();
		createFirstFragment();
	}

	private void initInstances() {
		map = new Intent(BattleActivity.this, MapActivity.class);
		gameWon = false;
		playerStatus = true;
		player = playerController.getInstance();
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

		currentBattleUnimonList = new Unimon[3];
		currentBattleUnimonList[0] = player.getUnimonByName(battleUnimonName);
		currentBattleUnimonList[1] = player
				.getUnimonByName(secondBattleUnimonName);
		currentBattleUnimonList[2] = player
				.getUnimonByName(thirdBattleUnimonName);

		battleUnimon = currentBattleUnimonList[0];
		battleController = new BattleController(enemyUnimon, battleUnimon);
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
		if (currentBattleUnimonList[1] == null) {
			return false;
		} else {
			return true;
		}
	}

	private void showToast(int toastText) {
		int duration = Toast.LENGTH_SHORT;
		toast = Toast.makeText(this, toastText, duration);
		toast.show();
	}

	private void checkStatus() {
		if (playerStatus) {
			// Fragment wieder klickable machen
		} else {
			// Fragment inklickbar machen
			enemyFight();
		}
	}

	// Is called, after each move (attack, escape, changeUnimon, useItem)
	private void enemyFight() {
		battleUnimon = battleController.enemyUnimonAttack();
		if (battleUnimon.getHealth() >= 0) {
			gameWon = false;
			fightEnd();
		} else {
			playerStatus = true;
			// checkStatus();
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

	// _____________________LISTENERS__________________

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

	@Override
	public void onSpellSelected(Spell chosenSpell) {
		enemyUnimon = battleController.ownUnimonAttack(chosenSpell);
		if (enemyUnimon.getHealth() >= 0) {
			gameWon = true;
			fightEnd();
		} else {
			showToast(R.string.battle_enemyunimon_lost_health_text
					+ battleController.getLostHealthOfEnemyUnimon(chosenSpell));
			playerStatus = false;
			// checkStatus();
		}
	}

	@Override
	public ArrayList<Spell> getSpellList() {
		ArrayList<Spell> spellList = battleUnimon.getOwnedSpells();
		return spellList;
	}

	@Override
	public void onUnimonChanged(Unimon chosenUnimon, int unimonNameIndex) {
		battleController.changeCurrentUnimon(chosenUnimon);
		currentBattleUnimonList[0] = chosenUnimon;
		currentBattleUnimonList[unimonNameIndex] = battleUnimon;
		battleUnimon = chosenUnimon;
		playerStatus = false;
		// checkStatus();
	}

	@Override
	public Unimon[] onGetBattleUnimonListArray() {
		return currentBattleUnimonList;
	}

	@Override
	public boolean onHealpotAvailable() {
		if (player.getInventory().getHealpotCount() == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean onUniballAvailable() {
		if (player.getInventory().getUniballCount() == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void onUniballButtonClicked() {
		player.getInventory().decreaseUniball();
		if (battleController.ableToCatchUnimon()) {
			battleController.unimonCatchSuccess();
			showToast(R.string.catch_unimon_success_text);
			startActivity(map);
		} else {
			showToast(R.string.catch_unimon_fail_text);
		}
		playerStatus = false;
		// checkStatus();

	}

	@Override
	public Unimon[] onGetBattleUnimonsList() {
		return currentBattleUnimonList;
	}

	@Override
	public void onHealpotUsedOnUnimon(Unimon unimon) {
		player.getInventory().decreaseHealpots();
		unimon.addHealth(50);
		showToast(R.string.battle_healpot_used);
		playerStatus = false;
		// checkStatus();
	}
}
