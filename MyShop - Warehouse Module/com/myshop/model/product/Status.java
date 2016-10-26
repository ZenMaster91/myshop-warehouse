package com.myshop.model.product;

public class Status {
	
	private int statusID;
	private String name;
	
	public Status(int statusID, String name) {
		setStatusID(statusID);
		setName(name);
	}

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
