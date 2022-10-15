package com.example.demo.common.id.blog;

import com.example.demo.common.id.intro.IntroId;

/**
 * ブログコメントモデル
 * @author nanai
 *
 */
public class BlogCommentId implements IntroId {
	
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
