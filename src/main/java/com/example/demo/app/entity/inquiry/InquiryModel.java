package com.example.demo.app.entity.inquiry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.app.common.id.inquiry.InquiryId;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;

/**
 * 問い合わせモデル
 * @author nanai
 *
 */
public class InquiryModel {
	
	/** 問い合わせID */
	private InquiryId               id;
	
	/** 名前 */
	private NameWord                name;
	
	/** Eメール */
	private EmailWord               email;
	
	/** コメント*/
	private CommentWord             comment;
	
	/** 生成日付 */
	private LocalDateTime           created;
	
	/** 返信リスト */
	private List<InquiryReplyModel> replyList;

	/**
	 * コンストラクタ
	 * @param id
	 * @param name
	 * @param email
	 * @param comment
	 * @param created
	 * @param replyList
	 */
	public InquiryModel(
			InquiryId               id,
			NameWord                name,
			EmailWord               email,
			CommentWord             comment,
			LocalDateTime           created,
			List<InquiryReplyModel> replyList) {
		this.id = (id == null ?
				new InquiryId(0) :
				id);
		
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
		
		this.replyList = (replyList == null ?
				new ArrayList<InquiryReplyModel>() :
				replyList);
	}
	
	/**
	 * コンストラクタ
	 * @param id
	 * @param name
	 * @param email
	 * @param comment
	 * @param created
	 */
	public InquiryModel(
			InquiryId     id,
			NameWord      name,
			EmailWord     email,
			CommentWord   comment,
			LocalDateTime created) {
		this.id = (id == null ?
				new InquiryId(0) :
				id);
		
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
		
		this.replyList = new ArrayList<InquiryReplyModel>();
	}
	
	/**
	 * コンストラクタ
	 * @param name
	 * @param email
	 * @param comment
	 * @param created
	 */
	public InquiryModel(
			NameWord      name,
			EmailWord     email,
			CommentWord   comment,
			LocalDateTime created) {
		this.id = new InquiryId(0);
		
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
		
		this.replyList = new ArrayList<InquiryReplyModel>();
	}
	
	/**
	 * コンストラクタ
	 * @param model
	 */
	public InquiryModel(
			InquiryModel model) {
		if(model == null) {
			this.id        = new InquiryId(0);
			this.name      = new NameWord("");
			this.email     = new EmailWord("");
			this.comment   = new CommentWord("");
			this.created   = LocalDateTime.now();
			this.replyList = new ArrayList<InquiryReplyModel>();
		} else {
			this.id        = new InquiryId(model.getId());
			this.name      = new NameWord(model.getName());
			this.email     = new EmailWord(model.getEmail());
			this.comment   = new CommentWord(model.getComment());
			this.created   = model.getCreated();
			this.replyList = model.getReplyList();
		}
	}

	public int getId() {
		return this.id.getId();
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
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		return ( created != null ? created : dateTime );
	}
	
	public List<InquiryReplyModel> getReplyList() {
		return replyList;
	}

}
