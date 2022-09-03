package com.example.demo.app.inquiry.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 問い合わせ返信フォーム
 * @author nanai
 *
 */
public class InquiryReplyForm {
	
	/** 返信名の最小数 */
	private static final int MIN_NAME_LENGTH = 1;
	
	/** 返信名の最大数 */
	private static final int MAX_NAME_LENGTH = 20;
	
	/** 返信名のエラーメッセージ */
	private static final String MAX_NAME_MESSAGE = MAX_NAME_LENGTH + "文字を超えています。";
	
	/** 返信メールアドレスの最小数 */
	private static final int MIN_EMAIL_LENGTH = 1;
	
	/** 返信メールアドレスの最大数 */
	private static final int MAX_EMAIL_LENGTH = 50;
	
	/** 返信メールアドレスのエラーメッセージ */
	private static final String MAX_EMAIL_MESSAGE = MAX_EMAIL_LENGTH + "文字を超えています。";
	
	/** Eメールアドレス形式エラーメッセージ */
	private static final String ERROR_EMAIL_MESSAGE = "正しいメールアドレスを入力してください。";
	
	/** 返信メールアドレスの最小数 */
	private static final int MIN_COMMENT_LENGTH = 1;
	
	/** 返信メールアドレスの最大数 */
	private static final int MAX_COMMENT_LENGTH = 100;
	
	/** 返信メールアドレスのエラーメッセージ */
	private static final String MAX_COMMENT_MESSAGE = MAX_EMAIL_LENGTH + "文字を超えています。";
	
	/** 返信名 */
	@NotNull
	@Size(min       = MIN_NAME_LENGTH, 
			max     = MAX_NAME_LENGTH, 
			message = MAX_NAME_MESSAGE)
	private String name;
	
	/** 返信Eメールアドレス */
	@NotNull
	@Email(message = ERROR_EMAIL_MESSAGE)
	@Size(min       = MIN_EMAIL_LENGTH,
			max     = MAX_EMAIL_LENGTH, 
			message = MAX_EMAIL_MESSAGE)
	private String email;
	
	/** 返信コメント */
	@NotNull
	@Size(min       = MIN_COMMENT_LENGTH,
			max     = MAX_COMMENT_LENGTH, 
			message = MAX_COMMENT_MESSAGE)
	private String comment;

	public InquiryReplyForm() {
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
