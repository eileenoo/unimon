package de.ur.unimon.buildings;

import de.ur.unimon.unimons.Unimon;

public class DompteurLogic {

	public void learnNewSkill(Unimon unimon, int spellnumber) {
		if (unimon.skillPoint > 0) {
			unimon.skillPoint--;
			unimon.ownedSpells.add(unimon.possibleSpells.get(spellnumber));
		}

	}

	public void improveSkill(Unimon unimon, int spellnumber) {
		if (unimon.skillPoint > 0) {
			unimon.skillPoint--;
			unimon.ownedSpells.get(spellnumber).levelUpSpell();
		}
	}
}
