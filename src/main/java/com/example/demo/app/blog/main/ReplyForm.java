package com.example.demo.app.blog.main;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReplyForm {
	
	@NotNull
	@Size(min = 1, max = 20, message = "20文字を超えています。")
	private String name;
	
	@NotNull
	@Size(min = 1, max = 40, message = "40文字を超えています。")
	private String comment;

	public ReplyForm() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
}
