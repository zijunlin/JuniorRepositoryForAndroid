package com.indexkey.repository.example;

import com.indexkey.repository.dbutility.DataBaseProvider;

import android.app.Application;

public class MyApplication extends Application {

	@Override
	public void onCreate() {

		super.onCreate();
		DataBaseProvider.setDataBaseName("MyRepositoryDb");
		DataBaseProvider.setDataBaseVersion(1);
		DataBaseProvider.AddTableDdlSql("CREATE TABLE [Rooms] "
				+ "( [RoomID] Integer  PRIMARY KEY autoincrement NOT NULL, "
				+ "[RoomName] VARCHAR(50)," + "[Address] VARCHAR(50)"
				+ ");");
		// DataBaseProvider.AddTableSql(User)
	}

}
