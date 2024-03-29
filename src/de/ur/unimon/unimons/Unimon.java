package de.ur.unimon.unimons;

import java.util.ArrayList;

public class Unimon {

	private String name;
	private int level, xp, xpPerLevel;
	public int skillPoints, image;
	private int health, baseHealth, maxHealth;
	public ArrayList<Spell> possibleSpells;
	public ArrayList<Spell> notYetLearnedSpells;
	public ArrayList<Spell> ownedSpells;
	public ArrayList<Spell> notYetLearnedSpellsLvl;
	private boolean isAlive;
	private boolean ownedByTrainer;

	public Unimon(String name, int baseHealth, boolean ownedByTrainer, int image) {
		this.name = name;
		this.baseHealth = baseHealth;
		this.image = image;
		maxHealth = 0;
		health = getMaxHealth();
		level = 1;
		xp = 0;
		possibleSpells = new ArrayList<Spell>();
		notYetLearnedSpells = new ArrayList<Spell>();
		notYetLearnedSpellsLvl = new ArrayList<Spell>();
		ownedSpells = new ArrayList<Spell>();
		setAlive(true);
		this.ownedByTrainer = ownedByTrainer;
		xpPerLevel = 0;
		skillPoints = 0;

	}

	public void reset() {
		level = 1;
		maxHealth = getMaxHealth();
		for (Spell s : possibleSpells) {
			s.setSpellLevel(0);
			s.forgetSpell();
		}
		notYetLearnedSpells.clear();
		for (int i = 0; i < 6; i++) {
			notYetLearnedSpells.add(possibleSpells.get(i));
		}

		ownedSpells.clear();
	}

	public void useHealpot() {
		int newHealth = health + (int) (Math.round(maxHealth * 0.40f));
		if (newHealth > maxHealth) {
			health = maxHealth;
		} else
			health = newHealth;
	}

	public int getHealpotAmount() {
		return (int) (Math.round(maxHealth * 0.40f));
	}

	public boolean ownedByTrainer() {
		return ownedByTrainer;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void levelUp() {
		level += 1;
	}

	public void setSkillpoints(int skillPoints) {
		this.skillPoints = skillPoints;
	}

	public int getSkillPoints() {
		calculateSkillPoints();
		return skillPoints;
	}

	private void calculateSkillPoints() {
		int usedSkillPoints = 0;
		for (int i = 0; i < ownedSpells.size(); i++) {
			usedSkillPoints += ownedSpells.get(i).getSpellLevel();
		}
		skillPoints = level - usedSkillPoints;

	}

	public void setXpPerLevel(int xpPerLevel) {
		this.xpPerLevel = xpPerLevel;
	}

	public int getXpPerLevel() {
		calculateXpPerLevel();
		return xpPerLevel;
	}

	private void calculateXpPerLevel() {
		setXpPerLevel((int) (100 + ((level * level / 1.5) + 10)));
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getXp() {
		return xp;
	}

	public void addXp(int amount) {
		if (xp + amount < getXpPerLevel()) {
			xp += amount;
		} else {
			xp = (xp + amount) - getXpPerLevel();
			levelUp();
		}
		while (xp > getXpPerLevel()) {
			int xpOverflow = xp - getXpPerLevel();
			levelUp();
			xp = xpOverflow;
		}
	}

	public void setBaseHealth(int baseHealth) {
		this.baseHealth = baseHealth;
	}

	public int getBaseHealth() {
		return baseHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void setToMaxHealth() {
		health = maxHealth;
	}

	public int getMaxHealth() {
		calculateMaxHealth();
		return maxHealth;
	}

	private void calculateMaxHealth() {
		maxHealth = (int) ((baseHealth + level * 24) * 1.5);
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealth() {
		return health;
	}

	public void addHealth(int amount) {
		if (health + amount > maxHealth) {
			health = maxHealth;
		} else {
			health += amount;
		}

	}

	public void loseHealth(int amount) {
		if (health - amount < 0) {
			health = 0;
			setAlive(false);
		} else {
			health -= amount;
		}
	}

	public boolean isAlive() {
		if (health > 0) {
			isAlive = true;
		} else {
			isAlive = false;
		}
		return isAlive;
	}

	public Spell getSpellBySpellNumber(int number) {
		return possibleSpells.get(number);
	}

	public ArrayList<Spell> getOwnedSpells() {
		return ownedSpells;
	}

	public void addPossibleSpell(Spell spell) {
		possibleSpells.add(spell);
		notYetLearnedSpells.add(spell);
	}

	public ArrayList<Spell> getNotYetLearnedSpellList() {
		return notYetLearnedSpells;
	}

	public ArrayList<Spell> getPossibleSpells() {
		return possibleSpells;
	}

	public void learnSpell(Spell spell) {
		spell.levelUpSpell();
		spell.learnSpell();
		ownedSpells.add(spell);
		for (int i = 0; i < notYetLearnedSpells.size(); i++) {
			if (notYetLearnedSpells.get(i) == spell) {
				notYetLearnedSpells.remove(i);
			}
		}
		for (int i = 0; i < notYetLearnedSpellsLvl.size(); i++) {
			if (notYetLearnedSpellsLvl.get(i) == spell) {
				notYetLearnedSpellsLvl.remove(i);
			}
		}
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public void setImageResource(int image) {
		this.image = image;
	}

	public int getImage() {
		return image;
	}

}
