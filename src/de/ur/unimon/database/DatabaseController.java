package de.ur.unimon.database;

import java.util.ArrayList;

import android.content.Context;
import de.ur.unimon.actionbar.Inventory;
import de.ur.unimon.startgame_logic.Player;
import de.ur.unimon.unimons.Unimon;

public class DatabaseController {
	
	private ArrayList<DatabaseListener> dbListeners = new ArrayList<DatabaseListener>();
	private AppDatabase dataBase;
	private Inventory inventory;
	private ArrayList<Unimon> ownedUnimons;
	private Player player;
	
	
	public DatabaseController(Context context){
		dataBase = new AppDatabase(context);
	}
	
	public void setOnDbControlllistener(DatabaseListener listener){
		dbListeners.add(listener);
	}
	
	public Player getPlayer(){
		dataBase.getReadableDB();
		inventory = dataBase.getInventory();
		int money = dataBase.getMoney();
		ownedUnimons = dataBase.getRawUnimons();
		for (int i=0; i<ownedUnimons.size(); i++){
			ownedUnimons = dataBase.getDetailToUnimons(ownedUnimons, i);			
		}
		player = new Player(money, ownedUnimons, inventory);
		for (DatabaseListener dbL: dbListeners) {
			dbL.onPlayerFromDbRecieved(player);
			dbL.onVisibilityforTrainerRecieved(ownedUnimons);
		}
		dataBase.close();
		return player;
	}
	
	public void clearDB(){
		dataBase.open();
		dataBase.removePlayerFromDatabase();
		dataBase.removeUnimonsFromDatabase();
		dataBase.close();
	}
	
	public void save(Player player){
		dataBase.open();
		
		dataBase.removePlayerFromDatabase();
		dataBase.removeUnimonsFromDatabase();
		
		dataBase.insertPlayerIntoDataBase(player);
		dataBase.insertUnimonsIntoDataBase(player.getUnimonList());
		
		dataBase.close();
		
		for (DatabaseListener dbL: dbListeners) {
			dbL.onSaveToDataBaseComplete();
		}
	}
	
	public boolean isDbEmpty(){
		dataBase.getReadableDB();
		return dataBase.checkIfIsEmpty();
	}
}
