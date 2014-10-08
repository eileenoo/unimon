package de.ur.wildlings;

import java.util.ArrayList;
import java.util.Random;

import de.ur.unimon.player.Player;
import de.ur.unimon.player.PlayerController;
import de.ur.unimon.trainer.Trainer;
import de.ur.unimon.unimons.Unimon;
import de.ur.unimon.unimons.UnimonList;

public class WildlingCreator {
	
	private Trainer trainer;
	private Unimon unimon;
	private ArrayList<Unimon> wildingList;
	private Random randomGenerator;
	private PlayerController playerConroller;
	private Player player;
	private int level;
	private int skillpoints;
	private int xp;
	
	public WildlingCreator() {
		wildingList = new ArrayList<Unimon>();
		for (Unimon u : new UnimonList().getWildUnimonsList()){
			wildingList.add(u);
		}
		randomGenerator = new Random();
		player = PlayerController.getInstance();
		level = 0;
		skillpoints = 0;
	}
	
	public void createWildUnimon() {
		setBasicUnimon();
		setUnimonLevel();
		setFullHp();
		generateSpells();
		getXPToEarnForPlayer();
		setTrainer();
	}
	
	private void setBasicUnimon(){
		unimon = wildingList.get(randomGenerator.nextInt(wildingList.size()));
	}
	
	private void setUnimonLevel(){ 
		int tempLevel = player.getHigheslevelOfUnimnon();
		int levelDiffernce = randomGenerator.nextInt(3);
		int randomSign = randomGenerator.nextInt(2);
		if (randomSign == 0){
			level = tempLevel + levelDiffernce;
		} else {
			level = tempLevel - levelDiffernce;
		}
		if (level < 1) {
			level = 1;
		}
		
		unimon.setLevel(level);
	}
	
	private void setFullHp(){
		unimon.setHealth(unimon.getMaxHealth());
	}
	
	private int getSkillpoints(){
		skillpoints = unimon.getSkillPoints();
		return skillpoints;
	}
	
	private void generateSpells(){
		if (level >= 20) {
			unimon.learnSpell(unimon.getSpellBySpellNumber(5));
		}
		if (level >= 14) {
			unimon.learnSpell(unimon.getSpellBySpellNumber(4));
		}
		if (level >= 9) {
			unimon.learnSpell(unimon.getSpellBySpellNumber(3));
		}
		if (level >= 5) {
			unimon.learnSpell(unimon.getSpellBySpellNumber(2));
		}
		if (level >= 3){
			unimon.learnSpell(unimon.getSpellBySpellNumber(1));
		}
		if (level >= 1){
			unimon.learnSpell(unimon.getSpellBySpellNumber(0));
		}
		
		switch (level){
			default:
			case 20:
			case 19:
				unimon.getSpellBySpellNumber(4).levelUpSpell();
			case 18:
				unimon.getSpellBySpellNumber(4).levelUpSpell();
			case 17:
				unimon.getSpellBySpellNumber(4).levelUpSpell();
			case 16:
				unimon.getSpellBySpellNumber(4).levelUpSpell();
			case 15:
				unimon.getSpellBySpellNumber(4).levelUpSpell();
			case 14:
			case 13:
				unimon.getSpellBySpellNumber(3).levelUpSpell();
			case 12:
				unimon.getSpellBySpellNumber(3).levelUpSpell();
			case 11:
				unimon.getSpellBySpellNumber(3).levelUpSpell();
			case 10:
				unimon.getSpellBySpellNumber(3).levelUpSpell();
			case 9:
			case 8:
				unimon.getSpellBySpellNumber(2).levelUpSpell();
			case 7:
				unimon.getSpellBySpellNumber(2).levelUpSpell();
			case 6:
				unimon.getSpellBySpellNumber(2).levelUpSpell();
			case 5:
			case 4:
				unimon.getSpellBySpellNumber(1).levelUpSpell();
			case 3:
			case 2: 
				unimon.getSpellBySpellNumber(0).levelUpSpell();
			case 1:
				break;				
		}
		
		while (getSkillpoints() > 0){
			unimon.getSpellBySpellNumber(5).levelUpSpell();
		}
	
	}
	
	private void getXPToEarnForPlayer() {
		xp = (int) (unimon.getXpPerLevel()/2);
	}
	
	private void setTrainer(){
		trainer = new Trainer(99, unimon, xp, 0);
	}
	
	public Trainer getTrainer(){
		return trainer;
	}

}
