package com.example.demo.common.id.blog;

import com.example.demo.common.id.intro.IntroId;

/**
 * ブログタグIDモデル
 * @author nanai
 *
 */
public class BlogTagId implements IntroId {

	/** ID */
	private int id;
	
	/**
	 * コンストラクタ
	 * @param id
	 */
	public BlogTagId(int id) {
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