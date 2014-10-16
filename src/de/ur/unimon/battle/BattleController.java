package de.ur.unimon.battle;

import java.util.Random;
import de.ur.unimon.player.Player;
import de.ur.unimon.player.PlayerController;
import de.ur.unimon.unimons.Spell;
import de.ur.unimon.unimons.Unimon;

public class BattleController {

	private Unimon battleUnimon;
	private Unimon enemyUnimon;
	private Player player;
	private PlayerController playerController;
	private Random randomGenerator;
	private boolean isSecondUnimonUsed = false;
	private boolean isThirdUnimonUsed = false;
	private int toOwnUnimonDamageDealt, toEnemyDamageDealt;

	public BattleController(Unimon enemyUnimon, Unimon battleUnimon) {
		initRandomGenerator();
		initPlayer();
		initEnemyUnimon(enemyUnimon);
		initBattleUnimon(battleUnimon);
	}

	private void initRandomGenerator() {
		randomGenerator = new Random();
	}

	private void initPlayer() {
		player = playerController.getInstance();
	}

	private void initEnemyUnimon(Unimon enemyUnimon) {
		this.enemyUnimon = enemyUnimon;
	}

	private void initBattleUnimon(Unimon battleUnimon) {
		this.battleUnimon = battleUnimon;
	}

	public boolean ableToEscape() {
		boolean escape = false;
		int difference = enemyUnimon.getLevel() - battleUnimon.getLevel();

		if (difference <= -2) {
			escape = true;
		} else if (difference >= 2) {
			escape = false;
		} else if (difference == 1 || difference == -1 || difference == 0) {
			escape = randomGenerator.nextBoolean();
		}
		return escape;
	}

	public boolean ableToCatchUnimon() {
		if (enemyUnimon.ownedByTrainer()) {
			return false;
		} else if (enemyUnimon.getHealth() < enemyUnimon.getMaxHealth() * 0.3) {
			return true;
		} else if (enemyUnimon.getHealth() > enemyUnimon.getMaxHealth() * 0.7) {
			return false;
		}
		return randomGenerator.nextBoolean();
	}

	public void unimonCatchSuccess() {
		player.ownUnimonList.add(enemyUnimon);
	}

	public int getXpSplit() {
		if (isSecondUnimonUsed == true && isThirdUnimonUsed == true) {
			return 3;
		} else if (isSecondUnimonUsed == false && isThirdUnimonUsed == false) {
			return 1;
		} else
			return 2;
	}

	public boolean isSecondUnimonUsed() {
		return isSecondUnimonUsed;
	}

	public boolean isThirdUnimonUsed() {
		return isThirdUnimonUsed;
	}

	public void changeCurrentUnimon(Unimon chosenUnimon, int index) {
		if (index == 1) {
			isSecondUnimonUsed = true;
		} else if (index == 2) {
			isThirdUnimonUsed = true;
		}
		battleUnimon = chosenUnimon;
	}

	public Unimon ownUnimonAttack(Spell spell) {
		toEnemyDamageDealt = spell.getDamage();
		enemyUnimon.loseHealth(toEnemyDamageDealt);
		return enemyUnimon;
	}

	public int getLostHealthOfEnemyUnimon(Spell spell) {
		return spell.getDamage();
	}

	public Unimon enemyUnimonAttack() {
		int spellSize = enemyUnimon.ownedSpells.size();
		toOwnUnimonDamageDealt = enemyUnimon.ownedSpells.get(
				randomGenerator.nextInt(spellSize)).getDamage();
		battleUnimon.loseHealth(toOwnUnimonDamageDealt);
		return battleUnimon;
	}

	public int getToEnemyUnimonDamageDealt() {
		return toEnemyDamageDealt;
	}

	public int getToOwnUnimonDamageDealt() {
		return toOwnUnimonDamageDealt;
	}
}
