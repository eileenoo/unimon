package de.ur.unimon.battle;

import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.mapoverview.MapActivity;
import de.ur.unimon.player.Player;
import de.ur.unimon.player.PlayerController;
import de.ur.unimon.trainer.Trainer;
import de.ur.unimon.trainer.TrainerList;
import de.ur.unimon.trainer.TrainerListController;
import de.ur.unimon.unimons.Spell;
import de.ur.unimon.unimons.Unimon;
import de.ur.wildlings.WildlingCreator;
import de.ur.unimon.unimons.UnimonList;


public class BattleActivity extends Activity implements
		AllOptionsBattleFragment.OnOptionsSelectorListener,
		AttackBattleFragment.OnSpellSelectedListener,
		ChangeUnimonBattleFragment.OnUnimonChangedListener,
		ChooseItemFragment.OnChooseItemListener,
		ChooseUnimonForHealpotBattleFragment.OnGetBattleUnimonsListListener,
		EnemyFightFragment.OnGetEnemyInformationListener {

	private ImageView enemyUnimonImage, ownUnimonImage;

	private TextView enemyUnimonName, ownUnimonName, enemyUnimonLevel,
			ownUnimonLevel, enemyUnimonHealth, ownUnimonHealth;
	private TextView damageToEnemyUnimonDealt, damageToOwnUnimonDealt,
			showHealTextView;

	private ProgressBar enemyUnimonHealthbar;
	private ProgressBar ownUnimonHealthbar;

	private Intent map;

	private Unimon battleUnimon;
	private Unimon enemyUnimon;
	private Unimon[] currentBattleUnimonList;
	private String[] battleUnimonListStringArray;
	private Trainer trainer;
	private TrainerListController trainerListController;
	private Player player;
	private PlayerController playerController;
	private WildlingCreator wildlingCreator;
	private BattleController battleController;
	private final Handler handler = new Handler();

	private Toast toast;

	private boolean playerStatus;
	private boolean gameWon;

	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	private AllOptionsBattleFragment allOptionsFragment;
	private EnemyFightFragment enemyFragment;

	private int XP, money;

	final Animation toEnemyDamageTextIn = new AlphaAnimation(0.0f, 1.0f);
	final Animation toOwnDamageTextIn = new AlphaAnimation(0.0f, 1.0f);
	final Animation toEnemyDamageTextOut = new AlphaAnimation(1.0f, 0.0f);
	final Animation toOwnDamageTextOut = new AlphaAnimation(1.0f, 0.0f);
	final Animation showHealOut = new AlphaAnimation(1.0f, 0.0f);
	final Animation showHealIn = new AlphaAnimation(0.0f, 1.0f);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battle_activity);
		initInstances();
		initBattleController();
		initUI();
		createFirstFragment();
		setUpAnimation();
	}
	
	@Override
	public void onBackPressed() {
	    //Hardware Zurückbutton disabled
	}

	private void setUpAnimation() {
		toEnemyDamageTextIn.setDuration(1000);
		toOwnDamageTextIn.setDuration(1000);
		toEnemyDamageTextOut.setDuration(1000);
		toEnemyDamageTextOut.setFillAfter(true);
		toOwnDamageTextOut.setDuration(1000);
		toOwnDamageTextOut.setFillAfter(true);
		showHealIn.setDuration(1000);
		showHealOut.setDuration(1000);
		showHealOut.setFillAfter(true);

		toEnemyDamageTextIn.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				damageToEnemyUnimonDealt.setText("-"
						+ battleController.getToEnemyUnimonDamageDealt());
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				damageToEnemyUnimonDealt.startAnimation(toEnemyDamageTextOut);
			}
		});

		toOwnDamageTextIn.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				damageToOwnUnimonDealt.setText("-"
						+ battleController.getToOwnUnimonDamageDealt());
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				damageToOwnUnimonDealt.startAnimation(toOwnDamageTextOut);
			}
		});

		showHealIn.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				showHealTextView.setText("+" + battleUnimon.getHealpotAmount());
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				showHealTextView.startAnimation(showHealOut);
			}
		});
	}

	private void showDamageDealtToOwnUnimon() {
		damageToOwnUnimonDealt.startAnimation(toOwnDamageTextIn);
	}

	private void showDamageDealtToEnemyUnimon() {
		damageToEnemyUnimonDealt.startAnimation(toEnemyDamageTextIn);
	}

	private void showHeal() {
		showHealTextView.startAnimation(showHealIn);
	}

	private void initUI() {
		showHealTextView = (TextView) findViewById(R.id.heal_text);
		damageToEnemyUnimonDealt = (TextView) findViewById(R.id.to_enemy_damage_dealt);
		damageToOwnUnimonDealt = (TextView) findViewById(R.id.to_own_damage_dealt);

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
		
		enemyUnimonImage.setImageResource(enemyUnimon.getImage());
		ownUnimonImage.setImageResource(battleUnimon.getImage());
		enemyUnimonName.setText(enemyUnimon.getName());
		ownUnimonName.setText(battleUnimon.getName());
		enemyUnimonLevel.setText("Level: " + enemyUnimon.getLevel());
		ownUnimonLevel.setText("Level: " + battleUnimon.getLevel());
		enemyUnimonHealth.setText(enemyUnimon.getHealth() + "/"
				+ enemyUnimon.getMaxHealth());
		ownUnimonHealth.setText(battleUnimon.getHealth() + "/"
				+ battleUnimon.getMaxHealth());

		enemyUnimonHealthbar.setMax(enemyUnimon.getMaxHealth());
		ownUnimonHealthbar.setMax(battleUnimon.getMaxHealth());
		enemyUnimonHealthbar.setProgress(enemyUnimon.getHealth());
		ownUnimonHealthbar.setProgress(battleUnimon.getHealth());
		getProgressDrawable(enemyUnimonHealthbar, enemyUnimon);
		getProgressDrawable(ownUnimonHealthbar, battleUnimon);

	}

	private void getProgressDrawable(ProgressBar healthbar, Unimon unimon) {
		double healthPercentage = ((double) unimon.getHealth())
				/ unimon.getMaxHealth();
		if (healthPercentage >= 0.75) {
			healthbar.setProgressDrawable(getResources().getDrawable(
					R.drawable.green_progress));
		} else if (healthPercentage <= 0.25) {
			healthbar.setProgressDrawable(getResources().getDrawable(
					R.drawable.red_progress));
			;
		} else
			healthbar.setProgressDrawable(getResources().getDrawable(
					R.drawable.orange_progress));
	}

	private void updateUIForChangedUnimon() {
		ownUnimonImage.setImageResource(battleUnimon.getImage());
		ownUnimonName.setText(battleUnimon.getName());
		ownUnimonLevel.setText("Level: " + battleUnimon.getLevel());
		ownUnimonHealth.setText(battleUnimon.getHealth() + "/"
				+ battleUnimon.getMaxHealth());
		ownUnimonHealthbar.setMax(battleUnimon.getMaxHealth());
		ownUnimonHealthbar.setProgress(battleUnimon.getHealth());
		getProgressDrawable(ownUnimonHealthbar, battleUnimon);
	}

	private void updateEnemeyHealthUI() {
		enemyUnimonHealth.setText(enemyUnimon.getHealth() + "/"
				+ enemyUnimon.getMaxHealth());
		enemyUnimonHealthbar.setProgress(enemyUnimon.getHealth());
		getProgressDrawable(enemyUnimonHealthbar, enemyUnimon);
	}

	private void updateOwnHealthUI() {
		ownUnimonHealth.setText(battleUnimon.getHealth() + "/"
				+ battleUnimon.getMaxHealth());
		ownUnimonHealthbar.setProgress(battleUnimon.getHealth());
		getProgressDrawable(ownUnimonHealthbar, battleUnimon);
	}

	private void initInstances() {
		map = new Intent(BattleActivity.this, MapActivity.class);
		playerStatus = true;
		player = playerController.getInstance();
		wildlingCreator = new WildlingCreator();
		ArrayList<Trainer> trainerList = trainerListController.getInstance().getTrainerList();

		int trainerID = getIntent().getExtras().getInt("trainerID");
		if (trainerID == 99) {
			wildlingCreator.createWildUnimon();
			trainer = wildlingCreator.getTrainer();
		}
		else { trainer = trainerList.get(trainerID); }
		enemyUnimon = trainer.getUnimon();		
	}

	private void initBattleController() {
		battleUnimonListStringArray = getIntent().getStringArrayExtra(
				"chosenUnimonStringArray");

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
		allOptionsFragment = new AllOptionsBattleFragment();

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
		enemyFragment = new EnemyFightFragment();

		if (playerStatus) {
			fragmentTransaction = getFragmentManager().beginTransaction();
			fragmentTransaction.replace(R.id.battle_activity_layout,
					allOptionsFragment);
			fragmentManager.popBackStack();
			fragmentTransaction.commit();

		} else {
			fragmentTransaction = getFragmentManager().beginTransaction();

			fragmentTransaction.replace(R.id.battle_activity_layout,
					enemyFragment);
			fragmentManager.popBackStack();
			fragmentTransaction.commit();

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
		showDamageDealtToOwnUnimon();
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
		XP = trainer.getExpValue();
		money = trainer.getMoneyValue();
		if (gameWon){
			player.addMoney(money);
			switch (battleController.getXpSplit()) {
			case 1:
				currentBattleUnimonList[0].addXp(XP);
				break;
			case 2:
				currentBattleUnimonList[0].addXp(XP / 2);
				if (battleController.isSecondUnimonUsed()) {
					currentBattleUnimonList[1].addXp((int) (Math.round(XP / 2d)));
				} else
					currentBattleUnimonList[2].addXp((int) (Math.round(XP / 2d)));
				break;
			case 3:
				currentBattleUnimonList[0].addXp((int) (Math.round(XP / 3d)));
				currentBattleUnimonList[1].addXp((int) (Math.round(XP / 3d)));
				currentBattleUnimonList[2].addXp((int) (Math.round(XP / 3d)));
				break;
			}
		}
		
		Intent toBattleEndActivity = new Intent(BattleActivity.this,
				BattleEndActivity.class);
		
		toBattleEndActivity.putExtra("XP", XP);
		toBattleEndActivity.putExtra("Money", money);
		toBattleEndActivity.putExtra("IsGameWon", gameWon);
		startActivity(toBattleEndActivity);
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
		showDamageDealtToEnemyUnimon();
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
		unimon.useHealpot();
		if (unimon == battleUnimon) {
			showHeal();
		}
		updateOwnHealthUI();
		showToast(R.string.battle_healpot_used);
		playerStatus = false;
		checkStatus();
	}

	@Override
	public String onGetEnemyName() {
		return enemyUnimon.getName();
	}
}
