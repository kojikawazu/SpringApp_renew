package com.example.demo.common.id.blog;

import com.example.demo.common.id.intro.IntroId;

/**
 * ブログIDクラス
 * @author nanai
 *
 */
public class BlogId implements IntroId {

	/** ID */
	private int id;
	
	/**
	 * コンストラクタ
	 * @param id
	 */
	public BlogId(int id) {
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
