package de.ur.unimon.unimons;

import java.util.Random;

public class Spell {
	
	private String name;
	private int baseDamage, damage;
	private int spellLevel;
	private int spellNumber;
	private boolean isLearned;
	
	public Spell(String name, int spellNumber, int baseDamage){
		this.name = name;
		this.spellNumber = spellNumber;
		this.baseDamage = baseDamage;
		spellLevel = 1;
		isLearned = false;
	}
	
	public void learnSpell(){
		isLearned = true;
	}
	
	public boolean isLearned(){
		return isLearned;
	}
	
	public int getSpellNumber(){
		return spellNumber;
	}
	
	public void setSpellLevel(int level){
		this.spellLevel = level;
	}
	
	public int getSpellLevel(){
		return spellLevel;
	}
	
	public void levelUpSpell(){
		this.spellLevel += 1;
	}
	
	public String getSpellName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setBaseDamage(int baseDamage){
		this.baseDamage = baseDamage;
	}
	
	public int getBaseDamage(){
		return baseDamage;
	}
	
	public void setDamage(int damage){
		this.damage = damage;
	}
	
	public int getDamage(){
		calculateSpellDamge();
		return damage;
	}

	private void calculateSpellDamge() {
		Random randomDmgCalculator = new Random();
		
		int tempDamage = (int) (baseDamage + Math.exp((spellLevel/2))+5);
		int randomBonusDmg = randomDmgCalculator.nextInt((int) (tempDamage*0.2)+1);
		int randomSign = randomDmgCalculator.nextInt(2);
		if (randomSign == 0){
			damage = tempDamage + randomBonusDmg;
		} else {
			damage = tempDamage - randomBonusDmg;
		}
		
		int critChance = randomDmgCalculator.nextInt(7);
		int missChance = randomDmgCalculator.nextInt(14);
		
		if (critChance == 1){
			damage *= 1.5;
		}
		if (missChance == 1){
			damage = 0;
		}
	}
}
