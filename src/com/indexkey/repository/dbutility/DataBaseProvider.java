package com.indexkey.repository.dbutility;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class DataBaseProvider {

	private static String dataBaseName="IndexKeyDb";
	private static WeakReference<List<String>> tables = new WeakReference<List<String>>(
			new ArrayList<String>());
	private static int version=1;

	public static void AddTableDdlSql(String ddl) {

		tables.get().add(ddl);
	}

	public static void setDataBaseName(String value) {
		dataBaseName = value;
	}

	public static void setDataBaseVersion(int value) {
		version = value;
	}

	protected static String getDataBaseName() {
		return dataBaseName;
	}

	protected static List<String> getTableSql() {
		return tables.get();
	}

	protected static int getVersion() {
		return version;
	}

	protected static void recycle() {
		tables = null;
	}
}
