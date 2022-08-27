package com.example.demo.common.id;

import com.example.demo.common.word.SuperWord;

/**
 * 問い合わせ返信IDクラス
 * @author nanai
 *
 */
public class InquiryReplyId implements IntroId {

	/** ID */
	private int id;
	
	/**
	 * コンストラクタ
	 * @param id
	 */
	public InquiryReplyId(int id) {
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
