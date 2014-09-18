package de.ur.unimon.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import de.ur.unimon.actionbar.Inventory;
import de.ur.unimon.config.UnimonAppConfig;
import de.ur.unimon.player.Player;
import de.ur.unimon.trainer.Trainer;
import de.ur.unimon.trainer.TrainerList;
import de.ur.unimon.unimons.Unimon;
import de.ur.unimon.unimons.UnimonList;

public class AppDatabase {
	private AppDatabaseHelper helper;
	private SQLiteDatabase db;

	public AppDatabase(Context context) {
		helper = new AppDatabaseHelper(context, UnimonAppConfig.Data.DB_NAME, null, UnimonAppConfig.Data.DATABASE_VERSION);
	}
	
	public void open() {
		db = helper.getWritableDatabase();
	}
	
	public void getReadableDB() {
		db = helper.getReadableDatabase();
	}
	
	public void close() {
		db.close();
		helper.close();
	}
	
	public Inventory getInventory(){
		String selectQuery = "SELECT " 
				+ UnimonAppConfig.Data.KEY_PLAYER_HEALPOTS + ", "
				+ UnimonAppConfig.Data.KEY_PLAYER_UNIBALLS + ", " 
				+ UnimonAppConfig.Data.KEY_PLAYER_REVIVES + ", " 
				+ UnimonAppConfig.Data.KEY_PLAYER_PROTECTORS 
				+ " FROM " + UnimonAppConfig.Data.DATABASE_TABLE_PLAYER;
		
		Cursor c = db.rawQuery(selectQuery, null);
		if (c != null)
            c.moveToFirst();
		
		return new Inventory(c.getInt(c.getColumnIndex(UnimonAppConfig.Data.KEY_PLAYER_HEALPOTS)),
				c.getInt(c.getColumnIndex(UnimonAppConfig.Data.KEY_PLAYER_UNIBALLS)),
				c.getInt(c.getColumnIndex(UnimonAppConfig.Data.KEY_PLAYER_REVIVES)),
				c.getInt(c.getColumnIndex(UnimonAppConfig.Data.KEY_PLAYER_PROTECTORS)));
	}
	
	public int getMoney(){
		String selectQuery = "SELECT "
				+ UnimonAppConfig.Data.KEY_PLAYER_MONEY + " FROM " + UnimonAppConfig.Data.DATABASE_TABLE_PLAYER;
		
		Cursor c = db.rawQuery(selectQuery, null);
		if (c != null)
            c.moveToFirst();
		
		return c.getInt(c.getColumnIndex(UnimonAppConfig.Data.KEY_PLAYER_MONEY));
	}
	
	public ArrayList<Unimon> getRawUnimons(){
		ArrayList<Unimon> ownedUnimons = new ArrayList<Unimon>();
		ArrayList<Unimon> allUnimons = new UnimonList().getAllUnimonsList();
		
		String selectQuery = "SELECT " + UnimonAppConfig.Data.KEY_UNIMONS_NAME + " FROM " + UnimonAppConfig.Data.DATABASE_TABLE_UNIMONS;
		
		Cursor c = db.rawQuery(selectQuery, null);
		if (c.moveToFirst()) {
            do {
            	for (int i = 0; i < allUnimons.size(); i++) {
        			if (allUnimons.get(i).getName().equals(c.getString(c.getColumnIndex(UnimonAppConfig.Data.KEY_UNIMONS_NAME)))) {
        				ownedUnimons.add(allUnimons.get(i));
        			}
        		}
            } while (c.moveToNext());
        }
		return ownedUnimons;
	}
	
	public ArrayList<Unimon> getDetailToUnimons(ArrayList<Unimon> unimonList, int index){
		int id = index +1;
		String selectQuery = "SELECT * FROM " + UnimonAppConfig.Data.DATABASE_TABLE_UNIMONS 
				+ " WHERE " + UnimonAppConfig.Data.KEY_UNIMONS_ID + " = " + id;
		
		Cursor c = db.rawQuery(selectQuery, null);
		if (c != null)
            c.moveToFirst();
		unimonList.get(index).setLevel(c.getInt(c.getColumnIndex(UnimonAppConfig.Data.KEY_UNIMONS_LEVEL)));
		unimonList.get(index).setXp(c.getInt(c.getColumnIndex(UnimonAppConfig.Data.KEY_UNIMONS_XP)));
		unimonList.get(index).setHealth(c.getInt(c.getColumnIndex(UnimonAppConfig.Data.KEY_UNIMONS_HEALTH)));
		if (c.getInt(c.getColumnIndex(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_1_LEARNED))== 1) {
			unimonList.get(index).learnSpell(unimonList.get(index).getSpellBySpellNumber(0));
		}
		if (c.getInt(c.getColumnIndex(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_2_LEARNED))== 1) {
			unimonList.get(index).learnSpell(unimonList.get(index).getSpellBySpellNumber(1));
		}
		if (c.getInt(c.getColumnIndex(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_3_LEARNED))== 1) {
			unimonList.get(index).learnSpell(unimonList.get(index).getSpellBySpellNumber(2));
		}
		if (c.getInt(c.getColumnIndex(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_4_LEARNED))== 1) {
			unimonList.get(index).learnSpell(unimonList.get(index).getSpellBySpellNumber(3));
		}
		if (c.getInt(c.getColumnIndex(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_5_LEARNED))== 1) {
			unimonList.get(index).learnSpell(unimonList.get(index).getSpellBySpellNumber(4));
		}
		if (c.getInt(c.getColumnIndex(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_6_LEARNED))== 1) {
			unimonList.get(index).learnSpell(unimonList.get(index).getSpellBySpellNumber(5));
		}
		unimonList.get(index).getSpellBySpellNumber(0).setSpellLevel(c.getInt(c.getColumnIndex(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_1_Level)));
		unimonList.get(index).getSpellBySpellNumber(1).setSpellLevel(c.getInt(c.getColumnIndex(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_2_Level)));
		unimonList.get(index).getSpellBySpellNumber(2).setSpellLevel(c.getInt(c.getColumnIndex(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_3_Level)));
		unimonList.get(index).getSpellBySpellNumber(3).setSpellLevel(c.getInt(c.getColumnIndex(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_4_Level)));
		unimonList.get(index).getSpellBySpellNumber(4).setSpellLevel(c.getInt(c.getColumnIndex(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_5_Level)));
		unimonList.get(index).getSpellBySpellNumber(5).setSpellLevel(c.getInt(c.getColumnIndex(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_6_Level)));
		
		return unimonList;
		
	}
	
	public TrainerList getTrainerVisibility(TrainerList trainerList, int index) {
		int id = index +1;
		String selectQuery = "SELECT * FROM " + UnimonAppConfig.Data.DATABASE_TABLE_TRAINER_VISIBILITY
				+ " WHERE " + UnimonAppConfig.Data.KEY_TRAINER_ID + " = " + id;
		
		Cursor c = db.rawQuery(selectQuery, null);
		if (c != null)
            c.moveToFirst();
		
		if (c.getInt(c.getColumnIndex(UnimonAppConfig.Data.KEY_TRAINER_VISIBLE)) == 1) {
			trainerList.getTrainerList().get(index).setVisible();
		}
	
		return trainerList;
	}
	
	public long insertPlayerIntoDataBase(Player currentPlayer) {
		ContentValues values = new ContentValues();
		values.put(UnimonAppConfig.Data.KEY_PLAYER_MONEY, currentPlayer.getMoney());
		values.put(UnimonAppConfig.Data.KEY_PLAYER_HEALPOTS, currentPlayer.getInventory().getHealpotCount());
		values.put(UnimonAppConfig.Data.KEY_PLAYER_REVIVES, currentPlayer.getInventory().getReviveCount());
		values.put(UnimonAppConfig.Data.KEY_PLAYER_UNIBALLS, currentPlayer.getInventory().getUniballCount());
		values.put(UnimonAppConfig.Data.KEY_PLAYER_PROTECTORS, currentPlayer.getInventory().getProtectorCount());
		long tag_id = db.insert(UnimonAppConfig.Data.DATABASE_TABLE_PLAYER, null, values);	
		
		return tag_id;
	}	
	
	public long insertUnimonsIntoDataBase(ArrayList<Unimon> unimons){
		long tag_id = 0;
		ContentValues values = new ContentValues();
		for (int i=0; i<unimons.size(); i++) {
			values.put(UnimonAppConfig.Data.KEY_UNIMONS_ID, i+1);
			values.put(UnimonAppConfig.Data.KEY_UNIMONS_NAME, unimons.get(i).getName());
			values.put(UnimonAppConfig.Data.KEY_UNIMONS_LEVEL, unimons.get(i).getLevel());
			values.put(UnimonAppConfig.Data.KEY_UNIMONS_XP, unimons.get(i).getXp());
			values.put(UnimonAppConfig.Data.KEY_UNIMONS_HEALTH, unimons.get(i).getHealth());
			values.put(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_1_LEARNED, unimons.get(i).getSpellBySpellNumber(0).isLearned()? 1:0);
			values.put(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_2_LEARNED, unimons.get(i).getSpellBySpellNumber(1).isLearned()? 1:0);
			values.put(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_3_LEARNED, unimons.get(i).getSpellBySpellNumber(2).isLearned()? 1:0);
			values.put(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_4_LEARNED, unimons.get(i).getSpellBySpellNumber(3).isLearned()? 1:0);
			values.put(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_5_LEARNED, unimons.get(i).getSpellBySpellNumber(4).isLearned()? 1:0);
			values.put(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_6_LEARNED, unimons.get(i).getSpellBySpellNumber(5).isLearned()? 1:0);
			values.put(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_1_Level, unimons.get(i).getSpellBySpellNumber(0).getSpellLevel());
			values.put(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_2_Level, unimons.get(i).getSpellBySpellNumber(1).getSpellLevel());
			values.put(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_3_Level, unimons.get(i).getSpellBySpellNumber(2).getSpellLevel());
			values.put(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_4_Level, unimons.get(i).getSpellBySpellNumber(3).getSpellLevel());
			values.put(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_5_Level, unimons.get(i).getSpellBySpellNumber(4).getSpellLevel());
			values.put(UnimonAppConfig.Data.KEY_UNIMONS_SPELL_6_Level, unimons.get(i).getSpellBySpellNumber(5).getSpellLevel());
			
			tag_id = db.insert(UnimonAppConfig.Data.DATABASE_TABLE_UNIMONS, null, values);	
		} 
		return tag_id;
	}
	
	public long insertTrainerVisibilityIntoDataBase(ArrayList<Trainer> trainerList) {
		long tag_id=0;
		ContentValues values = new ContentValues();
		for (int i=0; i<trainerList.size(); i++) {
			values.put(UnimonAppConfig.Data.KEY_TRAINER_ID, i+1);
			values.put(UnimonAppConfig.Data.KEY_TRAINER_VISIBLE, trainerList.get(i).isSeen()? 1:0);
			tag_id = db.insert(UnimonAppConfig.Data.DATABASE_TABLE_TRAINER_VISIBILITY, null, values);
		}
		
		return tag_id;
	}
	
	
	public void removePlayerFromDatabase() {
		db.delete(UnimonAppConfig.Data.DATABASE_TABLE_PLAYER, null, null);
	}
	
	public void removeUnimonsFromDatabase() {
		db.delete(UnimonAppConfig.Data.DATABASE_TABLE_UNIMONS, null, null);
	}
	
	public void removeTrainerVisibilityFromDataBase() {
		db.delete(UnimonAppConfig.Data.DATABASE_TABLE_TRAINER_VISIBILITY, null, null);
	}
	
	public boolean checkIfIsEmpty(){
		String selectQuery = "SELECT * FROM " + UnimonAppConfig.Data.DATABASE_TABLE_PLAYER ;
		Cursor c = db.rawQuery(selectQuery, null);
		if (c != null){
		    c.moveToFirst();
		    if (c.getCount() == 0) {
		      return true;
		    }	
		}
		return false;
	}

	private class AppDatabaseHelper extends SQLiteOpenHelper {
	
		private static final String CREATE_TABLE_PLAYER = "CREATE TABLE "+ UnimonAppConfig.Data.DATABASE_TABLE_PLAYER + " ("
				+ UnimonAppConfig.Data.KEY_PLAYER_MONEY  + " integer, "
				+ UnimonAppConfig.Data.KEY_PLAYER_HEALPOTS + " integer, "
				+ UnimonAppConfig.Data.KEY_PLAYER_UNIBALLS + " integer, "
				+ UnimonAppConfig.Data.KEY_PLAYER_REVIVES + " integer, "
				+ UnimonAppConfig.Data.KEY_PLAYER_PROTECTORS + " integer);";
		
		public static final String CREATE_TABLE_UNIMONS = "CREATE TABLE "+ UnimonAppConfig.Data.DATABASE_TABLE_UNIMONS  + " ("
				+ UnimonAppConfig.Data.KEY_UNIMONS_ID + " integer primary key, "
				+ UnimonAppConfig.Data.KEY_UNIMONS_NAME + " text not null, "
				+ UnimonAppConfig.Data.KEY_UNIMONS_LEVEL + " integer, "
				+ UnimonAppConfig.Data.KEY_UNIMONS_XP + " integer, "
				+ UnimonAppConfig.Data.KEY_UNIMONS_HEALTH + " integer, "
				+ UnimonAppConfig.Data.KEY_UNIMONS_SPELL_1_LEARNED + " integer, "
				+ UnimonAppConfig.Data.KEY_UNIMONS_SPELL_2_LEARNED + " integer, "
				+ UnimonAppConfig.Data.KEY_UNIMONS_SPELL_3_LEARNED + " integer, "
				+ UnimonAppConfig.Data.KEY_UNIMONS_SPELL_4_LEARNED + " integer, "
				+ UnimonAppConfig.Data.KEY_UNIMONS_SPELL_5_LEARNED + " integer, "
				+ UnimonAppConfig.Data.KEY_UNIMONS_SPELL_6_LEARNED + " integer, "
				+ UnimonAppConfig.Data.KEY_UNIMONS_SPELL_1_Level + " integer, "
				+ UnimonAppConfig.Data.KEY_UNIMONS_SPELL_2_Level + " integer, "
				+ UnimonAppConfig.Data.KEY_UNIMONS_SPELL_3_Level + " integer, "
				+ UnimonAppConfig.Data.KEY_UNIMONS_SPELL_4_Level + " integer, "
				+ UnimonAppConfig.Data.KEY_UNIMONS_SPELL_5_Level + " integer, "
				+ UnimonAppConfig.Data.KEY_UNIMONS_SPELL_6_Level + " integer);";
		
		public static final String CREATE_TABLE_TRAINER_VISIBILITY ="CREATE TABLE " + UnimonAppConfig.Data.DATABASE_TABLE_TRAINER_VISIBILITY + " ("
				+ UnimonAppConfig.Data.KEY_TRAINER_ID + " integer primary key, "
				+ UnimonAppConfig.Data.KEY_TRAINER_VISIBLE + " integer);";
				

		
		public AppDatabaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + UnimonAppConfig.Data.DATABASE_TABLE_PLAYER);
			db.execSQL("DROP TABLE IF EXISTS " + UnimonAppConfig.Data.DATABASE_TABLE_UNIMONS);
			db.execSQL("DROP TABLE IF EXISTS " + UnimonAppConfig.Data.DATABASE_TABLE_TRAINER_VISIBILITY);
			onCreate(db);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TABLE_PLAYER);
			db.execSQL(CREATE_TABLE_UNIMONS);
			db.execSQL(CREATE_TABLE_TRAINER_VISIBILITY);
		}

	}

 }
