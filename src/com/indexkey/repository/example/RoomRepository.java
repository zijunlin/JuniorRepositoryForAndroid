package com.indexkey.repository.example;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.indexkey.repository.IRepository;
import com.indexkey.repository.dbutility.CursorFormatHelper;
import com.indexkey.repository.dbutility.ICursorDeserializer;
import com.indexkey.repository.dbutility.SqliteHelper;

/**
 * 
 * 
 * @author linzijun
 * 
 */
public class RoomRepository implements IRepository<Room>,
		ICursorDeserializer<Room> {

	private final String TABLE_NAME = "[Rooms]";
	private final String COL_ROOM_ID = "RoomID";
	private final String COL_ROOM_NAME = "RoomName";
	private final String COL_ROOM_ADDRESS = "Address";

	private Context mContext;
	private SqliteHelper db;

	public RoomRepository(Context context) {
		mContext = context;
		db = new SqliteHelper(mContext);
	}

	@Override
	public Room getEntityByKey(String key) {
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append(String.format("select * from %1s", TABLE_NAME));
		sqlStr.append(String.format(" where %1s=? ", COL_ROOM_ID));
		String[] parameters = new String[] { key };
		List<Room> list = db.query(sqlStr.toString(), parameters, this);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Room> getAllEntity() {
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append(String.format("select * from %1s", TABLE_NAME));
		List<Room> list = db.query(sqlStr.toString(), null, this);
		return list;
	}

	@Override
	public List<Room> onDeserializer(Cursor cursor) {
		if (cursor == null)
			return null;
		List<Room> roomList = new ArrayList<Room>();
		while (cursor.moveToNext()) {
			Room model = new Room();
			model.setRoomId(CursorFormatHelper.getCursorIntegerValue(
					COL_ROOM_ID, cursor));
			model.setName(CursorFormatHelper.getCursorStringValue(
					COL_ROOM_NAME, cursor));
			model.setAddress(CursorFormatHelper.getCursorStringValue(
					COL_ROOM_ADDRESS, cursor));
			roomList.add(model);
		}

		return roomList;
	}

	@Override
	public void SaveEntity(Room entity) {

		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("insert into ");
		sqlStr.append(TABLE_NAME);
		sqlStr.append(String.format("(%1s,%2s,%3s)", COL_ROOM_ID,
				COL_ROOM_NAME, COL_ROOM_ADDRESS));
		sqlStr.append(" values(?,?,?)");
		Object[] parameters = new Object[] { entity.getRoomId(),
				entity.getName(), entity.getAddress() };
		db.execSql(sqlStr.toString(), parameters);

	}

	@Override
	public void DeleteEntity(Room entity) {
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("Delete from ");
		sqlStr.append(TABLE_NAME);
		sqlStr.append(String.format(" where %1s=? ", COL_ROOM_ID));
		String[] parameters = new String[] { String.valueOf(entity.getRoomId()) };
		db.execSql(sqlStr.toString(), parameters);

	}

	@Override
	public void EditEntity(Room entity) {
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("Update ");
		sqlStr.append(TABLE_NAME);
		sqlStr.append(String.format(" set %1s=?,%2s=? ", COL_ROOM_NAME,
				COL_ROOM_ADDRESS));
		sqlStr.append(String.format(" where %1s=? ", COL_ROOM_ID));
		Object[] parameters = new Object[] { entity.getName(),
				entity.getAddress(), entity.getRoomId() };
		db.execSql(sqlStr.toString(), parameters);
	}

}
