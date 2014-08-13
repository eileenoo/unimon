package de.ur.unimon.battle;


import de.ur.unimon.startgame_logic.PlayerListener;
import de.ur.unimon.unimons.Unimon;

public class BattleController {
	
	private Unimon[] battleUnimonsList = new Unimon[3];
	private Unimon battleUnimon;
	private Unimon enemyUnimon;
	private BattleLogic battleLogic;
	private PlayerListener playerListener = (PlayerListener) this;
	
	
	public BattleController (Unimon enemyUnimon, Unimon battleUnimon, Unimon[] unimonList) {
		initEnemyUnimon(enemyUnimon);
		initBattleUnimon(battleUnimon);
		initBattleUnimonList(unimonList);
		initBattleLogic();
	}
	
	private void initEnemyUnimon(Unimon enemyUnimon) {
		this.enemyUnimon = enemyUnimon; 
	}

	private void initBattleUnimon(Unimon battleUnimon) {
		this.battleUnimon = battleUnimon; 
	}

	private void initBattleUnimonList(Unimon[] unimonList) {
		this.battleUnimonsList = unimonList;
	}

	private void initBattleLogic() {
		battleLogic = new BattleLogic(battleUnimon, enemyUnimon);
	}

	public boolean escape() {
		if(battleLogic.ableToEscape()){
			return true;
		} else {
			return true;
		}
	}

	public boolean catchUnimon() {
		if (battleLogic.ableToCatchUnimon()) {
		return true;
		} else {
			return false;
		}
	}
	
	public void unimonCatchSuccess() {
		playerListener.onAddUnimonToOwnUnimonList(enemyUnimon);
	}
	
	public void useHealpot() {
			battleLogic.useHealpot();
			playerListener.onHealPotCountChange();
	}
	
	public void changeCurrentUnimon(int battleUnimonsListIndex) {
		Unimon newUnimon = battleUnimonsList[battleUnimonsListIndex]; 
		battleLogic.changeUnimon(newUnimon);
	}

}
