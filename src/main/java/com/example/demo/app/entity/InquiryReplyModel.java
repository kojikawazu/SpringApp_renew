package com.example.demo.app.entity;

import java.time.LocalDateTime;

public class InquiryReplyModel {
	
	private int id;
	
	private int inquiry_id;
	
	private String name;
	
	private String email;
	
	private String comment;
	
	private LocalDateTime created;

	public InquiryReplyModel() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInquiry_id() {
		return inquiry_id;
	}

	public void setInquiry_id(int inquiry_id) {
		this.inquiry_id = inquiry_id;
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

}
