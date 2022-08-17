package com.example.demo.app.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.common.id.InquiryId;
import com.example.demo.common.word.NameWord;

/**
 * 問い合わせモデル
 * @author nanai
 *
 */
public class InquiryModel {
	
	/** 問い合わせID */
	private InquiryId id;
	
	/** 名前 */
	private NameWord name;
	
	/** Eメール */
	private NameWord email;
	
	/** コメント*/
	private NameWord comment;
	
	/** 生成日付 */
	private LocalDateTime created;
	
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
			InquiryId id,
			NameWord name,
			NameWord email,
			NameWord comment,
			LocalDateTime created,
			List<InquiryReplyModel> replyList) {
		this.id = (id == null ?
				new InquiryId(0) :
				id);
		
		this.name = (name == null ?
				new NameWord("") :
					name);
		
		this.email = (email == null ?
				new NameWord("") :
				email);
		
		this.comment = (comment == null ?
				new NameWord("") :
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
			InquiryId id,
			NameWord name,
			NameWord email,
			NameWord comment,
			LocalDateTime created) {
		this.id = (id == null ?
				new InquiryId(0) :
				id);
		
		this.name = (name == null ?
				new NameWord("") :
					name);
		
		this.email = (email == null ?
				new NameWord("") :
				email);
		
		this.comment = (comment == null ?
				new NameWord("") :
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
			NameWord name,
			NameWord email,
			NameWord comment,
			LocalDateTime created) {
		this.id = new InquiryId(0);
		
		this.name = (name == null ?
				new NameWord("") :
					name);
		
		this.email = (email == null ?
				new NameWord("") :
				email);
		
		this.comment = (comment == null ?
				new NameWord("") :
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
			this.email     = new NameWord("");
			this.comment   = new NameWord("");
			this.created   = LocalDateTime.now();
			this.replyList = new ArrayList<InquiryReplyModel>();
		} else {
			this.id        = new InquiryId(model.getId());
			this.name      = new NameWord(model.getName());
			this.email     = new NameWord(model.getEmail());
			this.comment   = new NameWord(model.getComment());
			this.created   = model.getCreated();
			this.replyList = model.getReplyList();
		}
	}

	public int getId() {
		return this.id.getId();
	}

	protected void setId(int id) {
		this.id = new InquiryId(id);
	}

	public String getName() {
		return this.name.getWord();
	}

	protected void setName(String name) {
		if(name == null)	return ;
		this.name = new NameWord(name);
	}

	public String getEmail() {
		return this.email.getWord();
	}

	protected void setEmail(String email) {
		if(email == null)	return ;
		this.email = new NameWord(email);
	}

	public String getComment() {
		return this.comment.getWord();
	}

	protected void setComment(String comment) {
		if(comment == null)	return ;
		this.comment = new NameWord(comment);
	}

	public LocalDateTime getCreated() {
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		return ( created != null ? created : dateTime );
	}

	protected void setCreated(LocalDateTime created) {
		if(created == null)	return ;
		this.created = created;
	}
	
	public List<InquiryReplyModel> getReplyList() {
		return replyList;
	}

	protected void setReplyList(List<InquiryReplyModel> replyList) {
		if(replyList == null)	return ;
		this.replyList = replyList;
	}
}
