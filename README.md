# A junior repository for Android
  This project is package for SQLite helper .It's junior not an ORM,you still need to write SQL statements.But is easy use and understanding.

## Features
* Using SQL statemnet to do some Database job,so it has a High efficiency

* Help you control the Db,Cursor Open and Close Operating

* Provider a simple Cursor Foramtter Helper .

* You only to attention on the SQL statment

## How To Do

1.Create a MyApplication who extends Application.and set then Db name ¡¢version and Table DDL SQL statemnet.e.g.
```
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
```
Don't forget change AndroidManifest.xml ,Add a android:name in the application note

2.Create a Repository who implements IRepository<>

3.Use the CursorFormatHelper to Formatter you Model Class

## Attention

* It¡¯ Not safe to Multi-threaded

* If something is wrong in you programming about the Datebase ,you need to delete the Datebase and debug the project again.

* You must be sure the SQL is no wrong.beacuse this project unable to check you SQL staemnet .
