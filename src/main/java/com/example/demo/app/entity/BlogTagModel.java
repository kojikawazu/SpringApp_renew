package com.example.demo.app.entity;

import com.example.demo.common.id.BlogTagId;
import com.example.demo.common.word.NameWord;
import com.example.demo.common.word.TagWord;

/**
 * ブログタグモデル
 * @author nanai
 *
 */
public class BlogTagModel {
	
	/** タグID */
	private BlogTagId id;
	
	/** タグ名 */
	private TagWord   tag;

	/**
	 * コンストラクタ
	 * @param id
	 * @param tag
	 */
	public BlogTagModel(
			BlogTagId id,
			TagWord   tag
			) {
		this.id = (id == null ?
				new BlogTagId(0) :
				id);
		
		this.tag = (tag == null ?
				new TagWord("") :
				tag);
	}
	
	/**
	 * コンストラクタ
	 * @param model
	 */
	public BlogTagModel(
			BlogTagModel model) {
		if(model == null) {
			this.id  = new BlogTagId(0);
			this.tag = new TagWord("");
		} else {
			this.id = new BlogTagId(model.getId());
			this.tag = new TagWord(model.getTag());
		}
	}
	
	/**
	 * コンストラクタ
	 * @param tag
	 */
	public BlogTagModel(
			TagWord tag) {
		this.id = new BlogTagId(0);
		
		this.tag = (tag == null ?
				new TagWord("") :
				tag);
	}

	public int getId() {
		return this.id.getId();
	}

	protected void setId(int id) {
		this.id = new BlogTagId(id);
	}

	public String getTag() {
		return this.tag.getWord();
	}

	protected void setTag(String tag) {
		if(tag == null)	return ;
		this.tag = new TagWord(tag);
	}
}
