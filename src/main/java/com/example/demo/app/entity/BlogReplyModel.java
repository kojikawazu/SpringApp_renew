package com.example.demo.app.entity;

import java.time.LocalDateTime;

public class BlogReplyModel {
	
	private int id;
	private int commentid;
	private String name;
	private String comment;
	private int thanksCnt;
	private LocalDateTime created;

	public BlogReplyModel() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCommentid() {
		return commentid;
	}

	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	
	public String getName() {
		return ( name != null ? name : "" );
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return ( comment != null ? comment : "" );
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getThanksCnt() {
		return thanksCnt;
	}

	public void setThanksCnt(int thanksCnt) {
		this.thanksCnt = thanksCnt;
	}

	public LocalDateTime getCreated() {
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		return ( created != null ? created : dateTime );
		
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

}
