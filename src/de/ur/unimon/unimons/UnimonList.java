package de.ur.unimon.unimons;

import java.util.ArrayList;

import android.util.Log;

public class UnimonList {
	
	public static final String TAG = "Hoi";

	private static Unimon krisk;
	private static Unimon neni;
	private static Unimon brunz;
	private static Unimon lisa;
	private static Unimon wild1;
	private static Unimon wild2;
	private static Unimon wild3;
	private static Spell kriskSpell1;
	private static Spell kriskSpell2;
	private static Spell kriskSpell3;
	private static Spell kriskSpell4;
	private static Spell kriskSpell5;
	private static Spell kriskSpell6;
	private static Spell neniSpell1;
	private static Spell neniSpell2;
	private static Spell neniSpell3;
	private static Spell neniSpell4;
	private static Spell neniSpell5;
	private static Spell neniSpell6;
	private static Spell brunzSpell1;
	private static Spell brunzSpell2;
	private static Spell brunzSpell3;
	private static Spell brunzSpell4;
	private static Spell brunzSpell5;
	private static Spell brunzSpell6;
	private static Spell lisaSpell1;
	private static Spell lisaSpell2;
	private static Spell lisaSpell3;
	private static Spell lisaSpell4;
	private static Spell lisaSpell5;
	private static Spell lisaSpell6;
	
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
	

	public ArrayList<Unimon> unimonList;
	public ArrayList<Unimon> catchableUnimonList;

	public UnimonList() {
		
		unimonList = new ArrayList<Unimon>();
		catchableUnimonList = new ArrayList<Unimon>();
		
		krisk = new Unimon("Krisk", 10, true);
		kriskSpell1 = new Spell("Kratzer", 1, 15);
		kriskSpell2 = new Spell("Biss", 2, 20);
		kriskSpell3 = new Spell("Kopfstoß",3,20);
		kriskSpell4 = new Spell("Punch",4,15);
		kriskSpell5 = new Spell ("Kick",5,20);
		kriskSpell6 = new Spell ("Tackle",6,20);
		krisk.possibleSpells.add(kriskSpell1);
		krisk.possibleSpells.add(kriskSpell2);
		krisk.possibleSpells.add(kriskSpell3);
		krisk.possibleSpells.add(kriskSpell4);
		krisk.possibleSpells.add(kriskSpell5);
		krisk.possibleSpells.add(kriskSpell6);
		krisk.ownedSpells.add(kriskSpell1);
		krisk.setHealth(19);

		neni = new Unimon("Neni", 30, true);
		neniSpell1 = new Spell ("bla",1,10);
		neniSpell2 = new Spell ("bli",2,15);
		neniSpell3 = new Spell ("blub",3,15);
		neniSpell4 = new Spell ("mip",4,15);
		neniSpell5 = new Spell ("mäp",5,15);
		neniSpell6 = new Spell ("möp",6,20);
		neni.possibleSpells.add(neniSpell1);
		neni.possibleSpells.add(neniSpell2);
		neni.possibleSpells.add(neniSpell3);
		neni.possibleSpells.add(neniSpell4);
		neni.possibleSpells.add(neniSpell5);
		neni.possibleSpells.add(neniSpell6);
		neni.ownedSpells.add(neniSpell1);
		neni.setHealth(5);
		
		brunz = new Unimon("Brunz", 40, true);
		brunzSpell1 = new Spell ("bla",1,10);
		brunzSpell2 = new Spell("Biss", 2, 20);
		brunzSpell3 = new Spell ("mäp",3,15);
		brunzSpell4 = new Spell ("Tackle",4,20);
		brunzSpell5 = new Spell("Punch",5,15);
		brunzSpell6 = new Spell ("Hulk",6,50);
		brunz.possibleSpells.add(brunzSpell1);
		brunz.possibleSpells.add(brunzSpell2);
		brunz.possibleSpells.add(brunzSpell3);
		brunz.possibleSpells.add(brunzSpell4);
		brunz.possibleSpells.add(brunzSpell5);
		brunz.possibleSpells.add(brunzSpell6);
		brunz.ownedSpells.add(brunzSpell1);
		brunz.setHealth(20);
		
		lisa = new Unimon("Lisa", 20, true);
		lisaSpell1 =  new Spell ("möp",1,20);
		lisaSpell2  = new Spell("Biss", 2, 20);
		lisaSpell3  = new Spell("mip", 3, 20);
		lisaSpell4  = new Spell("test1", 4, 20);
		lisaSpell5  = new Spell("test2", 5, 20);
		lisaSpell6  = new Spell("test3", 6, 20);
		lisa.possibleSpells.add(lisaSpell1);
		lisa.possibleSpells.add(lisaSpell2);
		lisa.possibleSpells.add(lisaSpell3);
		lisa.possibleSpells.add(lisaSpell4);
		lisa.possibleSpells.add(lisaSpell5);
		lisa.possibleSpells.add(lisaSpell6);
		lisa.ownedSpells.add(lisaSpell1);
		lisa.setHealth(15);
		
		wild1 = new Unimon("Wildie", 20, false);
		wild1Spell1 =  new Spell ("bibi",1,20);
		wild1Spell2  = new Spell("bobo", 2, 20);
		wild1Spell3  = new Spell("bubu", 3, 20);
		wild1Spell4  = new Spell("baba", 4, 20);
		wild1Spell5  = new Spell("bebe", 5, 20);
		wild1Spell6  = new Spell("blbl", 6, 20);
		wild1.possibleSpells.add(wild1Spell1);
		wild1.possibleSpells.add(wild1Spell2);
		wild1.possibleSpells.add(wild1Spell3);
		wild1.possibleSpells.add(wild1Spell4);
		wild1.possibleSpells.add(wild1Spell5);
		wild1.possibleSpells.add(wild1Spell6);
		wild1.ownedSpells.add(wild1Spell1);
		
		wild2 = new Unimon("Wildie2", 20, false);
		wild2Spell1 =  new Spell ("didi",1,20);
		wild2Spell2  = new Spell("dodo", 2, 20);
		wild2Spell3  = new Spell("dudu", 3, 20);
		wild2Spell4  = new Spell("dada", 4, 20);
		wild2Spell5  = new Spell("dede", 5, 20);
		wild2Spell6  = new Spell("dldl", 6, 20);
		wild2.possibleSpells.add(wild2Spell1);
		wild2.possibleSpells.add(wild2Spell2);
		wild2.possibleSpells.add(wild2Spell3);
		wild2.possibleSpells.add(wild2Spell4);
		wild2.possibleSpells.add(wild2Spell5);
		wild2.possibleSpells.add(wild2Spell6);
		wild2.ownedSpells.add(wild2Spell1);
		
		wild3 = new Unimon("Wildie3", 20, false);
		wild3Spell1 =  new Spell ("hihi",1,20);
		wild3Spell2  = new Spell("hoho", 2, 20);
		wild3Spell3  = new Spell("huhu", 3, 20);
		wild3Spell4  = new Spell("haha", 4, 20);
		wild3Spell5  = new Spell("hehe", 5, 20);
		wild3Spell6  = new Spell("hlhl", 6, 20);
		wild3.possibleSpells.add(wild3Spell1);
		wild3.possibleSpells.add(wild3Spell2);
		wild3.possibleSpells.add(wild3Spell3);
		wild3.possibleSpells.add(wild3Spell4);
		wild3.possibleSpells.add(wild3Spell5);
		wild3.possibleSpells.add(wild3Spell6);
		wild3.ownedSpells.add(wild3Spell1);
		
		unimonList.add(krisk);
		unimonList.add(neni);
		unimonList.add(brunz);
		unimonList.add(lisa);
	}
	public ArrayList<Unimon> getUnimonList(){
		return unimonList;
	}

}
