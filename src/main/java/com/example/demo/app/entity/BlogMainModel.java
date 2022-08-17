package com.example.demo.app.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.common.id.BlogId;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.NameWord;

/**
 * メインブログモデル
 * @author nanai
 *
 */
public class BlogMainModel {
	
	/** ブログID */
	private BlogId               id;
	
	/** タイトル */
	private NameWord             title;
	
	/** タグ */
	private NameWord             tag;
	
	/** コメント */
	private NameWord             comment;
	
	/** いいね数 */
	private ThanksCntNumber      thanksCnt;
	
	/** 生成日付 */
	private LocalDateTime        created;
	
	/** 更新日付 */
	private LocalDateTime        updated;
	
	/** 返信モデルリスト */
	private List<BlogReplyModel> replyList;
	
	/**
	 * コンストラクタ
	 * @param id
	 * @param title
	 * @param tag
	 * @param comment
	 * @param thanksCnt
	 * @param created
	 * @param updated
	 * @param replyList
	 */
	public BlogMainModel(
			BlogId               id,
			NameWord             title,
			NameWord             tag,
			NameWord             comment,
			ThanksCntNumber      thanksCnt,
			LocalDateTime        created,
			LocalDateTime        updated,
			List<BlogReplyModel> replyList
			) {
		this.id = (id == null ?
				new BlogId(0) :
				id);
		
		this.title = (title == null ?
				new NameWord("") :
				title);
		
		this.tag = (tag == null ?
				new NameWord("") :
				tag);
		
		this.comment = (comment == null ?
				new NameWord("") :
				comment);
		
		this.thanksCnt = (thanksCnt == null ?
				new ThanksCntNumber(0) :
				thanksCnt);
		
		this.created = (created == null ?
				LocalDateTime.now() :
				created);
		
		this.updated = (updated == null ?
				LocalDateTime.now() :
				updated);
		
		this.replyList = (replyList == null ?
				new ArrayList<BlogReplyModel>() :
				replyList);
	}
	
	/**
	 * コンストラクタ
	 * @param id
	 * @param title
	 * @param tag
	 * @param comment
	 * @param thanksCnt
	 * @param created
	 * @param updated
	 */
	public BlogMainModel(
			BlogId               id,
			NameWord             title,
			NameWord             tag,
			NameWord             comment,
			ThanksCntNumber      thanksCnt,
			LocalDateTime        created,
			LocalDateTime        updated
			) {
		this(id,
			title,
			tag,
			comment,
			thanksCnt,
			created,
			updated,
			new ArrayList<BlogReplyModel>());
	}
	
	/**
	 * コンストラクタ
	 * @param title
	 * @param tag
	 * @param comment
	 * @param thanksCnt
	 * @param created
	 * @param updated
	 */
	public BlogMainModel(
			NameWord             title,
			NameWord             tag,
			NameWord             comment,
			ThanksCntNumber      thanksCnt,
			LocalDateTime        created,
			LocalDateTime        updated
			) {
		this(new BlogId(0),
			title,
			tag,
			comment,
			thanksCnt,
			created,
			updated,
			new ArrayList<BlogReplyModel>());
	}
	
	/**
	 * コンストラクタ
	 * @param model
	 */
	public BlogMainModel(
			BlogMainModel model) {
		if(model == null) {
			this.id        = new BlogId(0);
			this.title     = new NameWord("");
			this.tag       = new NameWord("");
			this.comment   = new NameWord("");
			this.thanksCnt = new ThanksCntNumber(0);
			this.created   = LocalDateTime.now();
			this.updated   = LocalDateTime.now();
			this.replyList = new ArrayList<BlogReplyModel>();
		} else {
			this.id        = new BlogId(model.getId());
			this.title     = new NameWord(model.getTitle());
			this.tag       = new NameWord(model.getTag());
			this.comment   = new NameWord(model.getComment());
			this.thanksCnt = new ThanksCntNumber(model.getThanksCnt());
			this.created   = model.getCreated();
			this.updated   = model.getUpdated();
			this.replyList = model.getReplyList();
		}
	}
	
	public int getId() {
		return this.id.getId();
	}
	
	protected void setId(int id) {
		this.id = new BlogId(id);
	}
	
	public String getTitle() {
		return this.title.getWord();
	}
	
	protected void setTitle(String title) {
		if(title == null)	return ;
		this.title = new NameWord(title);
	}
	
	public String getTag() {
		return this.tag.getWord();
	}
	
	protected void setTag(String tag) {
		if(tag == null)	return ;
		this.tag = new NameWord(tag);
	}
	
	public String getComment() {
		return this.comment.getWord();
	}
	
	protected void setComment(String comment) {
		if(comment == null)	return ;
		this.comment = new NameWord(comment);
	}
	
	public int getThanksCnt() {
		return this.thanksCnt.getNumber();
	}
	
	protected void setThanksCnt(int thanksCnt) {
		this.thanksCnt = new ThanksCntNumber(thanksCnt);
	}
	public LocalDateTime getCreated() {
		return ( this.created != null ? 
				this.created : 
				LocalDateTime.of(2000, 01, 01, 00, 00, 00) );
	}
	
	protected void setCreated(LocalDateTime created) {
		if(created == null)	return ;
		this.created = created;
	}
	
	public LocalDateTime getUpdated() {
		return ( this.updated != null ? 
				this.updated : 
				LocalDateTime.of(2000, 01, 01, 00, 00, 00) );
	}
	
	protected void setUpdated(LocalDateTime updated) {
		if(updated == null)	return ;
		this.updated = updated;
	}
	
	public List<BlogReplyModel> getReplyList() {
		return this.replyList;
	}
	
	public void setReplyList(List<BlogReplyModel> replyList) {
		if(replyList == null)	return ;
		this.replyList = replyList;
	}
}
