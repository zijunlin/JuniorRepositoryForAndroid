package com.indexkey.repository.example;

import java.util.List;

import com.indexkey.repository.R;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RoomListActivity extends ListActivity implements OnClickListener {

	RoomAdapter adapter;
	RoomRepository repository;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViewById(R.id.btnAdd).setOnClickListener(this);
		queryList();

	}

	private void queryList() {
		repository = new RoomRepository(this.getApplicationContext());
		adapter = new RoomAdapter(this.getApplicationContext(),
				repository.getAllEntity());
		this.getListView().setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		Room room = new Room();
		room.setName("luluxiu");
		room.setAddress("sku,xkai,xing");
		repository.SaveEntity(room);

		queryList();

	}

	private class RoomAdapter extends BaseAdapter {

		private LayoutInflater layoutInflater;
		private List<Room> rooms;

		public RoomAdapter(Context context, List<Room> list) {
			layoutInflater = LayoutInflater.from(context);
			rooms = list;
		}

		@Override
		public int getCount() {

			return rooms == null ? 0 : rooms.size();
		}

		@Override
		public Object getItem(int position) {

			return rooms.get(position);
		}

		@Override
		public long getItemId(int position) {

			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = layoutInflater.inflate(
						android.R.layout.simple_list_item_1, null);
			}

			Room room = rooms.get(position);
			((TextView) convertView).setText(String.format(
					"ID:%1s \nName:%2s \nAddress:%3s", room.getRoomId(),
					room.getName(), room.getAddress()));

			return convertView;
		}

	}

}
