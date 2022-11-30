package com.example.demo.app.entity.blog;

import com.example.demo.app.common.id.blog.BlogTagId;
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

	public String getTag() {
		return this.tag.getWord();
	}
}
