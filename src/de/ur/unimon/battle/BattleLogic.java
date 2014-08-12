package de.ur.unimon.battle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import de.ur.unimon.actionbar.Player;
import de.ur.unimon.unimons.Spell;
import de.ur.unimon.unimons.Unimon;

public class BattleLogic {
	private Unimon playerUnimon;
	private Unimon enemyUnimon;
	Random randomGenerator;

	public BattleLogic(Unimon playerUnimon, Unimon enemyUnimon) {
		this.playerUnimon = playerUnimon;
		this.enemyUnimon = enemyUnimon;
	}

	public Unimon ownUnimonAttack(Spell spell) {
		enemyUnimon.loseHealth(spell.getDamage());
		if (enemyUnimon.getHealth() >= 0) {
			fightWon();
		}
		return enemyUnimon;
	}

	private void fightWon() {
// addmoney in player
	}

	public Unimon enemyUnimonAttack() {
		int spellSize = enemyUnimon.ownedSpells.size();
		playerUnimon.loseHealth(enemyUnimon.ownedSpells.get(
				randomGenerator.nextInt(spellSize)).getDamage());
		if (playerUnimon.getHealth() >= 0) {
			fightLost();
		}
		return playerUnimon;
	}

	private void fightLost() {
		// TODO Auto-generated method stub

	}

	public boolean tryToEscape() {
		boolean escape = false;
		int difference = enemyUnimon.getLevel() - playerUnimon.getLevel();

		if (difference <= -2) {
			escape = true;
		} else if (difference >= 2) {
			escape = false;
		} else if (difference == 1 || difference == -1 || difference == 0) {
			escape = randomGenerator.nextBoolean();
		}

		return escape;
	}

	public void changeUnimon(Unimon choosenUnimon) {
		this.playerUnimon = choosenUnimon;
	}

	public void useHealpot() {
		playerUnimon.addHealth(50);
	}

	// Sollte in Tips & Tricks stehen: Man kann ein Unimon nur fangen, wenn es
	// ein wildes Unimon ist
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

}
