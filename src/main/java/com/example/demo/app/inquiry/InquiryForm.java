package com.example.demo.app.inquiry;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InquiryForm {
	
	@NotNull
	@Size(min = 1, max = 20, message = "20文字を超えています。")
	private String name;
	
	@NotNull
	@Size(min = 1, max = 50, message = "50文字を超えています。")
	@Email(message = "正しいメールアドレスを入力してください。")
	private String email;
	
	@NotNull
	private String comment;
	
	public InquiryForm() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
