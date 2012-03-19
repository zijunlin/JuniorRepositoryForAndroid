package com.indexkey.repository.dbutility;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DataBaseHelper extends SQLiteOpenHelper {

	private final static String DATEBASE_NAME = DataBaseProvider
			.getDataBaseName();
	private static final int DATEBASE_VERSION = DataBaseProvider.getVersion();

	public DataBaseHelper(Context c) {
		super(c, DATEBASE_NAME, null, DATEBASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 创建数据库表
		try {
			createTable(db);
		} catch (Exception ex) {
			Log.e("IndexKey.Reository", ex.getMessage());
		}

	}

	/**
	 * 创建数据库表
	 * 
	 * @param db
	 */
	private void createTable(SQLiteDatabase db) {
		// TODO 创建表
		try {
			for (int i = 0; i < DataBaseProvider.getTableSql().size(); i++) {
				db.execSQL(DataBaseProvider.getTableSql().get(i));
			}
			DataBaseProvider.recycle();
		} catch (Exception ex) {
			Log.e("IndexKey.Reository", ex.getMessage());
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
