package de.ur.unimon.battle;

import java.util.Random;

import de.ur.unimon.startgame_logic.Player;
import de.ur.unimon.startgame_logic.PlayerListener;
import de.ur.unimon.unimons.Spell;
import de.ur.unimon.unimons.Unimon;

public class BattleController {

	private Unimon[] battleUnimonsList;
	private Unimon battleUnimon;
	private Unimon enemyUnimon;
	private Player player;
	private Random randomGenerator;

	public BattleController(Unimon enemyUnimon, Unimon battleUnimon,
			Unimon[] unimonList) {
		initRandomGenerator();
		initPlayer();
		initEnemyUnimon(enemyUnimon);
		initBattleUnimon(battleUnimon);
		initBattleUnimonList(unimonList);
	}

	private void initRandomGenerator() {
		randomGenerator = new Random();
	}

	private void initPlayer() {
		player = de.ur.unimon.appstart.StartScreenActivity.player;
	}

	private void initEnemyUnimon(Unimon enemyUnimon) {
		this.enemyUnimon = enemyUnimon;
	}

	private void initBattleUnimon(Unimon battleUnimon) {
		this.battleUnimon = battleUnimon;
	}

	private void initBattleUnimonList(Unimon[] unimonList) {
		this.battleUnimonsList = unimonList;
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

	public void useHealpot() {
		battleUnimon.addHealth(50);
		player.getInventory().decreaseHealpots();
	}

	public void changeCurrentUnimon(Unimon chosenUnimon) {
		battleUnimon = chosenUnimon;
	}
	
	public Unimon ownUnimonAttack(Spell spell) {
		enemyUnimon.loseHealth(spell.getDamage());
		return enemyUnimon;
	}
	
	public Unimon enemyUnimonAttack() {
		int spellSize = enemyUnimon.ownedSpells.size();
		battleUnimon.loseHealth(enemyUnimon.ownedSpells.get(
				randomGenerator.nextInt(spellSize)).getDamage());
		return battleUnimon;
	}
}
