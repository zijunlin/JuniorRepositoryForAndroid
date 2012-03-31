package com.indexkey.repository.dbutility;

import android.database.Cursor;

/**
 * 格式化 Cursor，并输出
 * @author linzijun
 *
 */
public class CursorFormatHelper {

	/**
	 * 字符串格式
	 * @param ColumnName
	 * @param cursor
	 * @return
	 */
	public static String getCursorStringValue(String ColumnName, Cursor cursor) {
		int col = cursor.getColumnIndex(ColumnName);
		
		if (col != -1 && !cursor.isNull(col)) {
			return cursor.getString(col);
		} else {
			return null;
		}
	}
	
	/**
	 * 整型格式
	 * @param ColumnName
	 * @param cursor
	 * @return
	 */
	public static int getCursorIntegerValue(String ColumnName,Cursor cursor)
	{
		int col = cursor.getColumnIndex(ColumnName);
		if (col != -1 && !cursor.isNull(col)) {
			return cursor.getInt(col);
		} else {
			return -1;
		}
	}

	/**
	 * 布尔类型
	 * @param ColumnName
	 * @param cursor
	 * @return
	 */
	public static boolean getCursorBooleanValue(String ColumnName,Cursor cursor)
	{
		int col = cursor.getColumnIndex(ColumnName);
		if (col != -1 && !cursor.isNull(col)) {
			return cursor.getInt(col)!=0;
		}
		else
		{
			return false;
		}
	}
}
