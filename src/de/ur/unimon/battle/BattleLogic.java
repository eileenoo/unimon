package de.ur.unimon.battle;

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

	public Unimon ownUnimonAttack(Unimon attacker, Spell spell) {

		if (attacker == playerUnimon) {
			enemyUnimon.loseHealth(spell.getDamage());
		}
		// if (attacker == enemy) {
		// player.loseHealth(enemy.ownedSpells.get(rand.nextInt(size))
		// .getDamage());
		// }
		
		if (enemyUnimon.getHealth() >= 0) {
			fightWon();
		}
		return enemyUnimon;

	}

	private void fightWon() {
		//Player addMoney
	}

	public Unimon enemyUnimonAttack() {
		int spellSize = enemyUnimon.ownedSpells.size();
		playerUnimon.loseHealth(enemyUnimon.ownedSpells.get(randomGenerator.nextInt(spellSize)).getDamage());
		if (playerUnimon.getHealth() >= 0) {
			fightLost();
		}
		return playerUnimon;
	}

	private void fightLost() {
		// TODO Auto-generated method stub
		
	}

	public boolean escape() {
		boolean escape = false;
		int difference = enemyUnimon.getLevel() - playerUnimon.getLevel();

		if (difference <= -2) {
			escape = true;
		}
		if (difference >= 2) {
			escape = false;
		}
		if (difference == 1 || difference == -1 || difference == 0) {
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

	public boolean catchUnimon() {
		if (enemyUnimon.ownedByTrainer() == true) {
			return false;
		}
		if (enemyUnimon.ownedByTrainer() == false) {
			if (enemyUnimon.getHealth() < enemyUnimon.getMaxHealth() * 0.3) {
				return true;
			}
			if (enemyUnimon.getHealth() > enemyUnimon.getMaxHealth() * 0.7) {
				return false;
			}

		}
		return randomGenerator.nextBoolean();
	}

}
