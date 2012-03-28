package com.indexkey.repository.dbutility;

import java.util.List;

import android.database.Cursor;

public interface ICursorDeserializer<T> {

	List<T> onDeserializer(Cursor cursor);
}
