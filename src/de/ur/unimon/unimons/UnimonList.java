package de.ur.unimon.unimons;

import java.util.ArrayList;

public class UnimonList {

	private static Unimon krisk;
	private static Unimon neni;
	private static Unimon brunz;
	private static Unimon lisa;
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
	

	public ArrayList<Unimon> unimonList;

	public UnimonList() {
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

		neni = new Unimon("Neni", 10, false);
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
		
		brunz = new Unimon("Brunz", 10, true);
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
		
		lisa = new Unimon("Lisa", 20, false);
		lisaSpell1 =  new Spell ("möp",1,20);
		lisaSpell2  = new Spell("Biss", 2, 20);
		lisaSpell3  = new Spell("mip", 3, 20);
		lisaSpell4  = new Spell("FU", 4, 20);
		lisaSpell5  = new Spell("YOU", 5, 20);
		lisaSpell6  = new Spell("HARD", 6, 20);
		lisa.possibleSpells.add(lisaSpell1);
		lisa.possibleSpells.add(lisaSpell2);
		lisa.possibleSpells.add(lisaSpell3);
		lisa.possibleSpells.add(lisaSpell4);
		lisa.possibleSpells.add(lisaSpell5);
		lisa.possibleSpells.add(lisaSpell6);
		lisa.ownedSpells.add(lisaSpell1);
		
		unimonList.add(krisk);
		unimonList.add(neni);
		unimonList.add(brunz);
		unimonList.add(lisa);
	}
	public ArrayList<Unimon> getUnimonList(){
		return unimonList;
	}

}
