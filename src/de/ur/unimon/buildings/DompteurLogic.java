package de.ur.unimon.buildings;

import de.ur.unimon.unimons.Unimon;

public class DompteurLogic {

	public void learnNewSkill(Unimon unimon, int spellnumber) {
		if (unimon.getSkillPoints() > 0) {
			unimon.learnSpell(unimon.possibleSpells.get(spellnumber));
		}

	}

	public void improveSkill(Unimon unimon, int spellnumber) {
		if (unimon.getSkillPoints() > 0) {
			unimon.ownedSpells.get(spellnumber).levelUpSpell();
		}
	}
}
