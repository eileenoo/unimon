package de.ur.unimon.battle;

import java.util.Random;

import de.ur.unimon.unimons.Spell;
import de.ur.unimon.unimons.Unimon;

public class BattleLogic {
	private Unimon player;
	private Unimon enemy;
	Random rand;

	public BattleLogic(Unimon player, Unimon enemy) {
		this.player = player;
		this.enemy = enemy;
	}

	public Unimon doDmg(Unimon attacker, Spell spell) {

		if (attacker == player) {
			enemy.loseHealth(spell.getDamage());
		}
		// if (attacker == enemy) {
		// player.loseHealth(enemy.ownedSpells.get(rand.nextInt(size))
		// .getDamage());
		// }
		return enemy;

	}

	public Unimon enemyAttack() {
		int size = enemy.ownedSpells.size();
		player.loseHealth(enemy.ownedSpells.get(rand.nextInt(size)).getDamage());
		return player;

	}

	public boolean escape() {
		boolean escape = false;
		int difference = enemy.getLevel() - player.getLevel();

		if (difference <= -2) {
			escape = true;
		}
		if (difference >= 2) {
			escape = false;
		}
		if (difference == 1 || difference == -1 || difference == 0) {
			escape = rand.nextBoolean();
		}
		return escape;

	}

	public void changeUnimon(Unimon choosenUnimon) {
		this.player = choosenUnimon;
	}

	public void useHealpot() {
		player.addHealth(50);
	}

	public boolean catchUnimon() {
		if (enemy.ownedByTrainer() == true) {
			return false;
		}
		if (enemy.ownedByTrainer() == false) {
			if (enemy.getHealth() < enemy.getMaxHealth() * 0.3) {
				return true;
			}
			if (enemy.getHealth() > enemy.getMaxHealth() * 0.7) {
				return false;
			}

		}
		return rand.nextBoolean();
	}

}
