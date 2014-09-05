package de.ur.unimon.battle;

import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.mapoverview.MapActivity;
import de.ur.unimon.startgame_logic.Player;
import de.ur.unimon.startgame_logic.PlayerController;
import de.ur.unimon.unimons.Spell;
import de.ur.unimon.unimons.Unimon;

public class BattleActivity extends Activity implements
		AllOptionsBattleFragment.OnOptionsSelectorListener,
		AttackBattleFragment.OnSpellSelectedListener,
		ChangeUnimonBattleFragment.OnUnimonChangedListener,
		ChooseItemFragment.OnChooseItemListener,
		ChooseUnimonForHealpotBattleFragment.OnGetBattleUnimonsListListener {
	
	private ImageView enemyUnimonImage, ownUnimonImage;
	private TextView enemyUnimonName, ownUnimonName, enemyUnimonLevel, ownUnimonLevel, enemyUnimonHealth, ownUnimonHealth;
	private ProgressBar enemyUnimonHealthbar;
	private ProgressBar ownUnimonHealthbar;

	private Intent map;

	private Unimon battleUnimon;
	private Unimon enemyUnimon;
	private Unimon[] currentBattleUnimonList;
	private String[] battleUnimonListStringArray;
	private Trainer trainer;
	private Player player;
	private PlayerController playerController;
	private BattleController battleController;
	private final Handler handler = new Handler();

	private Toast toast;

	private boolean playerStatus;
	private boolean gameWon;

	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	
	private int XP, money;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battle_activity);
		initInstances();
		initBattleController();
		initUI();
		createFirstFragment();
	}
	
	

	private void initUI() {
		enemyUnimonImage = (ImageView) findViewById(R.id.enemy_unimon_image);
		ownUnimonImage = (ImageView) findViewById(R.id.own_unimon_image);
		enemyUnimonName = (TextView) findViewById(R.id.enemy_unimon_name);
		ownUnimonName = (TextView) findViewById(R.id.own_unimon_name);
		enemyUnimonLevel = (TextView) findViewById(R.id.enemy_unimon_level);
		ownUnimonLevel = (TextView) findViewById(R.id.own_unimon_level);
		enemyUnimonHealth = (TextView) findViewById(R.id.enemy_unimon_health);
		ownUnimonHealth = (TextView) findViewById(R.id.own_unimon_health);
		enemyUnimonHealthbar = (ProgressBar) findViewById(R.id.enemy_healthBar);
		ownUnimonHealthbar = (ProgressBar) findViewById(R.id.own_healthBar);
		
		enemyUnimonName.setText(enemyUnimon.getName());
		ownUnimonName.setText(battleUnimon.getName());
		enemyUnimonLevel.setText("Level: "+enemyUnimon.getLevel());
		ownUnimonLevel.setText("Level: "+battleUnimon.getLevel());
		enemyUnimonHealth.setText(enemyUnimon.getHealth()+"/"+enemyUnimon.getMaxHealth());
		ownUnimonHealth.setText(battleUnimon.getHealth()+"/"+battleUnimon.getMaxHealth());
		
		enemyUnimonHealthbar.setMax(enemyUnimon.getMaxHealth());
		ownUnimonHealthbar.setMax(battleUnimon.getMaxHealth());
		enemyUnimonHealthbar.setProgress(enemyUnimon.getHealth());
		ownUnimonHealthbar.setProgress(battleUnimon.getHealth());
		getProgressDrawable(enemyUnimonHealthbar, enemyUnimon);
		getProgressDrawable(ownUnimonHealthbar, battleUnimon);		
		
//		Bilder müssen noch dynmaisch gesetzt werden, so dann irgendwie
//		enemyUnimonImage.setImageDrawable(enemyUnimon.getImage());
//		ownUnimonImage.setImageDrawable(battleUnimon.getImage());
		
	}
	
	private void getProgressDrawable(ProgressBar healthbar,
			Unimon unimon) {
		double healthPercentage =((double) unimon.getHealth()) / unimon.getMaxHealth();
		if (healthPercentage >= 0.75) {
			healthbar.setProgressDrawable(getResources().getDrawable(R.drawable.green_progress));
		} else if (healthPercentage <= 0.25){
			healthbar.setProgressDrawable(getResources().getDrawable(R.drawable.red_progress));;
		} else healthbar.setProgressDrawable(getResources().getDrawable(R.drawable.orange_progress));	
	}



	private void updateUIForChangedUnimon(){
		ownUnimonName.setText(battleUnimon.getName());
		ownUnimonLevel.setText("Level: "+battleUnimon.getLevel());
		ownUnimonHealth.setText(battleUnimon.getHealth()+"/"+battleUnimon.getMaxHealth());
		ownUnimonHealthbar.setMax(battleUnimon.getMaxHealth());
		ownUnimonHealthbar.setProgress(battleUnimon.getHealth());
		getProgressDrawable(ownUnimonHealthbar, battleUnimon);
	}
	
	private void updateEnemeyHealthUI() {
		enemyUnimonHealth.setText(enemyUnimon.getHealth()+"/"+enemyUnimon.getMaxHealth());
		enemyUnimonHealthbar.setProgress(enemyUnimon.getHealth());
		getProgressDrawable(enemyUnimonHealthbar, enemyUnimon);
	}
	
	private void updateOwnHealthUI() {
		ownUnimonHealth.setText(battleUnimon.getHealth()+"/"+battleUnimon.getMaxHealth());
		ownUnimonHealthbar.setProgress(battleUnimon.getHealth());
		getProgressDrawable(ownUnimonHealthbar, battleUnimon);
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

		ArrayList<Trainer> trainerList = new TrainerList().getTrainerList();
		trainer = trainerList.get(getIntent().getExtras().getInt("trainerID"));
		enemyUnimon = trainer.getUnimon();

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
			
			 handler.postDelayed(new Runnable() { 
				 @Override 
				 public void run() { 
					 enemyFight(); 
					 } 
				 }, 3000);	
		}
	}

	// Is called, after each move (attack, escape, changeUnimon, useItem)
	private void enemyFight() {
		battleUnimon = battleController.enemyUnimonAttack();
		updateOwnHealthUI();
		if (battleUnimon.getHealth() <= 0) {
			gameWon = false;
			fightEnd();
		} else {
			playerStatus = true;
			checkStatus();
		}
	}

	private void fightEnd() {
		if (gameWon) {
			XP = trainer.getExpValue();
			money = trainer.getMoneyValue();
			player.addMoney(money);
			switch (battleController.getXpSplit()){
			case 1: 
				currentBattleUnimonList[0].addXp(XP);
				break;
			case 2:
				currentBattleUnimonList[0].addXp(XP/2);
				if (battleController.isSecondUnimonUsed()){
					currentBattleUnimonList[1].addXp((int)(Math.round(XP/2d)));
				} else currentBattleUnimonList[2].addXp((int) (Math.round(XP/2d))); 
				break;
			case 3:
				currentBattleUnimonList[0].addXp((int) (Math.round(XP/3d)));
				currentBattleUnimonList[1].addXp((int) (Math.round(XP/3d)));
				currentBattleUnimonList[2].addXp((int) (Math.round(XP/3d)));
				break;
			}
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
		checkStatus();
	}

	@Override
	public boolean onIsEscapeAvailable() {
		boolean isEscapeAvailable = battleController.ableToEscape();
		return isEscapeAvailable;
	}

	@Override
	public void onSpellSelected(Spell chosenSpell) {
		enemyUnimon = battleController.ownUnimonAttack(chosenSpell);
		updateEnemeyHealthUI();
		if (enemyUnimon.getHealth() <= 0) {
			gameWon = true;
			fightEnd();
		} else {
			showToast(R.string.battle_enemyunimon_lost_health_text);
			playerStatus = false;
			checkStatus();
		}
	}


	@Override
	public ArrayList<Spell> getSpellList() {
		ArrayList<Spell> spellList = battleUnimon.getOwnedSpells();
		return spellList;
	}

	@Override
	public void onUnimonChanged(Unimon chosenUnimon, int unimonNameIndex) {
		battleController.changeCurrentUnimon(chosenUnimon, unimonNameIndex);
		currentBattleUnimonList[0] = chosenUnimon;
		currentBattleUnimonList[unimonNameIndex] = battleUnimon;
		battleUnimon = chosenUnimon;
		updateUIForChangedUnimon();
		playerStatus = false;
		checkStatus();
	}

	@Override
	public Unimon[] onGetBattleUnimonListArray() {
		return currentBattleUnimonList;
	}

	@Override
	public boolean onHealpotAvailable() {
		return player.getInventory().healpotAvailable();
	}

	@Override
	public boolean onUniballAvailable() {
		return player.getInventory().uniballAvailable();
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
		checkStatus();

	}

	@Override
	public Unimon[] onGetBattleUnimonsList() {
		return currentBattleUnimonList;
	}

	@Override
	public void onHealpotUsedOnUnimon(Unimon unimon) {
		player.getInventory().decreaseHealpots();
		unimon.addHealth(50);
		updateOwnHealthUI();
		showToast(R.string.battle_healpot_used);
		playerStatus = false;
		checkStatus();
	}
}
