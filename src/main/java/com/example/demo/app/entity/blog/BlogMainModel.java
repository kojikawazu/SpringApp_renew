package com.example.demo.app.entity.blog;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.common.id.blog.BlogId;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.NameWord;
import com.example.demo.common.word.TagWord;
import com.example.demo.common.word.TittleWord;

/**
 * メインブログモデル
 * @author nanai
 *
 */
public class BlogMainModel {
	
	/** ブログID */
	private BlogId               id;
	
	/** タイトル */
	private TittleWord           title;
	
	/** タグ */
	private TagWord              tag;
	
	/** コメント */
	private CommentWord          comment;
	
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
			TittleWord           title,
			TagWord              tag,
			CommentWord          comment,
			ThanksCntNumber      thanksCnt,
			LocalDateTime        created,
			LocalDateTime        updated,
			List<BlogReplyModel> replyList
			) {
		this.id = (id == null ?
				new BlogId(0) :
				id);
		
		this.title = (title == null ?
				new TittleWord("") :
				title);
		
		this.tag = (tag == null ?
				new TagWord("") :
				tag);
		
		this.comment = (comment == null ?
				new CommentWord("") :
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
			TittleWord           title,
			TagWord              tag,
			CommentWord          comment,
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
			TittleWord           title,
			TagWord              tag,
			CommentWord          comment,
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
			this.title     = new TittleWord("");
			this.tag       = new TagWord("");
			this.comment   = new CommentWord("");
			this.thanksCnt = new ThanksCntNumber(0);
			this.created   = LocalDateTime.now();
			this.updated   = LocalDateTime.now();
			this.replyList = new ArrayList<BlogReplyModel>();
		} else {
			this.id        = new BlogId(model.getId());
			this.title     = new TittleWord(model.getTitle());
			this.tag       = new TagWord(model.getTag());
			this.comment   = new CommentWord(model.getComment());
			this.thanksCnt = new ThanksCntNumber(model.getThanksCnt());
			this.created   = model.getCreated();
			this.updated   = model.getUpdated();
			this.replyList = model.getReplyList();
		}
	}
	
	public int getId() {
		return this.id.getId();
	}
	
	public String getTitle() {
		return this.title.getWord();
	}
	
	public String getTag() {
		return this.tag.getWord();
	}
	
	public String getComment() {
		return this.comment.getWord();
	}
	
	public int getThanksCnt() {
		return this.thanksCnt.getNumber();
	}
	
	public LocalDateTime getCreated() {
		return ( this.created != null ? 
				this.created : 
				LocalDateTime.of(2000, 01, 01, 00, 00, 00) );
	}
	
	public LocalDateTime getUpdated() {
		return ( this.updated != null ? 
				this.updated : 
				LocalDateTime.of(2000, 01, 01, 00, 00, 00) );
	}
	
	public List<BlogReplyModel> getReplyList() {
		return this.replyList;
	}
}
