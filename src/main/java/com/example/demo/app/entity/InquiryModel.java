package com.example.demo.app.entity;

import java.time.LocalDateTime;
import java.util.List;

public class InquiryModel {
	
	private int id;
	private String name;
	private String email;
	private String comment;
	private LocalDateTime created;
	
	private List<InquiryReplyModel> replyList;

	public InquiryModel() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return ( name != null ? name : "" );
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return ( email != null ? email : "" );
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComment() {
		return ( comment != null ? comment : "" );
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getCreated() {
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		return ( created != null ? created : dateTime );
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	
	public List<InquiryReplyModel> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<InquiryReplyModel> replyList) {
		this.replyList = replyList;
	}
	
}
