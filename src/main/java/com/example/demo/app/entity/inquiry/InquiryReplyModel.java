package com.example.demo.app.entity.inquiry;

import java.time.LocalDateTime;

import com.example.demo.app.common.id.inquiry.InquiryId;
import com.example.demo.app.common.id.inquiry.InquiryReplyId;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;

/**
 * 問い合わせ返信モデルクラス
 * @author nanai
 *
 */
public class InquiryReplyModel {
	
	/** 問い合わせ返信ID */
	private InquiryReplyId id;
	
	/** 問い合わせID */
	private InquiryId      inquiry_id;
	
	/** 名前 */
	private NameWord       name;
	
	/** Eメール */
	private EmailWord      email;
	
	/** コメント */
	private CommentWord    comment;
	
	/** 生成日付 */
	private LocalDateTime  created;

	/**
	 * コンストラクタ
	 * @param id
	 * @param inquiry_id
	 * @param name
	 * @param email
	 * @param comment
	 * @param created
	 */
	public InquiryReplyModel(
			InquiryReplyId id,
			InquiryId      inquiry_id,
			NameWord       name,
			EmailWord      email,
			CommentWord    comment,
			LocalDateTime  created) {
		this.id = (id == null ?
				new InquiryReplyId(0) :
				id);
		
		this.inquiry_id = (inquiry_id == null ?
				new InquiryId(0) :
					inquiry_id);
		
		this.name = (name == null ?
				new NameWord("") :
				name);
		
		this.email = (email == null ?
				new EmailWord("") :
				email);
		
		this.comment = (comment == null ?
				new CommentWord("") :
				comment);
		
		this.created = (created == null ?
				LocalDateTime.now() :
				created);
	}
	
	/**
	 * コンストラクタ
	 * @param inquiry_id
	 * @param name
	 * @param email
	 * @param comment
	 * @param created
	 */
	public InquiryReplyModel(
			InquiryId      inquiry_id,
			NameWord       name,
			EmailWord      email,
			CommentWord    comment,
			LocalDateTime  created) {
		this.id = new InquiryReplyId(0);
		
		this.inquiry_id = (inquiry_id == null ?
				new InquiryId(0) :
					inquiry_id);
		
		this.name = (name == null ?
				new NameWord("") :
				name);
		
		this.email = (email == null ?
				new EmailWord("") :
				email);
		
		this.comment = (comment == null ?
				new CommentWord("") :
				comment);
		
		this.created = (created == null ?
				LocalDateTime.now() :
				created);
	}
	
	/**
	 * コンストラクタ
	 * @param model
	 */
	public InquiryReplyModel(
			InquiryReplyModel model) {
		if(model == null) {
			this.id         = new InquiryReplyId(0);
			this.inquiry_id = new InquiryId(0);
			this.name       = new NameWord("");
			this.email      = new EmailWord("");
			this.comment    = new CommentWord("");
			this.created    = LocalDateTime.now();
		} else {
			this.id         = new InquiryReplyId(model.getId());
			this.inquiry_id = new InquiryId(model.getInquiry_id());
			this.name       = new NameWord(model.getName());
			this.email      = new EmailWord(model.getEmail());
			this.comment    = new CommentWord(model.getComment());
			this.created    = model.getCreated();
		}
	}

	public int getId() {
		return this.id.getId();
	}

	public int getInquiry_id() {
		return this.inquiry_id.getId();
	}

	public String getName() {
		return this.name.getWord();
	}

	public String getEmail() {
		return this.email.getWord();
	}

	public String getComment() {
		return this.comment.getWord();
	}

	public LocalDateTime getCreated() {
		return this.created;
	}
}
