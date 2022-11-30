package com.example.demo.app.common.id.blog;

import com.example.demo.common.id.SuperId;

/**
 * ブログコメントモデル
 * @author nanai
 *
 */
public class BlogCommentId implements SuperId {
	
	/** ID */
	private int id;
	
	/**
	 * コンストラクタ
	 * @param id
	 */
	public BlogCommentId(int id) {
		this.id = id;
	}

	/**
	 * getter
	 * @return id
	 */
	@Override
	public int getId() {
		return this.id;
	}
}
