package com.indexkey.repository.example;

public class Room {

	private int id;
	private String name;
	private String address;

	public void setRoomId(int value) {
		id = value;
	}

	public void setName(String value) {
		name = value;
	}

	public void setAddress(String value) {
		address = value;
	}

	public int getRoomId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}
}
