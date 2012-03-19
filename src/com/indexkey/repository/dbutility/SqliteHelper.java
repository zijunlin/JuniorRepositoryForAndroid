package com.indexkey.repository.dbutility;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SQLite ���ݿ������ִ��
 * 
 * 
 */
public class SqliteHelper {

	private DataBaseHelper dbHelper;

	public SqliteHelper(Context c) {
		dbHelper = new DataBaseHelper(c);
	}

	/**
	 * ִ�д�����SQL���
	 * 
	 * @param sqlStr
	 * @param parameters
	 */
	public void execSql(String sqlStr, Object[] parameters) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		try {
			db.execSQL(sqlStr, parameters);
		} catch (Exception ex) {
			Log.e("IndexKey", "SQL���ִ�д���" + ex.getMessage());
		}
		
		db.close();
	}

	/**
	 * ִ�д�������
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
	 * ִ�в�������sql���
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
	 * ִ�в�������������
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
	 * ��ѯ���ݿ�
	 * 
	 * @param sqlStr
	 *            SQL��ѯ���
	 * @param parameters
	 *            ���������?��ʱ�Ĳ��������Դ���nullֵ��ʾ�޲Ρ�
	 * @return List<T> �����б�
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
	 * ��ѯ���ݿ�
	 * 
	 * @param sqlStr
	 *            SQL��ѯ���
	 * @param parameters
	 *            ���������?��ʱ�Ĳ��������Դ���nullֵ��ʾ�޲Ρ�
	 * @return Cursor �����б�
	 */
	public Cursor queryForCursor(String sqlStr, String[] parameters) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery(sqlStr, parameters);
		return cursor;
	}
	
	
	

}
