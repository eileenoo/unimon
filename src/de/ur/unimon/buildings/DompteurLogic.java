package de.ur.unimon.buildings;

import de.ur.unimon.unimons.Unimon;

public class DompteurLogic {

	public Unimon learnNewSkill(Unimon unimon, int spellnumber) {
		if (unimon.skillPoint > 0) {
			unimon.skillPoint--;
			unimon.ownedSpells.add(unimon.possibleSpells.get(spellnumber));

		}
		return unimon;

	}

	public Unimon improveSkill(Unimon unimon, int spellnumber) {
		if (unimon.skillPoint > 0) {
			unimon.skillPoint--;
			unimon.ownedSpells.get(spellnumber).levelUpSpell();
		}
		return unimon;
	}
}
