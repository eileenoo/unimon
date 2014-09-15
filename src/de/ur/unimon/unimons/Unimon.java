package de.ur.unimon.unimons;

import java.util.ArrayList;

public class Unimon {
	
	private String name;
	private int level, xp, xpPerLevel;
	public int skillPoints;
	private int health, baseHealth, maxHealth;
	public ArrayList<Spell> possibleSpells;
	public ArrayList<Spell> notYetLearnedSpells;
	public ArrayList<Spell> ownedSpells;
	private boolean isAlive;
	private boolean ownedByTrainer;
	
	public Unimon(String name, int baseHealth, boolean ownedByTrainer){
		this.name = name;	
		this.baseHealth = baseHealth;
		maxHealth = 0;
		health = getMaxHealth();
		level = 1;
		xp = 0;
		possibleSpells = new ArrayList<Spell>();
		notYetLearnedSpells = new ArrayList<Spell>();
		ownedSpells = new ArrayList<Spell>();
		setAlive(true);
		this.ownedByTrainer = ownedByTrainer;
		xpPerLevel = 0; 
		skillPoints = 0; 
		
		
	}
	
	public void useHealpot(){
		int newHealth = health + (int) (Math.round(maxHealth*0.20f));
		if (newHealth > maxHealth){
			health = maxHealth;
		} else health = newHealth;
	}
	
	public int getHealpotAmount(){
		return (int) (Math.round(maxHealth*0.20f));
	}
	
	public boolean ownedByTrainer(){
		return ownedByTrainer;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public int getLevel(){
		return level;
	}
	
	public void levelUp(){
		level += 1;
	}
	
	public void setSkillpoints (int skillPoints) {
		this.skillPoints = skillPoints;
	}
	
	public int getSkillPoints() {
		calculateSkillPoints();
		return skillPoints;
	}
	
	private void calculateSkillPoints() {
		int usedSkillPoints = 0;
		for (int i=0; i<ownedSpells.size(); i++){
			usedSkillPoints += ownedSpells.get(i).getSpellLevel();
		}
		skillPoints = level - usedSkillPoints;
		
	}

	public void setXpPerLevel(int xpPerLevel){
		this.xpPerLevel = xpPerLevel;
	}
	
	public int getXpPerLevel(){
		calculateXpPerLevel();
		return xpPerLevel;
	}
	
	private void calculateXpPerLevel() {
		setXpPerLevel(100 + (int) Math.exp((level/2d))+10);
	}

	public void setXp(int xp) {
		this.xp = xp;
	}
	
	public int getXp(){
		return xp;
	}
	
	public void addXp(int amount){
		if (xp + amount < getXpPerLevel()){
			xp += amount;
		}
		else {
			xp = (xp + amount) - getXpPerLevel();
			levelUp();
		}
	}
	
	public void setBaseHealth(int baseHealth){
		this.baseHealth = baseHealth;
	}
	
	public int getBaseHealth(){
		return baseHealth;
	}
	
	public void setMaxHealth(int maxHealth){
		this.maxHealth = maxHealth;
	}
	
	public void setToMaxHealth() {
		health = maxHealth;
	}
	
	public int getMaxHealth(){
		calculateMaxHealth();
		return maxHealth;
	}
	
	private void calculateMaxHealth(){
//		setMaxHealth((int) (baseHealth + Math.exp((level/15d))+10));
		maxHealth = (int) (baseHealth + level*17+level*7);
	}
	
	public void setHealth(int health){
		this.health = health;
	}
	
	public int getHealth(){
		return health;
	}
	
	public void addHealth(int amount){
		if (health + amount > maxHealth){
			health = maxHealth;
		} else {
			health += amount;
		}
		
	}
	
	public void loseHealth(int amount){
		if (health - amount < 0){
			health = 0;
			setAlive(false);
		} else {
			health -= amount;
		}
	}
	
	public boolean isAlive(){
		if (health > 0){
			isAlive = true;
		} else {
			isAlive = false;
		}
		return isAlive;
	}
	
	public Spell getSpellBySpellNumber(int number){
		return possibleSpells.get(number);
	}
	
	public ArrayList<Spell> getOwnedSpells(){
		return ownedSpells;
	}
	
	public void addPossibleSpell(Spell spell){
		possibleSpells.add(spell);
		notYetLearnedSpells.add(spell);
	}
	
	public ArrayList<Spell> getNotYetLearnedSpellList(){
		return notYetLearnedSpells;
	}
	
	public ArrayList<Spell> getPossibleSpells() {
		return possibleSpells;
	}
	
	public void learnSpell(Spell spell){
		spell.levelUpSpell();
		spell.learnSpell();
		ownedSpells.add(spell);
		for (int i=0; i<notYetLearnedSpells.size(); i++){
			if (notYetLearnedSpells.get(i) == spell){
				notYetLearnedSpells.remove(i);
			}
		}
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

}
