package com.indexkey.repository.dbutility;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SQLite 数据库操作的执行
 * 
 * 
 */
public class SqliteHelper {

	private DataBaseHelper dbHelper;

	public SqliteHelper(Context c) {
		dbHelper = new DataBaseHelper(c);
	}

	/**
	 * 执行带参数SQL语句
	 * 
	 * @param sqlStr
	 * @param parameters
	 */
	public void execSql(String sqlStr, Object[] parameters) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		try {
			db.execSQL(sqlStr, parameters);
		} catch (Exception ex) {
			Log.e("IndexKey", "SQL语句执行错误" + ex.getMessage());
		}
		
		db.close();
	}

	/**
	 * 执行带参事务
	 * 
	 * @param sql
	 * @throws Exception
	 */
	public void execSqlForTransaction(HashMap<String, Object[]> sql)
			throws Exception {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.beginTransaction();
		try {
			for (Map.Entry<String, Object[]> it : sql.entrySet()) {

				db.execSQL(it.getKey(), it.getValue());
			}
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
		db.close();
	}

	/**
	 * 执行不带参数sql语句
	 * 
	 * @param sqlStr
	 */
	public void execSql(String sqlStr) {
		try {
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			db.execSQL(sqlStr);
			db.close();
		} catch (Exception ex) {
			Log.e("indexKey", ex.getMessage());
		}
	}

	/**
	 * 执行不带参数的事务
	 * 
	 * @param sqlStr
	 */
	public void execSqlForTransaction(String[] sqlStr) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.beginTransaction();
		try {
			for (String item : sqlStr) {
				db.execSQL(item);
			}
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
		db.close();
	}

	/**
	 * 查询数据库
	 * 
	 * @param sqlStr
	 *            SQL查询语句
	 * @param parameters
	 *            当语句中有?号时的参数，可以传入null值表示无参。
	 * @return List<T> 返回列表
	 */
	public <T> List<T> query(String sqlStr, String[] parameters,
			ICursorDeserializer<T> cursorDeserializer) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery(sqlStr, parameters);
		List<T> list = null;
		if (cursorDeserializer != null) {
			list = cursorDeserializer.onDeserializer(cursor);
		}
		cursor.close();
		db.close();
		return list;
	}

	/**
	 * 查询数据库
	 * 
	 * @param sqlStr
	 *            SQL查询语句
	 * @param parameters
	 *            当语句中有?号时的参数，可以传入null值表示无参。
	 * @return Cursor 返回列表
	 */
	public Cursor queryForCursor(String sqlStr, String[] parameters) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery(sqlStr, parameters);
		return cursor;
	}
	
	
	

}
