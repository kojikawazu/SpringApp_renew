package com.example.demo.app.common.id.blog;

import com.example.demo.common.id.NormalId;

/**
 * ブログコメントモデル
 * <br>
 * extends {@link NormalId}
 * @author nanai
 *
 */
public class BlogCommentId extends NormalId {

	/**
	 * コンストラクタ
	 */
	public BlogCommentId() {
		super();
	}

	/**
	 * コンストラクタ
	 * @param id
	 */
	public BlogCommentId(int id) {
		super();
		this.id = id;
	}

	/**
	 * setter
	 * @param id {@link BlogCommentId}
	 */
	public void setId(BlogCommentId id) {
		if (id == null)	return;
		this.id = id.getId();
	}
}
