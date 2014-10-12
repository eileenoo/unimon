package de.ur.unimon.database;

import java.util.ArrayList;

import android.content.Context;
import de.ur.unimon.actionbar.Inventory;
import de.ur.unimon.player.Player;
import de.ur.unimon.player.PlayerController;
import de.ur.unimon.trainer.Trainer;
import de.ur.unimon.trainer.TrainerList;
import de.ur.unimon.trainer.TrainerListController;
import de.ur.unimon.unimons.Unimon;

public class DatabaseController {

	private AppDatabase dataBase;
	private Inventory inventory;
	private ArrayList<Unimon> ownedUnimons;
	private Player player;
	private TrainerListController trainerListController;
	private PlayerController playerController;
	private ArrayList<Trainer> trainerList;
	
	
	public DatabaseController(Context context){
		dataBase = new AppDatabase(context);
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
		
		dataBase.close();
		return player;
	}
	
	public TrainerList getTrainerList() {
		dataBase.getReadableDB();
		TrainerList trainerList = new TrainerList();
		for (int i=0; i<trainerList.getTrainerList().size(); i++){
			trainerList = dataBase.getTrainerVisibility(trainerList, i);
		}
				
		dataBase.close();
		return trainerList;
	}
	
	public void clearDB(){
		dataBase.open();
		dataBase.removePlayerFromDatabase();
		dataBase.removeUnimonsFromDatabase();
		dataBase.removeTrainerVisibilityFromDataBase();
		dataBase.close();
	}
	
	public void save(){
		player = playerController.getInstance();
		trainerList = trainerListController.getInstance().getTrainerList();
		
		dataBase.open();
		
		dataBase.removePlayerFromDatabase();
		dataBase.removeUnimonsFromDatabase();
		dataBase.removeTrainerVisibilityFromDataBase();
		
		dataBase.insertPlayerIntoDataBase(player);
		dataBase.insertUnimonsIntoDataBase(player.getUnimonList());
		dataBase.insertTrainerVisibilityIntoDataBase(trainerList);
		
		dataBase.close();
	}
	
	public boolean isDbEmpty(){
		dataBase.getReadableDB();
		return dataBase.checkIfIsEmpty();
	}
}
