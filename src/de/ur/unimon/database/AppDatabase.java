package de.ur.unimon.database;

import java.util.ArrayList;

import de.ur.unimon.startgame_logic.Player;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class AppDatabase {
	private AppDatabaseHelper helper;
	private SQLiteDatabase db;
	public static final String DB_NAME = "unimon-app";
	public static final int DB_VERSION = 1;
	public static final String DATABASE_TABLE = "unimons";
	public static final String KEY_ID = "_id";
	public static final String KEY_MONEY = "_money";
	public static final String KEY_INVENTORY = "_inventory";

	public AppDatabase(Context context) {
		helper = new AppDatabaseHelper(context, DB_NAME, null, DB_VERSION);
	}
	
	public void open() {
		db = helper.getWritableDatabase();
	}
	
	public void close() {
		db.close();
		helper.close();
	}
	
	public void insertPlayerIntoDataBase(Player currentPlayer) {
		ContentValues values = new ContentValues();
		values.put(KEY_MONEY, currentPlayer.getMoney());
		db.insert(DATABASE_TABLE, null, values);		
	}	
	
	public void removeAllItemsFromDatabase() {
		db.delete(DATABASE_TABLE, null, null);
	}

	private class AppDatabaseHelper extends SQLiteOpenHelper {
	
		private static final String DATABASE_CREATE = "create table "
				+ DATABASE_TABLE + " (" + KEY_ID
				+ " integer primary key autoincrement, "
				+ KEY_MONEY  + " text not null, )";

		
		public AppDatabaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, DB_NAME, factory, DB_VERSION);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

	}

 }
