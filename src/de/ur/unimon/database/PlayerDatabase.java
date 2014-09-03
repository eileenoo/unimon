package de.ur.unimon.database;

import de.ur.unimon.config.UnimonAppConfig;
import de.ur.unimon.startgame_logic.Player;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class PlayerDatabase {
	/*private PlayerDatabaseHelper helper;
	private SQLiteDatabase playerDb;

	public PlayerDatabase(Context context) {
		helper = new PlayerDatabaseHelper(context,
				UnimonAppConfig.Data.DATABASE_KEY, null,
				UnimonAppConfig.Data.DATABASE_VERSION);
	}

	private class PlayerDatabaseHelper extends SQLiteOpenHelper {

		private static final String DATABASE_CREATE = "create table "
				+ UnimonAppConfig.Data.TABLE_KEY + " ("
				+ UnimonAppConfig.Data.MONEY_KEY + " text not null); ";

		public PlayerDatabaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Drop older table if existed
			playerDb.execSQL("DROP TABLE IF EXISTS "
					+ UnimonAppConfig.Data.TABLE_KEY);

			// Create tables again
			onCreate(playerDb);
		}

		@Override
		public void onCreate(SQLiteDatabase locationDb) {
			playerDb.execSQL(DATABASE_CREATE);
		}
	}

	public void close() {
		playerDb.close();
		helper.close();
	}

	public void open() throws SQLException {
		playerDb = helper.getWritableDatabase();
	}

	public long insertPlayerToDb(Player player) {
		ContentValues newPlayerValues = new ContentValues();
		newPlayerValues = getNewPlayerValues(player);

		return playerDb.insert(UnimonAppConfig.Data.TABLE_KEY, null, newPlayerValues);
	}

	private ContentValues getNewPlayerValues(Player player) {
		ContentValues newPlayerValues = new ContentValues();
		newPlayerValues.put(UnimonAppConfig.Data.MONEY_KEY, player.getMoney());		
		return newPlayerValues;
	}

	public void updateDb(Player player) {
		ContentValues newPlayerValues = new ContentValues();
		newPlayerValues = getNewPlayerValues(player);

		String constraints = UnimonAppConfig.Data.ID_KEY + "=?";
		String[] arguments = new String[] { newPlayerValues
				.getAsString(UnimonAppConfig.Data.ID_KEY) };

		int updatedRows = playerDb.update(UnimonAppConfig.Data.TABLE_KEY,
				newPlayerValues, constraints, arguments);
		if (updatedRows == 0) {
			playerDb.insert(UnimonAppConfig.Data.TABLE_KEY, null, newPlayerValues);
		}
	}

	public Player getPlayer() {
		Player player = new Player();
		Cursor cursor = playerDb.query(UnimonAppConfig.Data.TABLE_KEY,
				new String[] {UnimonAppConfig.Data.MONEY_KEY}, null, null, null, null,
				null);
		if (cursor.moveToFirst()) {
			do {
				String money = cursor.getString(1);
				player = new Player();

			} while (cursor.moveToNext());
		}
		return player;
	}*/

}
