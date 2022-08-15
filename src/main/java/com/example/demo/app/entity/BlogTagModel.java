package com.example.demo.app.entity;

public class BlogTagModel {
	
	private int id;
	
	private String tag;

	public BlogTagModel() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTag() {
		return ( tag != null ? tag : "" );
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	

}
