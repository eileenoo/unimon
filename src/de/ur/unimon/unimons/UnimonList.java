package de.ur.unimon.unimons;

import java.util.ArrayList;

import de.ur.mi.android.excercises.starter.R;
import android.util.Log;

public class UnimonList {
	
	public static final String TAG = "Hoi";

	private static Unimon schleimi;
	private static Unimon kaeffchen;
	private static Unimon robotaer;
	private static Unimon hentaicha;
	private static Unimon hydroxyethan;
	private static Unimon asymptoterus;
	private static Unimon legdayskipper;
	
	private static Unimon wild1;
	private static Unimon wild2;
	private static Unimon wild3;
	private static Unimon start1;
	private static Unimon start2;
	private static Unimon start3;
	
	private static Spell schleimiSpell1;
	private static Spell schleimiSpell2;
	private static Spell schleimiSpell3;
	private static Spell schleimiSpell4;
	private static Spell schleimiSpell5;
	private static Spell schleimiSpell6;
	
	private static Spell kaeffchenSpell1;
	private static Spell kaeffchenSpell2;
	private static Spell kaeffchenSpell3;
	private static Spell kaeffchenSpell4;
	private static Spell kaeffchenSpell5;
	private static Spell kaeffchenSpell6;
	
	private static Spell hydroxyethanSpell1;
	private static Spell hydroxyethanSpell2;
	private static Spell hydroxyethanSpell3;
	private static Spell hydroxyethanSpell4;
	private static Spell hydroxyethanSpell5;
	private static Spell hydroxyethanSpell6;
	
	private static Spell hentaichaSpell1;
	private static Spell hentaichaSpell2;
	private static Spell hentaichaSpell3;
	private static Spell hentaichaSpell4;
	private static Spell hentaichaSpell5;
	private static Spell hentaichaSpell6;
	
	private static Spell robotaerSpell1;
	private static Spell robotaerSpell2;
	private static Spell robotaerSpell3;
	private static Spell robotaerSpell4;
	private static Spell robotaerSpell5;
	private static Spell robotaerSpell6;
	
	private static Spell asymptoterusSpell1;
	private static Spell asymptoterusSpell2;
	private static Spell asymptoterusSpell3;
	private static Spell asymptoterusSpell4;
	private static Spell asymptoterusSpell5;
	private static Spell asymptoterusSpell6;
	
	private static Spell legdayskipperSpell1;
	private static Spell legdayskipperSpell2;
	private static Spell legdayskipperSpell3;
	private static Spell legdayskipperSpell4;
	private static Spell legdayskipperSpell5;
	private static Spell legdayskipperSpell6;
	
	private static Spell wild1Spell1;
	private static Spell wild1Spell2;
	private static Spell wild1Spell3;
	private static Spell wild1Spell4;
	private static Spell wild1Spell5;
	private static Spell wild1Spell6;
	
	private static Spell wild2Spell1;
	private static Spell wild2Spell2;
	private static Spell wild2Spell3;
	private static Spell wild2Spell4;
	private static Spell wild2Spell5;
	private static Spell wild2Spell6;
	
	private static Spell wild3Spell1;
	private static Spell wild3Spell2;
	private static Spell wild3Spell3;
	private static Spell wild3Spell4;
	private static Spell wild3Spell5;
	private static Spell wild3Spell6;
	
	private static Spell start1Spell1;
	private static Spell start1Spell2;
	private static Spell start1Spell3;
	private static Spell start1Spell4;
	private static Spell start1Spell5;
	private static Spell start1Spell6;
	
	private static Spell start2Spell1;
	private static Spell start2Spell2;
	private static Spell start2Spell3;
	private static Spell start2Spell4;
	private static Spell start2Spell5;
	private static Spell start2Spell6;
	
	private static Spell start3Spell1;
	private static Spell start3Spell2;
	private static Spell start3Spell3;
	private static Spell start3Spell4;
	private static Spell start3Spell5;
	private static Spell start3Spell6;
	

	public ArrayList<Unimon> allUnimonsList;
	public ArrayList<Unimon> wildUnimonsList;
	public ArrayList<Unimon> startUnimonsList;
	public ArrayList<Unimon> trainerUnimonsList;

	public UnimonList() {
		
		allUnimonsList = new ArrayList<Unimon>();
		wildUnimonsList = new ArrayList<Unimon>();
		startUnimonsList = new ArrayList<Unimon>();
		trainerUnimonsList = new ArrayList<Unimon>();
		

		
		// ausgeglichen
		schleimi = new Unimon("Schleimi", 123, true, R.drawable.robomon);
		schleimiSpell1 = new Spell("Scratch", 1, 15);
		schleimiSpell2 = new Spell("Bite", 2, 25);
		schleimiSpell3 = new Spell("Headbutt",3,35);
		schleimiSpell4 = new Spell("Punch",4,45);
		schleimiSpell5 = new Spell ("Kick",5,55);
		schleimiSpell6 = new Spell ("Earthquake",6,65);
		schleimi.possibleSpells.add(schleimiSpell1);
		schleimi.possibleSpells.add(schleimiSpell2);
		schleimi.possibleSpells.add(schleimiSpell3);
		schleimi.possibleSpells.add(schleimiSpell4);
		schleimi.possibleSpells.add(schleimiSpell5);
		schleimi.possibleSpells.add(schleimiSpell6);
		schleimi.setLevel(3);
		schleimi.learnSpell(schleimiSpell1);
		schleimi.getSpellBySpellNumber(0).setSpellLevel(2);
		schleimi.learnSpell(schleimiSpell2);
		schleimi.setHealth(schleimi.getMaxHealth());
		

		//defensiver
		kaeffchen = new Unimon("Käffchen", 182, true, R.drawable.kaeffchen);
		kaeffchenSpell1 = new Spell ("Take Down",1,10);
		kaeffchenSpell2 = new Spell ("Flamethrower",2,15);
		kaeffchenSpell3 = new Spell ("Low Kick",3,20);
		kaeffchenSpell4 = new Spell ("Rock Throw",4,25);
		kaeffchenSpell5 = new Spell ("Glare",5,35);
		kaeffchenSpell6 = new Spell ("Blizzard",6,40);
		kaeffchen.addPossibleSpell(kaeffchenSpell1);
		kaeffchen.addPossibleSpell(kaeffchenSpell2);
		kaeffchen.addPossibleSpell(kaeffchenSpell3);
		kaeffchen.addPossibleSpell(kaeffchenSpell4);
		kaeffchen.addPossibleSpell(kaeffchenSpell5);
		kaeffchen.addPossibleSpell(kaeffchenSpell6);
		kaeffchen.learnSpell(kaeffchenSpell1);
		kaeffchen.learnSpell(kaeffchenSpell2);
		kaeffchen.learnSpell(kaeffchenSpell3);
		kaeffchen.learnSpell(kaeffchenSpell4);
		kaeffchen.setLevel(9);
		kaeffchen.getSpellBySpellNumber(0).setSpellLevel(2);
		kaeffchen.getSpellBySpellNumber(1).setSpellLevel(2);
		kaeffchen.getSpellBySpellNumber(2).setSpellLevel(4);
		kaeffchen.setHealth(kaeffchen.getMaxHealth());

		//offensiver
		robotaer = new Unimon("Robotär", 93, true, R.drawable.robomon);
		robotaerSpell1 = new Spell ("Kick",1,30);
		robotaerSpell2 = new Spell("Take Down", 2, 40);
		robotaerSpell3 = new Spell ("Ice Shard",3,45);
		robotaerSpell4 = new Spell ("Head Smash",4,60);
		robotaerSpell5 = new Spell("Dynamic Punch",5,65);
		robotaerSpell6 = new Spell ("Machinegun",6,80);
		robotaer.addPossibleSpell(robotaerSpell1);
		robotaer.addPossibleSpell(robotaerSpell2);
		robotaer.addPossibleSpell(robotaerSpell3);
		robotaer.addPossibleSpell(robotaerSpell4);
		robotaer.addPossibleSpell(robotaerSpell5);
		robotaer.addPossibleSpell(robotaerSpell6);
		robotaer.setLevel(12);
		robotaer.learnSpell(robotaerSpell1);
		robotaer.learnSpell(robotaerSpell2);
		robotaer.learnSpell(robotaerSpell3);
		robotaer.learnSpell(robotaerSpell4);
		robotaer.getSpellBySpellNumber(0).setSpellLevel(2);
		robotaer.getSpellBySpellNumber(1).setSpellLevel(2);
		robotaer.getSpellBySpellNumber(2).setSpellLevel(4);
		robotaer.getSpellBySpellNumber(3).setSpellLevel(4);
		robotaer.setHealth(robotaer.getMaxHealth());

		
		//ausgeglichen
		hentaicha = new Unimon("Hentaicha", 113, true, R.drawable.kraki);
		hentaichaSpell1 =  new Spell ("Wood Hammer",1,16);
		hentaichaSpell2  = new Spell("Bite", 2, 24);
		hentaichaSpell3  = new Spell("Icicle Spear", 3, 34);
		hentaichaSpell4  = new Spell("Beat Up", 4, 47);
		hentaichaSpell5  = new Spell("Sucker Punch", 5, 55);
		hentaichaSpell6  = new Spell("Poison Fang", 6, 63);
		hentaicha.addPossibleSpell(hentaichaSpell1);
		hentaicha.addPossibleSpell(hentaichaSpell2);
		hentaicha.addPossibleSpell(hentaichaSpell3);
		hentaicha.addPossibleSpell(hentaichaSpell4);
		hentaicha.addPossibleSpell(hentaichaSpell5);
		hentaicha.addPossibleSpell(hentaichaSpell6);
		hentaicha.learnSpell(hentaichaSpell1);
		hentaicha.setHealth(hentaicha.getMaxHealth());
		

		// sehr defensiv
		hydroxyethan = new Unimon("Hydroxyethan", 260, true, R.drawable.robomon);
		hydroxyethanSpell1 = new Spell ("Sucker Punch",1,11);
		hydroxyethanSpell2 = new Spell("Bite", 2, 22);
		hydroxyethanSpell3 = new Spell ("Explosion",3,33);
		hydroxyethanSpell4 = new Spell ("Low Kick",4,44);
		hydroxyethanSpell5 = new Spell("Flamethrower",5,50);
		hydroxyethanSpell6 = new Spell ("Headbutt",6,55);
		hydroxyethan.addPossibleSpell(hydroxyethanSpell1);
		hydroxyethan.addPossibleSpell(hydroxyethanSpell2);
		hydroxyethan.addPossibleSpell(hydroxyethanSpell3);
		hydroxyethan.addPossibleSpell(hydroxyethanSpell4);
		hydroxyethan.addPossibleSpell(hydroxyethanSpell5);
		hydroxyethan.addPossibleSpell(hydroxyethanSpell6);
		hydroxyethan.setLevel(16);
		hydroxyethan.learnSpell(hydroxyethanSpell1);
		hydroxyethan.learnSpell(hydroxyethanSpell2);
		hydroxyethan.learnSpell(hydroxyethanSpell3);
		hydroxyethan.learnSpell(hydroxyethanSpell4);
		hydroxyethan.learnSpell(hydroxyethanSpell5);
		hydroxyethan.getSpellBySpellNumber(0).setSpellLevel(2);
		hydroxyethan.getSpellBySpellNumber(1).setSpellLevel(2);
		hydroxyethan.getSpellBySpellNumber(2).setSpellLevel(4);
		hydroxyethan.getSpellBySpellNumber(3).setSpellLevel(5);
		hydroxyethan.getSpellBySpellNumber(4).setSpellLevel(3);
		hydroxyethan.setHealth(hydroxyethan.getMaxHealth());
		
		
		
		//sehr offensiv und hält relativ viel aus
		asymptoterus = new Unimon("Asymptoterus", 124, true, R.drawable.robomon);
		asymptoterusSpell1 = new Spell ("Giga Impact",1,32);
		asymptoterusSpell2 = new Spell("Icicle Spear", 2, 43);
		asymptoterusSpell3 = new Spell ("Explosion",3,54);
		asymptoterusSpell4 = new Spell ("Blizzard",4,65);
		asymptoterusSpell5 = new Spell("Flamethrower",5,74);
		asymptoterusSpell6 = new Spell ("Head Smash",6,85);
		asymptoterus.addPossibleSpell(asymptoterusSpell1);
		asymptoterus.addPossibleSpell(asymptoterusSpell2);
		asymptoterus.addPossibleSpell(asymptoterusSpell3);
		asymptoterus.addPossibleSpell(asymptoterusSpell4);
		asymptoterus.addPossibleSpell(asymptoterusSpell5);
		asymptoterus.addPossibleSpell(asymptoterusSpell6);
		asymptoterus.setLevel(5);
		asymptoterus.learnSpell(asymptoterusSpell1);
		asymptoterus.learnSpell(asymptoterusSpell2);
		asymptoterus.learnSpell(asymptoterusSpell3);
		asymptoterus.getSpellBySpellNumber(0).setSpellLevel(2);
		asymptoterus.getSpellBySpellNumber(1).setSpellLevel(2);
		asymptoterus.setHealth(asymptoterus.getMaxHealth());
		
		
		//oberboss stark defensiv+offensiv
		legdayskipper = new Unimon("Legdayskipper", 210, true, R.drawable.robomon);
		legdayskipperSpell1 = new Spell ("Giga Impact",1,33);
		legdayskipperSpell2 = new Spell("Take Down", 2, 44);
		legdayskipperSpell3 = new Spell ("Low Kick",3,55);
		legdayskipperSpell4 = new Spell ("Punch",4,66);
		legdayskipperSpell5 = new Spell("Jump Kick",5,77);
		legdayskipperSpell6 = new Spell ("Tail Whip",6,88);
		legdayskipper.addPossibleSpell(legdayskipperSpell1);
		legdayskipper.addPossibleSpell(legdayskipperSpell2);
		legdayskipper.addPossibleSpell(legdayskipperSpell3);
		legdayskipper.addPossibleSpell(legdayskipperSpell4);
		legdayskipper.addPossibleSpell(legdayskipperSpell5);
		legdayskipper.addPossibleSpell(legdayskipperSpell6);
		legdayskipper.setLevel(20);
		legdayskipper.learnSpell(legdayskipperSpell1);
		legdayskipper.learnSpell(legdayskipperSpell2);
		legdayskipper.learnSpell(legdayskipperSpell3);
		legdayskipper.learnSpell(legdayskipperSpell4);
		legdayskipper.learnSpell(legdayskipperSpell5);
		legdayskipper.learnSpell(legdayskipperSpell6);
		legdayskipper.getSpellBySpellNumber(0).setSpellLevel(2);
		legdayskipper.getSpellBySpellNumber(0).setSpellLevel(2);
		legdayskipper.getSpellBySpellNumber(0).setSpellLevel(4);
		legdayskipper.getSpellBySpellNumber(0).setSpellLevel(5);
		legdayskipper.getSpellBySpellNumber(0).setSpellLevel(6);
		legdayskipper.getSpellBySpellNumber(0).setSpellLevel(1);
		legdayskipper.setHealth(legdayskipper.getMaxHealth());
		
		
		
		//sehr offensiv
		wild1 = new Unimon("Eimerchen", 130, false, R.drawable.eimerchen);
		wild1Spell1 =  new Spell ("Giga Impact",1,31);
		wild1Spell2  = new Spell("Ice Shard", 2, 42);
		wild1Spell3  = new Spell("Bite", 3, 53);
		wild1Spell4  = new Spell("Double Hit", 4, 64);
		wild1Spell5  = new Spell("Slap in the Face", 5, 75);
		wild1Spell6  = new Spell("Head Smash", 6, 86);
		wild1.addPossibleSpell(wild1Spell1);
		wild1.addPossibleSpell(wild1Spell2);
		wild1.addPossibleSpell(wild1Spell3);
		wild1.addPossibleSpell(wild1Spell4);
		wild1.addPossibleSpell(wild1Spell5);
		wild1.addPossibleSpell(wild1Spell6);
		wild1.setHealth(wild1.getMaxHealth());

		//defensiv
		wild2 = new Unimon("Wildie2", 210, false, R.drawable.robomon);
		wild2Spell1 =  new Spell ("Mud Bomb",1,15);
		wild2Spell2  = new Spell("Head Smash", 2, 26);
		wild2Spell3  = new Spell("Electro Ball", 3, 33);
		wild2Spell4  = new Spell("Fling", 4, 47);
		wild2Spell5  = new Spell("Machinegun", 5, 55);
		wild2Spell6  = new Spell("Blow of an Ax", 6, 63);
		wild2.addPossibleSpell(wild2Spell1);
		wild2.addPossibleSpell(wild2Spell2);
		wild2.addPossibleSpell(wild2Spell3);
		wild2.addPossibleSpell(wild2Spell4);
		wild2.addPossibleSpell(wild2Spell5);
		wild2.addPossibleSpell(wild2Spell6);
		wild2.setHealth(wild2.getMaxHealth());

		//ausgeglichen
		wild3 = new Unimon("Wildie3", 130, false, R.drawable.robomon);
		wild3Spell1 =  new Spell ("Tail Whip",1,15);
		wild3Spell2  = new Spell("Gunshot", 2, 25);
		wild3Spell3  = new Spell("Storm", 3, 35);
		wild3Spell4  = new Spell("Fling", 4, 45);
		wild3Spell5  = new Spell("Double Hit", 5, 55);
		wild3Spell6  = new Spell("Sand Attack", 6, 65);
		wild3.addPossibleSpell(wild3Spell1);
		wild3.addPossibleSpell(wild3Spell2);
		wild3.addPossibleSpell(wild3Spell3);
		wild3.addPossibleSpell(wild3Spell4);
		wild3.addPossibleSpell(wild3Spell5);
		wild3.addPossibleSpell(wild3Spell6);
		wild3.setHealth(wild3.getMaxHealth());


		//neutral
		start1 = new Unimon("Facemeltor", 100, false, R.drawable.facemeltor);
		start1Spell1 =  new Spell ("Jump Kick",1,15);
		start1Spell2 =  new Spell ("Overdrive",1,25);
		start1Spell3 =  new Spell ("Mega Punch",1,35);
		start1Spell4 =  new Spell ("Electro Ball",1,45);
		start1Spell5 =  new Spell ("Dizzy Punch",1,55);
		start1Spell6 =  new Spell ("Beat Up",1,65);
		start1.addPossibleSpell(start1Spell1);
		start1.addPossibleSpell(start1Spell2);
		start1.addPossibleSpell(start1Spell3);
		start1.addPossibleSpell(start1Spell4);
		start1.addPossibleSpell(start1Spell5);
		start1.addPossibleSpell(start1Spell6);
		start1.learnSpell(start1Spell1);
		start1.setHealth(start1.getMaxHealth());
		
		//offensiv
		start2 = new Unimon("Charlie", 90, false, R.drawable.charlie);
		start2Spell1 =  new Spell ("Karate Chop",1,17);
		start2Spell2 =  new Spell ("Cut",1,27);
		start2Spell3 =  new Spell ("Fire Spin",1,37);
		start2Spell4 =  new Spell ("Arm Thrust",1,47);
		start2Spell5 =  new Spell ("Thunder Punch",1,57);
		start2Spell6 =  new Spell ("Rock Blast",1,67);
		start2.addPossibleSpell(start2Spell1);
		start2.addPossibleSpell(start2Spell2);
		start2.addPossibleSpell(start2Spell3);
		start2.addPossibleSpell(start2Spell4);
		start2.addPossibleSpell(start2Spell5);
		start2.addPossibleSpell(start2Spell6);
		start2.learnSpell(start2Spell1);
		start2.setHealth(start2.getMaxHealth());
		
		//defensiv
		start3 = new Unimon("Bademeistär", 110, false, R.drawable.bademeister);
		start3Spell1 =  new Spell ("Power Trick",1,13);
		start3Spell2 =  new Spell ("Poison Sting",1,23);
		start3Spell3 =  new Spell ("Electro Ball",1,33);
		start3Spell4 =  new Spell ("Noble Roar",1,43);
		start3Spell5 =  new Spell ("Jump Kick",1,53);
		start3Spell6 =  new Spell ("Wild Charge",1,63);
		start3.addPossibleSpell(start3Spell1);
		start3.addPossibleSpell(start3Spell2);
		start3.addPossibleSpell(start3Spell3);
		start3.addPossibleSpell(start3Spell4);
		start3.addPossibleSpell(start3Spell5);
		start3.addPossibleSpell(start3Spell6);
		start3.learnSpell(start1Spell1);
		start3.setHealth(start3.getMaxHealth());
		
		
		
		
		
		allUnimonsList.add(schleimi);
		allUnimonsList.add(kaeffchen);
		allUnimonsList.add(robotaer);
		allUnimonsList.add(hentaicha);
		allUnimonsList.add(hydroxyethan);
		allUnimonsList.add(asymptoterus);
		allUnimonsList.add(legdayskipper);
		allUnimonsList.add(wild1);
		allUnimonsList.add(wild2);
		allUnimonsList.add(wild3);
		allUnimonsList.add(start1);
		allUnimonsList.add(start2);
		allUnimonsList.add(start3);
		
		wildUnimonsList.add(wild1);
		wildUnimonsList.add(wild2);
		wildUnimonsList.add(wild3);
		
		startUnimonsList.add(start1);
		startUnimonsList.add(start2);
		startUnimonsList.add(start3);
		
		trainerUnimonsList.add(schleimi);
		trainerUnimonsList.add(kaeffchen);
		trainerUnimonsList.add(robotaer);
		trainerUnimonsList.add(hentaicha);
		trainerUnimonsList.add(hydroxyethan);
		trainerUnimonsList.add(asymptoterus);
		trainerUnimonsList.add(legdayskipper);
		
	}
	
	// gibt ArrayListe mit allen existierenden Unimons zurück
	public ArrayList<Unimon> getAllUnimonsList(){
		return allUnimonsList;
	}
	
	// gibt ArrayListe mit allen wilden Unimons zurück
	public ArrayList<Unimon> getWildUnimonsList(){
		return wildUnimonsList;
	}
	
	// gibt ArrayListe mit den 3 Unimons zurück, die bei Spielbeginn ausgewählt werden können
	public ArrayList<Unimon> getStartUnimonList(){
		return startUnimonsList;
	}
	
	// gibt ArrayListe mit allen Unimons der Trainer zurück
	public ArrayList<Unimon> getTrainerUnimonsList() {
		return trainerUnimonsList;
	}

}
