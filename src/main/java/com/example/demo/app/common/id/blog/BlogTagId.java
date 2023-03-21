package com.example.demo.app.common.id.blog;

import com.example.demo.common.id.NormalId;

/**
 * ブログタグIDモデル
 * <br>
 * extends {@link NormalId}
 * @author nanai
 *
 */
public class BlogTagId extends NormalId {

	/**
	 * コンストラクタ
	 */
	public BlogTagId() {
		super();
	}

	/**
	 * コンストラクタ
	 * @param id
	 */
	public BlogTagId(int id) {
		super();
		this.id = id;
	}

	/**
	 * setter
	 * @param id {@link BlogTagId}
	 */
	public void setId(BlogTagId id) {
		if (id == null)	return;
		this.id = id.getId();
	}
}
