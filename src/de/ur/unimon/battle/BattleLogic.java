package de.ur.unimon.battle;

import java.util.Random;
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

	public Unimon enemyUnimonAttack() {
		int spellSize = enemyUnimon.ownedSpells.size();
		playerUnimon.loseHealth(enemyUnimon.ownedSpells.get(
				randomGenerator.nextInt(spellSize)).getDamage());
		if (playerUnimon.getHealth() >= 0) {
			fightLost();
		}
		return playerUnimon;
	}

	public boolean ableToEscape() {
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
	
	
	private void fightLost() {
		//gesundheit des pokemons ist null
		//

	}

	private void fightWon() {
		// addmoney in player
	}

}
