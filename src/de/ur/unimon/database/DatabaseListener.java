package de.ur.unimon.database;

import java.util.ArrayList;

import de.ur.unimon.startgame_logic.Player;

public interface DatabaseListener {
	public void onSaveToDataBaseComplete();
	public void onPlayerFromDbRecieved(Player player);
	public void onVisibilityforTrainerRecieved(ArrayList visibilityList);

}
