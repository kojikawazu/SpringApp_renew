package com.example.demo.common.id.inquiry;

import com.example.demo.common.id.intro.IntroId;

/**
 * 問い合わせIDクラス
 * @author nanai
 *
 */
public class InquiryId implements IntroId {

	/** ID */
	private int id;
	
	/**
	 * コンストラクタ
	 * @param id
	 */
	public InquiryId(int id) {
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
