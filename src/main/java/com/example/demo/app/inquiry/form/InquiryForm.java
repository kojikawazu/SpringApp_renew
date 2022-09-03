package com.example.demo.app.inquiry.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 問い合わせフォーム
 * @author nanai
 *
 */
public class InquiryForm {
	
	/** 名前の最小文字数 */
	private static final int MIN_NAME_LENGTH     = 1;
	
	/** 名前の最大文字数 */
	private static final int MAX_NAME_LENGTH     = 20;
	
	/** 名前のエラーメッセージ */
	private static final String MAX_NAME_MESSAGE = MAX_NAME_LENGTH + "文字を超えています。";
	
	/** Eメールの最小文字数 */
	private static final int MIN_EMAIL_LENGTH     = 1;
	
	/** Eメールの最大文字数 */
	private static final int MAX_EMAIL_LENGTH     = 50;
	
	/** Eメールのエラーメッセージ */
	private static final String MAX_EMAIL_MESSAGE = MAX_EMAIL_LENGTH + "文字を超えています。";
	
	/** Eメールアドレス形式エラーメッセージ */
	private static final String ERROR_EMAIL_MESSAGE = "正しいメールアドレスを入力してください。";
	
	/** コメントの最小文字数 */
	private static final int MIN_COMMENT_LENGTH     = 1;
	
	/** コメントの最大文字数 */
	private static final int MAX_COMMENT_LENGTH     = 100;
	
	/** コメントのエラーメッセージ */
	private static final String MAX_COMMENT_MESSAGE = MAX_COMMENT_LENGTH + "文字を超えています。";
	
	/** 問い合わせ名 */
	@NotNull
	@Size(min       = MIN_NAME_LENGTH, 
			max     = MAX_NAME_LENGTH, 
			message = MAX_NAME_MESSAGE)
	private String name;
	
	/** 問い合わせメールアドレス */
	@NotNull
	@Size(min       = MIN_EMAIL_LENGTH, 
			max     = MAX_EMAIL_LENGTH, 
			message = MAX_EMAIL_MESSAGE)
	@Email(message  = ERROR_EMAIL_MESSAGE)
	private String email;
	
	/** コメント */
	@NotNull
	@Size(min       = MIN_COMMENT_LENGTH, 
			max     = MAX_COMMENT_LENGTH, 
			message = MAX_COMMENT_MESSAGE)
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
